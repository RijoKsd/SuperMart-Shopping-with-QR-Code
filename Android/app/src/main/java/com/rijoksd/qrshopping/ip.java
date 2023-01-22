package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ip extends AppCompatActivity {
    EditText ip_address;
    Button ip_register;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        ip_address = findViewById(R.id.ip_address);
        ip_register = findViewById(R.id.ip_register);

    }
}