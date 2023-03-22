package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class viewProductInfoWhenScan extends AppCompatActivity {
    Button addToCart,scanNow;
    TextView scannedProductName,scannedProductDetails,scannedProductQuantity,scannedProductPrice,scannedProductOfferPrice,tagOffer;
    ImageView scannedProductImage;


    SharedPreferences sh;
    String ip, url, url1, lid,contents;

//    String[] productID, productImage,productName,productDetails,productQuantity,productPrice,productOfferPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_info_when_scan);

        scannedProductImage = (ImageView) findViewById(R.id.imageView5);

        scannedProductName = (TextView) findViewById(R.id.textView51);
        scannedProductDetails = (TextView) findViewById(R.id.textView53);
        scannedProductQuantity = (TextView) findViewById(R.id.textView55);
        scannedProductPrice = (TextView) findViewById(R.id.textView57);
        scannedProductOfferPrice = (TextView) findViewById(R.id.textView59);
        tagOffer = (TextView) findViewById(R.id.textView58);

        scanNow = (Button) findViewById(R.id.button);
        scanNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),scanQr.class);
                startActivity(i);
            }
        });
        addToCart = (Button) findViewById(R.id.button2);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ScannedQrQuantity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });



        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("ip", "");
        url = "http://" + ip + ":4000/and_view_product_with_qr";
        lid = sh.getString("lid", "");

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {


                                if(jsonObj.getString("offer").equalsIgnoreCase("0"))
                                {
                                    scannedProductOfferPrice.setVisibility(View.INVISIBLE);
                                    tagOffer.setVisibility(View.INVISIBLE);



                                }
                                else {
                                    tagOffer.setVisibility(View.VISIBLE);
                                    scannedProductOfferPrice.setVisibility(View.VISIBLE);
                                }
                                JSONObject jj = jsonObj.getJSONObject("data");
                               scannedProductName.setText(jj.getString("n"));
                                scannedProductDetails.setText(jj.getString("details"));
                                scannedProductQuantity.setText(jj.getString("quantity"));
                                scannedProductPrice.setText(jj.getString("price"));
//                                    # Discount = ActualPrice - (ActualPrice * Discount_Rate / 100;
                                int offerPrice = Integer.parseInt(jj.getString("price")) - ( Integer.parseInt(jj.getString("price")) * Integer.parseInt(jsonObj.getString("offer")) /100);
                                scannedProductOfferPrice.setText(offerPrice+"");




                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                                SharedPreferences.Editor ed=sh.edit();

//                                ed.putString("ofrprice", String.valueOf(offerPrice));
                                ed.putString("productID",jj.getString("product_id"));
                                ed.putString("shopID",jj.getString("shop_id"));
                                ed.putString("productPrice",jj.getString("price"));
                                ed.commit();

                                String image = jj.getString("im");
//                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");
                                String url = "http://" + ip + ":4000" + image;
                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(scannedProductImage);//circle

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

//                params.put("login", lid);//passing to python
                params.put("contents", sh.getString("contents", ""));//passing to python
                params.put("login", sh.getString("lid", ""));//passing to python
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

