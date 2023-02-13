package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Registration extends AppCompatActivity {
    EditText username,place,pincode,mail,phone,registerPassword;
    RadioGroup gender;
    RadioButton male,female,other;
    ImageView image;
    Button registerBtn;

    Bitmap bitmap = null;
    ProgressDialog pd;
    String url = "";
    Button reg;
    SharedPreferences sh;
//    String gender= "Male";
    RadioButton r1,r2,r3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username=findViewById(R.id.username);
        place=findViewById(R.id.place);
        pincode=findViewById(R.id.pincode);
        mail=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        registerPassword=findViewById(R.id.register_password);

        registerBtn=findViewById(R.id.register_btn);
//       gender=findViewById(R.id.gender);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        other=findViewById(R.id.other);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String registerUsername = username.getText().toString();
                final String registerUserPlace = place.getText().toString();
                final String registerUserPincode = pincode.getText().toString();
                final String registerUserMail = mail.getText().toString();
                final String registerUserPhone = phone.getText().toString();
                final String registerUserPassword = registerPassword.getText().toString();
//                final String registerUserGender =gender.getTōext().toString();

                uploadBitmap( registerUsername,registerUserPlace,registerUserPincode,registerUserMail,registerUserPhone,registerUserPassword );



            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                image.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //converting to bitarray
    public byte[] getFileDataFromDrawable() {
        return getFileDataFromDrawable(null);
    }

    //converting to bitarray
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void uploadBitmap(final String registerUsername, final String registerUserPlace, final String registerUserPincode, final String registerUserMail, final String registerUserPhone, final String registerUserPassword) {


        pd = new ProgressDialog(Registration.this);
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            pd.dismiss();


                            JSONObject obj = new JSONObject(new String(response.data));

                            if(obj.getString("status").equals("ok")){
                                Toast.makeText(getApplicationContext(), "Registration success", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Registration failed" ,Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("andUserName", registerUsername);//passing to python
                params.put("andUserPlace", registerUserPlace);//passing to python
                params.put("andUserPIN", registerUserPincode);
                params.put("andUserMail", registerUserMail);
                params.put("andUserPhone", registerUserPhone);
                params.put("andUserPassword", registerUserPassword);
//                params.put("andUserGender", registerUserGender);

                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);



}
    {
    }
}