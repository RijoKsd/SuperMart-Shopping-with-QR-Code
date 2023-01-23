
package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class sendComplaint extends AppCompatActivity {
    EditText complaint;
    Button complaintBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_complaint);
        complaint.findViewById(R.id.editTextTextPersonName5);
        complaintBtn.findViewById(R.id.button8);
    }
}