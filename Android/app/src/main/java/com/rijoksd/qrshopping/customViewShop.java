
package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class customViewShop extends AppCompatActivity {
    ImageView shopImage;
    TextView shopName,shopAddress, shopEmail,shopPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_shop);

        shopName.findViewById(R.id.textView5);
        shopAddress.findViewById(R.id.textView7);
        shopEmail.findViewById(R.id.textView9);
        shopPhone.findViewById(R.id.textView11);

        shopImage.findViewById(R.id.imageView2);


    }
}