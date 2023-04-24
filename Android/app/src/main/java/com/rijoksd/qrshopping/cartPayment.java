
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class cartPayment extends AppCompatActivity {

    EditText bankName,bankAccountNo,bankIFSCCode;
    TextView totalAmountToPay;
    Button onlinePay,offlinePay;
    SharedPreferences sh;
    String url,url1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_payment);
        bankName = findViewById(R.id.bankName);
        bankAccountNo = findViewById(R.id.bankAccountNo);
        bankIFSCCode = findViewById(R.id.bankIFSCCode);
        totalAmountToPay = findViewById(R.id.totalAmountToPay);
        onlinePay = findViewById(R.id.onlinePay);
        offlinePay = findViewById(R.id.offlinePay);

        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String p=sh.getString("productPrice","");

//        Toast.makeText(this, "ppppppppp"+p, Toast.LENGTH_SHORT).show();
        totalAmountToPay.setText(sh.getString("gnd_totl",""));
        offlinePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                sh.getString("ip","");
                sh.getString("url","");
                url1=sh.getString("url","")+"/and_offline_payment_from_cart";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(cartPayment.this, "Pay total amount  in bill counter", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(cartPayment.this, "Have a nice day", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(getApplicationContext(),UserHome.class);
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

                    //                value Passing android to python
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

//                        params.put("mid", sh.getString("mid", ""));//passing to python
                        params.put("id", sh.getString("lid",""));//passing to python
                        params.put("shopid", sh.getString("shopid", ""));//passing to python
                        params.put("mid", sh.getString("mid", ""));//passing to python


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

        onlinePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userBankName = bankName.getText().toString();
                final String userBankAccountNo = bankAccountNo.getText().toString();
                final String userBankIFSCCode = bankIFSCCode.getText().toString();
                final String userTotalAmountToPay = totalAmountToPay.getText().toString();
//                if (userBankName.equalsIgnoreCase("" )) {
//                    bankName.setError("Bank name is required");
//                } else if (userBankAccountNo.equalsIgnoreCase("" )) {
//                    bankAccountNo.setError("Account number is required");
//                }else  if (userBankIFSCCode.equalsIgnoreCase("" )) {
//                    bankIFSCCode.setError("IFSC code is required");
//                } else {

//                    SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                sh.getString("ip", "");
                sh.getString("url", "");
                url = sh.getString("url", "") + "/and_payment_from_cart";

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                        Toast.makeText(cartPayment.this, "Payment successfully completed", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), UserHome.class);
                                        startActivity(i);
                                    }
                                    if (jsonObj.getString("status").equalsIgnoreCase("insufficient")) {


                                        Toast.makeText(cartPayment.this, "Please check you bank balance!!!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), UserHome.class);
                                        startActivity(i);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Wrong Bank details", Toast.LENGTH_LONG).show();
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

                        params.put("bankName", userBankName);//passing to python
                        params.put("accountNo", userBankAccountNo);//passing to python
                        params.put("IFSCode", userBankIFSCCode);//passing to python
                        params.put("totalAmount", userTotalAmountToPay);//passing to python
                        params.put("id", sh.getString("lid", ""));//passing to python
                        params.put("shopid", sh.getString("shopid", ""));//passing to python
                        params.put("mid", sh.getString("mid", ""));//passing to python
                        params.put("total", sh.getString("gnd_totl", ""));//passing to python
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