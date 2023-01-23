package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class customViewReply extends AppCompatActivity {
    TextView complaintDate,complaint,replyDate,reply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_reply);
        complaintDate.findViewById(R.id.textView23);
        complaint.findViewById(R.id.textView25);
        replyDate.findViewById(R.id.textView27);
        reply.findViewById(R.id.textView29);

    }
}