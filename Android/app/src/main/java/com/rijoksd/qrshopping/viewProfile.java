package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class viewProfile extends AppCompatActivity {
    ImageView profileImage;
    TextView profileName, profileGender, profileMail, profilePlace, profilePin, profilePhone;

    SharedPreferences sh;
    String url,lid,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        profileImage =(ImageView) findViewById(R.id.profilePic);
        profileName = (TextView) findViewById(R.id.profileName);
        profileGender = (TextView)findViewById(R.id.profileGender);
        profileMail = (TextView)findViewById(R.id.profileMail);
        profilePlace = (TextView)findViewById(R.id.profilePlace);
        profilePin  =(TextView) findViewById(R.id.profilePin);
        profilePhone = (TextView)findViewById(R.id.profilePhone);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("ip", "");
        url = "http://" + ip + ":4000/and_view_profile";
        lid = sh.getString("lid", "");

//        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        sh.getString("ip","");
//        url=sh.getString("url","")+"and_view_profile";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONObject jj = jsonObj.getJSONObject("data");

                                profileName.setText(jj.getString("name"));
                                profileMail.setText(jj.getString("email"));
                                profilePhone.setText(jj.getString("phone_no"));
                                profilePlace.setText(jj.getString("place"));
                                profilePin.setText(jj.getString("pincode"));
                                profileGender.setText(jj.getString("gender"));


                                String image = jj.getString("image");
                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");
                                String url = "http://" + ip + ":4000" + image;
                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(profileImage);//circle

                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
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

                params.put("userID", lid);//passing to python

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
}