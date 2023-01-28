
package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
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

public class customViewShop extends BaseAdapter {
    String[] shopID, shopName, shopAddress, shopMail, shopPhone, shopImage;
    private Context context;
    Button b1;

    public customViewShop(Context applicationContext, String[] shopID, String[] shopName, String[] shopAddress, String[] shopMail, String[] shopPhone, String[] shopImage) {

        this.context = applicationContext;
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopMail = shopMail;
        this.shopPhone = shopPhone;
        this.shopImage = shopImage;
    }

    @Override
    public int getCount() {
        return shopID.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_shop, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView27);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView5);
        TextView tv2 = (TextView) gridView.findViewById(R.id.t51);
        TextView tv3 = (TextView) gridView.findViewById(R.id.t61);


        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(uk[i]);
        tv2.setText(post[i]);
        tv3.setText(date[i]);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":5000" + ik[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle

//
        return gridView;


    }
}