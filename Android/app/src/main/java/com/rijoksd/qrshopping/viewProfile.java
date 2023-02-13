package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class viewProfile extends AppCompatActivity {
    ImageView profileImage;
    TextView profileName, profileGender, profileMail, profilePlace, profilePin, profilePhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        profileImage = findViewById(R.id.profilePic);
        profileName = findViewById(R.id.profileName);
        profileGender = findViewById(R.id.profileGender);
        profileMail = findViewById(R.id.profileMail);
        profilePlace = findViewById(R.id.profilePlace);
        profilePin  = findViewById(R.id.profilePin);
        profilePhone = findViewById(R.id.profilePhone);




    }
}