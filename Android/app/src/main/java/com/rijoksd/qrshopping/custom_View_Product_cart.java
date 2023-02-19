package com.rijoksd.qrshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_View_Product_cart extends BaseAdapter {


    String[] productID,productImage,productName,productQuantity,productPrice,sId,billID ;
    private Context context;
    String url1,url;
    SharedPreferences sh;

    public custom_View_Product_cart(Context applicationContext, String[] productID, String[] productImage, String[] productName, String[] productQuantity, String[] productPrice, String[] sId, String[] billID ) {

        this.context = applicationContext;
        this.productID = productID;
        this.productImage = productImage;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.sId = sId;
        this.billID = billID;

    }

    @Override
    public int getCount() {
        return productName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custom_view_product_cart, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView17);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.productImage);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView21);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView19);

        ImageView delete = (ImageView) gridView.findViewById(R.id.delete);
        delete.setTag(i);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = (int) view.getTag();
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                sh.getString("ip","");
                url=sh.getString("url","")+"and_product_cart_delete";

                RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                        Toast.makeText(custom_View_Product_cart.this, "Product deleted", Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(context.getApplicationContext(),UserHome.class);
//                                                        i.putExtra("pid",id);
                                        context.startActivity(i);
                                    } else {
                                        Toast.makeText(context.getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context.getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context.getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {

                    //                value Passing android to python
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("bill", billID[pos]);//passing to python



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




        Button bt1 = (Button) gridView.findViewById(R.id.add_to_cart_btn);


        Button offerBtn = (Button) gridView.findViewById(R.id.offerBtn);
        offerBtn.setTag(i);
        offerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("productID",productID[pos]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),viewOffer.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });

        bt1.setTag(i);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("productID",productID[pos]);
                ed.putString("shopID",sId[pos]);
                ed.putString("productPrice",productPrice[pos]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),quantity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }
        });


        bt1.setTag(i);

//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos = (int) view.getTag();
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//
//                SharedPreferences.Editor ed=sh.edit();
//                ed.putString("productID",productID[pos]);
//                ed.commit();
//                Intent i=new Intent(context.getApplicationContext(),viewProduct.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//
//            }
//        });



        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);

//        tv1.setAllCaps(true);
        Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        tv1.setTypeface(typeface);

        //    Discount = Actual Price - (Actual Price * Discount_Rate/100)
//        tv4 = productPrice - (productPrice * productOffer/100);



        tv1.setText(productName[i]);
        tv2.setText(productQuantity[i]);
        tv3.setText(productPrice[i]);



        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":4000" + productImage[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle

//
        return gridView;

    }
}