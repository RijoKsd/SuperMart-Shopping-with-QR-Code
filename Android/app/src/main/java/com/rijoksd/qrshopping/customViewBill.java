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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.ParseError;
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

import kotlin.jvm.internal.PackageReference;

public class customViewBill extends BaseAdapter {
    String[] billDate,billAmount,billID,billShopName;
    private Context context;
    public customViewBill(Context applicationContext,String[] billShopName, String[] billDate, String[] billAmount, String[] billID) {
        this.context = applicationContext;
        this.billShopName = billShopName;
        this.billDate = billDate;
        this.billAmount = billAmount;
        this.billID = billID;
    }

    @Override
    public int getCount() {
        return billAmount.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_bill, null);//same class name

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView14);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView16);
        TextView tv3 = (TextView) gridView.findViewById(R.id.billShopName);
        Button viewAll = (Button) gridView.findViewById(R.id.viewBillProduct);

        viewAll.setTag(i);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("billID",billID[pos]);
                ed.putString("billAmount",billAmount[pos]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),viewBillProduct.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        tv3.setTextColor(Color.parseColor("#fea116"));
        tv2.setTextColor(Color.BLACK);
        tv1.setTextColor(Color.BLACK);
        tv3.setAllCaps(true);
        Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        tv3.setTypeface(typeface);

        tv1.setText(billDate[i]);
        tv2.setText(billAmount[i]);
        tv3.setText(billShopName[i]);
        return gridView;
    }
}
