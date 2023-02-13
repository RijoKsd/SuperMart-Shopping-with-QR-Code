package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button login,registerBtn;

    SharedPreferences sh;
    String url;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        registerBtn = findViewById(R.id.register);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(getApplicationContext(), Registration.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userEmail = email.getText().toString();
                final String userPassword = password.getText().toString();
                sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                sh.getString("ip","");
                url=sh.getString("url","")+"and_login";

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                        Toast.makeText(Login.this, "welcome", Toast.LENGTH_SHORT).show();
                                        String typ = jsonObj.getString("type");
                                        String id = jsonObj.getString("lid");
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", id);
                                        ed.commit();
                                        if (typ.equalsIgnoreCase("user")) {
                                            Toast.makeText(getApplicationContext(), "Welcome to supermart", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(getApplicationContext(), UserHome.class);
                                            startActivity(i);
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {

                    //                value Passing android to python
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("u", userEmail);  //passing to python
                        params.put("p", userPassword);


                        return params;
                    }
                };


                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);


            }
        });
    }
}