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

import com.squareup.picasso.Picasso;

public class customViewProduct extends BaseAdapter {


    String[] productID,productImage,productName,productQuantity,productPrice,sId,productDetails ;
    private Context context;

    public customViewProduct(Context applicationContext, String[] productID, String[] productImage, String[] productName,String[] productDetails, String[] productQuantity, String[] productPrice, String[] sId ) {

        this.context = applicationContext;
        this.productID = productID;
        this.productImage = productImage;
        this.productName = productName;
        this.productDetails = productDetails;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.sId = sId;


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
        TextView tv1 = (TextView) gridView.findViewById(R.id.billProduct);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.productImage);
        TextView tv2 = (TextView) gridView.findViewById(R.id.billQuantity);
        TextView tv3 = (TextView) gridView.findViewById(R.id.billPrice);
        TextView tv4 = (TextView) gridView.findViewById(R.id.billDetails);



        Button bt1 = (Button) gridView.findViewById(R.id.buyProduct);

        Button buySingleProduct = (Button) gridView.findViewById(R.id.buySinglePro);
        buySingleProduct.setTag(i);
        buySingleProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("productID",productID[pos]);
                ed.putString("shopID",sId[pos]);
                ed.putString("productPrice",productPrice[i]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),singleBuyQuantity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


        Button offerBtn = (Button) gridView.findViewById(R.id.offerBtn);
        offerBtn.setTag(i);
        offerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("productID",productID[pos]);
                ed.putString("pqty",productQuantity[pos]);
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


        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);

//        tv1.setAllCaps(true);
        Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        tv1.setTypeface(typeface);

        //    Discount = Actual Price - (Actual Price * Discount_Rate/100)
//        tv4 = productPrice - (productPrice * productOffer/100);



        tv1.setText(productName[i]);
        tv2.setText(productQuantity[i]);
        tv3.setText(productPrice[i]);
        tv4.setText(productDetails[i]);



        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":4000" + productImage[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle

//
        return gridView;

    }
}