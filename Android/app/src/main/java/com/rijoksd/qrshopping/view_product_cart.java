package com.rijoksd.qrshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class view_product_cart extends AppCompatActivity {

    ListView list;
    SharedPreferences sh;
    String ip, url, url1, lid;
    TextView grandTotal;
    Button placeOrder;

    ImageView arrow;
    String[] productID, productImage,productName,productQuantity,productPrice,sId,billID,productShopName,totalPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_cart);
        list = (ListView) findViewById(R.id.list);
        grandTotal = (TextView) findViewById(R.id.totalAmount);
        placeOrder = (Button) findViewById(R.id.buyAll);

        arrow = (ImageView) findViewById(R.id.arrowLeft);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),UserHome.class);
                startActivity(i);
            }
        });


        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),cartPayment.class);
                startActivity(i);
            }
        });




        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ip", "");
        sh.getString("url", "");
        url = sh.getString("url", "") + "and_view_product_cart";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                String grnd_totl=jsonObj.getString("gt");
                                String ssid=jsonObj.getString("shopid");
                                grandTotal.setText(grnd_totl);
                                grandTotal.setTextColor(Color.RED);
                                SharedPreferences.Editor ed = sh.edit();
                                ed.putString("gnd_totl", grnd_totl);
                                ed.putString("shopid", ssid);
                                ed.commit();

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                productID = new String[js.length()];
                                productImage = new String[js.length()];
                                productName = new String[js.length()];
                                productQuantity = new String[js.length()];
                                productPrice = new String[js.length()];
                                sId = new String[js.length()];
                                billID = new String[js.length()];
                                productShopName = new String[js.length()];
                                totalPrice = new String[js.length()];



                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    //dbcolumn name in double quotes
                                    productID[i] = u.getString("product_id");
                                    productImage[i] = u.getString("image");
                                    productName[i] = u.getString("name");
                                    productQuantity[i] = u.getString("quantity");
                                    productPrice[i] = u.getString("price");
                                    sId[i] = u.getString("shop_id");
                                    billID[i] = u.getString("bill_id");
                                    productShopName[i] = u.getString("sn");
                                    totalPrice[i] = u.getString("total");

                                }
                                list.setAdapter(new custom_View_Product_cart(getApplicationContext(), productID, productImage, productName, productQuantity, productPrice,sId,billID,productShopName,totalPrice));//custom_view_service.xml and li is the listview object


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
                params.put("id", sh.getString("lid", ""));//passing to python
                params.put("shopid", sh.getString("shopID", ""));//passing to python
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
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),cartShop.class);
        startActivity(i);

    }
}