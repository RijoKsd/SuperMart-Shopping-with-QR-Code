package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Registration extends AppCompatActivity {
    EditText username,place,pincode,mail,phone,registerPassword;
    RadioGroup gender;
    RadioButton male,female,other;
    Image image;
    Button registerBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username.findViewById(R.id.username);
        place.findViewById(R.id.place);
        pincode.findViewById(R.id.pincode);
        mail.findViewById(R.id.mail);
        phone.findViewById(R.id.phone);
        registerPassword.findViewById(R.id.register_password);

        registerBtn.findViewById(R.id.register_btn);

        gender.findViewById(R.id.gender);
        male.findViewById(R.id.male);
        female.findViewById(R.id.female);
        other.findViewById(R.id.other);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String registerUsername = username.getText().toString();
                final String registerUserPlace = place.getText().toString();
                final String registerUserPincode = pincode.getText().toString();
                final String registerUserMail = mail.getText().toString();
                final String registerUserPhone = phone.getText().toString();
                final String registerUserPassword = registerPassword.getText().toString();



            }
        });





    }
}