package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class customViewBill extends AppCompatActivity {
    TextView billDate,billAmount;

    Button viewProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_bill);
        billDate.findViewById(R.id.textView14);
        billAmount.findViewById(R.id.textView16);

        viewProductBtn.findViewById(R.id.button5);

    }
}