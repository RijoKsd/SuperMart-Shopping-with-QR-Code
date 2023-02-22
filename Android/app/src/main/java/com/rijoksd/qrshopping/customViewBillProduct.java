package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class customViewBillProduct extends BaseAdapter {
    String[] billProductName, quantityBill, priceBill, totalBill ;
    private Context context;
    Button b1;

    public customViewBillProduct(Context applicationContext, String[] billProductName, String[] quantityBill, String[] priceBill, String[] totalBill ) {

        this.context = applicationContext;
        this.billProductName = billProductName;
        this.quantityBill = quantityBill;
        this.priceBill = priceBill;
        this.totalBill = totalBill;

    }

    @Override
    public int getCount() {
        return billProductName.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_bill_product, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.billProduct);
        TextView tv2 = (TextView) gridView.findViewById(R.id.billQuantity);
        TextView tv3 = (TextView) gridView.findViewById(R.id.billPrice);
        TextView tv4 = (TextView) gridView.findViewById(R.id.billTotal);




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);

//        tv1.setAllCaps(true);
//        Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
//        tv1.setTypeface(typeface);

        tv1.setText(billProductName[i]);
        tv2.setText(quantityBill[i]);
        tv3.setText(priceBill[i]);
        tv4.setText(totalBill[i]);

//        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//        String ip = sh.getString("ip", "");
//        String url = "http://" + ip + ":4000" + shopImage[i];
//        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle



//
        return gridView;


    }
}
