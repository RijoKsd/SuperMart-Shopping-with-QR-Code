package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class customViewProduct extends AppCompatActivity {
    ImageView productImage;

    TextView productName,productAmount,productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_product);
        productName.findViewById(R.id.textView17);
        productAmount.findViewById(R.id.textView21);
        productPrice.findViewById(R.id.textView19);

        productImage.findViewById(R.id.imageView3);

    }
}