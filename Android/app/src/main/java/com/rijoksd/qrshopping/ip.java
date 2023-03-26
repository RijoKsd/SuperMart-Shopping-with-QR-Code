package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ip extends AppCompatActivity {
    EditText ipAddress;
    Button ipRegister;
    SharedPreferences sh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        ipAddress = findViewById(R.id.ip_address);
        ipRegister = findViewById(R.id.ip_register);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ipAddress.setText(sh.getString("ip",""));

        ipRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ip = ipAddress.getText().toString();
                if (ip.equalsIgnoreCase("" )) {
                    ipAddress.setError("IP address  is required");
                }else {


                    String url1 = "http://" + ip + ":4000/";
                    SharedPreferences.Editor ed = sh.edit();
                    ed.putString("ip", ip);
                    ed.putString("url", url1);
                    ed.commit();
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),ip.class);
        startActivity(i);

    }
}