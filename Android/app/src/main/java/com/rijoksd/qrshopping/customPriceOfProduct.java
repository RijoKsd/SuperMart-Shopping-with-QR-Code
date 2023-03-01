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
import android.widget.TextView;

public class customPriceOfProduct extends BaseAdapter {

    String[] amt;
    private Context context;
    public customPriceOfProduct(Context applicationContext,String[] amt) {
        this.context = applicationContext;
        this.amt = amt;
    }

    @Override
    public int getCount() {
        return amt.length;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_price_of_product,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.totalProductsAmount);
        Button b=(Button) gridView.findViewById(R.id.payNowBtn);
        b.setTag(i);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=(int) view.getTag();
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("total",amt[pos]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),payment.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });



        tv1.setTextColor(Color.BLACK);


        tv1.setText(amt[i]);
        return gridView;
    }
}