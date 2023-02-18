
package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
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
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView5);
        ImageView imageView = (ImageView) gridView.findViewById(R.id.imageView2);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView7);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView9);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView11);
        Button bt1 = (Button) gridView.findViewById(R.id.view_product_btn);

        bt1.setTag(i);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("shopID",shopID[pos]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),viewProduct.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });


        tv1.setTextColor(Color.parseColor("#fea116"));
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);

        tv1.setAllCaps(true);
        Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        tv1.setTypeface(typeface);

        tv1.setText(shopName[i]);
        tv2.setText(shopAddress[i]);
        tv3.setText(shopMail[i]);
        tv4.setText(shopPhone[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":4000" + shopImage[i];
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);//circle



//
        return gridView;


    }
}