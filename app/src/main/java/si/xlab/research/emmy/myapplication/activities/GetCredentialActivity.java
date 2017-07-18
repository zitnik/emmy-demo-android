package si.xlab.research.emmy.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.V8;

import java.io.InputStream;

import si.xlab.research.emmy.myapplication.R;


public class GetCredentialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_credential);
        System.loadLibrary("j2v8");
        V8 runtime = V8.createV8Runtime(getApplicationInfo().dataDir);
        runtime.executeVoidScript("console.log('heloworldddddddddddddd')");
        runtime.release();
    }

    @Override
    protected void onStart() {
        super.onStart();

        final NodeJS nodeJS = NodeJS.createNodeJS();

        String scriptFileName = "auth.js";
        InputStream inStream = this.getResources().openRawResource(R.raw.auth);

        /*try {
            FileOutputStream outStream = openFileOutput(scriptFileName, Context.MODE_PRIVATE);
            outStream.write(IOUtils.toByteArray(inStream));
            outStream.close();

            File scriptFile = new File(getFilesDir().getPath(), "auth.js");
            nodeJS.require(scriptFile);
            while(nodeJS.isRunning()) {
                nodeJS.handleMessage();
            }
            nodeJS.release();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

}
