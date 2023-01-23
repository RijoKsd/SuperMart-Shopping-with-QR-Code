package com.rijoksd.qrshopping;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class addRating extends AppCompatActivity {
    RatingBar ratingBar;
    Button ratingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);
        ratingBar.findViewById(R.id.ratingBar);
        ratingBar.findViewById(R.id.button7);



    }
}