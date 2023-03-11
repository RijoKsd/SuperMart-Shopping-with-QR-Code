package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

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
    EditText username,place,pinCode,mail,phone,registerPassword;
    RadioButton male,female,other;
    ImageView pho;
    RadioGroup gen;
    Button registerBtn;
    Bitmap bitmap = null;
    ProgressDialog pd;
    String url;
    SharedPreferences sh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ip","");
        url=sh.getString("url","")+"and_user_register";

        username=findViewById(R.id.username);
        place=findViewById(R.id.place);
        pinCode=findViewById(R.id.pincode);
        mail=findViewById(R.id.mail);
        phone=findViewById(R.id.phone);
        pho=findViewById(R.id.image);
        registerPassword=findViewById(R.id.register_password);
        registerBtn=findViewById(R.id.register_btn);
        gen=findViewById(R.id.radioGroup);
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
        pho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registerUsername = username.getText().toString();
                String registerUserPlace = place.getText().toString();
                String registerUserPinCode = pinCode.getText().toString();
                String registerUserMail = mail.getText().toString();
                String registerUserPhone = phone.getText().toString();
                String registerUserPassword = registerPassword.getText().toString();
                String registerUserGender =((RadioButton)findViewById(gen.getCheckedRadioButtonId())).getText().toString();


//                if (registerUsername.equalsIgnoreCase("" )) {
//                    username.setError("Username is required");
//                } else if (registerUserPlace.equalsIgnoreCase("" )) {
//                    place.setError("Place is required");
//                } else if (registerUserPinCode.equalsIgnoreCase("" )) {
//                    pinCode.setError("Pincode is required");
//                } else if (registerUserMail.equalsIgnoreCase("" )) {
//                    mail.setError("Mail is required");
//                } else if (registerUserPhone.equalsIgnoreCase("" )) {
//                    phone.setError("Phone is required");
//                } else if (registerUserPassword.equalsIgnoreCase("" )) {
//                    registerPassword.setError("Password is required");
//                }else{

                uploadBitmap(registerUsername,registerUserPlace,registerUserPinCode,registerUserMail,registerUserGender,registerUserPhone,registerUserPassword );
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

                pho.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    //converting to bitarray
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void uploadBitmap(final String registerUsername, final String registerUserPlace, final String registerUserPincode, final String registerUserMail, final String registerUserGender, final String registerUserPhone, final String registerUserPassword)
        {


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

                                if (obj.getString("status").equals("ok")) {
                                    Toast.makeText(getApplicationContext(), "Registration successfully completed", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), Login.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
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
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    params.put("andUserName", registerUsername);//passing to python
                    params.put("andUserPlace", registerUserPlace);//passing to python
                    params.put("andUserPIN", registerUserPincode);
                    params.put("andUserMail", registerUserMail);
                    params.put("andUserPhone", registerUserPhone);
                    params.put("andUserPassword", registerUserPassword);
                    params.put("andUserGender", registerUserGender);
//                params.put("g",gender);

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
}
