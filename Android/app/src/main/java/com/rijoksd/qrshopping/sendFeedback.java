package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class sendFeedback extends AppCompatActivity {
    EditText feedback;
    Button feedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);
        feedback.findViewById(R.id.editTextTextPersonName4);
        feedbackBtn.findViewById(R.id.button6);
    }
}