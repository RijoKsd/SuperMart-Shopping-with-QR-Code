package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

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

public class quantity extends AppCompatActivity {
    EditText quantity;
    Button quantityBtn;
    SharedPreferences sh;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        quantity=findViewById(R.id.quantity);
        quantityBtn=findViewById(R.id.quantityBtn);

        quantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String productQuantity = quantity.getText().toString();

                sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                sh.getString("ip","");
                sh.getString("url","");
                url=sh.getString("url","")+"and_quantity";




                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(quantity.this, "Quantity send", Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(getApplicationContext(),viewProduct.class);
                                        startActivity(i);

                                    }if (jsonObj.getString("status").equalsIgnoreCase("update")) {
                                        Toast.makeText(quantity.this, "Quantity updated", Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(getApplicationContext(),viewProduct.class);
                                        startActivity(i);

                                    } if (jsonObj.getString("status").equalsIgnoreCase("cart")) {
                                        Toast.makeText(quantity.this, "Add to cart", Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(getApplicationContext(),viewProduct.class);
                                        startActivity(i);

                                    } if (jsonObj.getString("status").equalsIgnoreCase("greater")) {
                                        Toast.makeText(quantity.this, "Out of stock", Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(getApplicationContext(),viewProduct.class);
                                        startActivity(i);

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

                    //            value Passing android to python
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("qua",productQuantity);//passing to python
                        params.put("id", sh.getString("lid",""));//passing to python
                        params.put("shopID", sh.getString("shopID",""));//passing to python
                        params.put("productPrice", sh.getString("productPrice",""));//passing to python
                        params.put("productID", sh.getString("productID",""));//passing to python
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