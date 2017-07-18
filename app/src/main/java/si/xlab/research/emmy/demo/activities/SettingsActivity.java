package si.xlab.research.emmy.demo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import si.xlab.research.emmy.demo.R;

public class SettingsActivity extends AppCompatActivity {

    private EditText serverIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        serverIP = (EditText) findViewById(R.id.editText_server_ip);
        //serverIP.set
    }
}
