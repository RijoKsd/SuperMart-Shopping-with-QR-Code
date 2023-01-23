package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ip extends AppCompatActivity {
    EditText ipAddress;
    Button ipRegister;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        ipAddress = findViewById(R.id.ip_address);
        ipRegister = findViewById(R.id.ip_register);

    }
}