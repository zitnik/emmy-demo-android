package si.xlab.research.emmy.demo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.Map;

import mobile.CACertificate;
import mobile.Credential;
import mobile.OrgPubKeys;
import mobile.Pseudonym;
import si.xlab.research.emmy.demo.Config;
import si.xlab.research.emmy.demo.R;
import si.xlab.research.emmy.demo.client.Pseudonymsys;
import si.xlab.research.emmy.demo.client.PseudonymsysCA;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private static Config emmyConfig;
    private static String val = "121212121"; // secret provided by the user

    private Button btnSettings, btnExamplePsys, btnExampleSchnorr, btnExampleSchnorrEC,
        btnExamplePedersen, btnExamplePedersenEC, btnExampleCSPaillier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSettings = (Button)findViewById(R.id.button_settings);
        btnSettings.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });

        btnExamplePsys = (Button) findViewById(R.id.button_example_psys);
        btnExamplePsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    doPseudonymsys();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnExampleSchnorr = (Button) findViewById(R.id.button_example_schnorr);
        btnExampleSchnorrEC = (Button) findViewById(R.id.button_example_schnorr_ec);
        btnExamplePedersen = (Button) findViewById(R.id.button_example_pedersen);
        btnExamplePedersenEC = (Button) findViewById(R.id.button_example_pedersen_ec);
        btnExampleCSPaillier = (Button) findViewById(R.id.button_example_cspaillier);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadEmmyConfig();
        val = "121212121";
    }

    void loadEmmyConfig() {
        String tag = TAG + " parseEmmyConfig";

        InputStream configFile = this.getResources().openRawResource(R.raw.defaults);
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            byte[] configFileContent = IOUtils.toByteArray(configFile);

            // Tell native code to use this configuration
            config.Config.loadCustomConfigFromBuffer(configFileContent);

            // For ease of use, make this config accessible across Java app as well
            emmyConfig = mapper.readValue(configFileContent, Config.class);
        } catch (Exception e) {
            Log.e(tag, e.getMessage());
        }
    }

    void doPseudonymsys() throws Exception {
        Map<String,String> pseudonymsysDefaults = emmyConfig.getPseudonymsys();

        String userSecret = pseudonymsysDefaults.get("user1");
        String masterNymA = pseudonymsysDefaults.get("pseudonym_a");
        String masterNymB = pseudonymsysDefaults.get("pseudonym_b");
        String orgH1 = pseudonymsysDefaults.get("org_h1");
        Log.d("H1", orgH1);
        String orgH2 = pseudonymsysDefaults.get("org_h2");
        Log.d("H2", orgH2);

        try {
            // TODO - mobile client implemented in Go code currently doesn't use caCert,
            //  e.g. all connections from mobile clients are insecure
            /*String caCertName = "cacert.pem";
            InputStream caCertKeyContent = this.getResources().openRawResource(R.raw.cacert);
            FileOutputStream outStream = openFileOutput(caCertName, Context.MODE_PRIVATE);
            outStream.write(IOUtils.toByteArray(caCertKeyContent));
            outStream.close();
            String caCertPath = new File(getFilesDir().getPath(), caCertName).getAbsolutePath();*/

            PseudonymsysCA caClient = new PseudonymsysCA(emmyConfig.getEndpoint());

            CACertificate caCert = caClient.getCertificate(userSecret, masterNymA, masterNymB);
            Log.d("doPseudonymSys", "Obtained CA Certificates");

            Pseudonymsys pseudonymClientA = new Pseudonymsys(emmyConfig.getEndpoint());
            Pseudonymsys pseudonymClientB = new Pseudonymsys(emmyConfig.getEndpoint());

            Pseudonym pseudonymA = pseudonymClientA.registerWithOrg(userSecret, caCert);
            Pseudonym pseudonymB = pseudonymClientB.registerWithOrg(userSecret, caCert);
            Log.d("doPseudonymSys", "Obtained Nyms for organizations A and B (REGISTRATION)");

            OrgPubKeys orgPubKeys = new OrgPubKeys(orgH1, orgH2);
            Credential credential = pseudonymClientA.obtainCredential(userSecret, pseudonymA, orgPubKeys);
            Log.d("doPseudonymSys", "Obtained Anonymous Credential for authentication with organization A " + credential.toString());

            boolean result = pseudonymClientB.transferCredential("orgX", userSecret, pseudonymB, credential);
            Log.d("doPseudonymSys", "Transferred Anonymous Credential for authentication with organization A, authenticated with organization B: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
