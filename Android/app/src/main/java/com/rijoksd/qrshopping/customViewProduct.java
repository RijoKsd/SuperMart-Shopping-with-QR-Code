package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class customViewProduct extends BaseAdapter {


    String[] productID,productImage,productName,productQuantity,productPrice ;
    private Context context;

    public customViewProduct(Context applicationContext, String[] productID, String[] productImage, String[] productName, String[] productQuantity, String[] productPrice ) {

        this.context = applicationContext;
        this.productID = productID;
        this.productImage = productImage;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;

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
            gridView = inflator.inflate(R.layout.activity_custom_view_product, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView17);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView3);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView21);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView19);


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



        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


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