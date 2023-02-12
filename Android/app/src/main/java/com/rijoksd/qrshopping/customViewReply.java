package com.rijoksd.qrshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class customViewReply extends BaseAdapter {
    private Context context;
    String[] complaintDate,complaint,replyDate,reply;

    public customViewReply(Context applicationContext, String[] complaintDate, String[] complaint, String[] replyDate, String[] reply) {
        this.context = applicationContext;
        this.complaintDate = complaintDate;
        this.complaint = complaint;
        this.replyDate = replyDate;
        this.reply = reply;

    }

    @Override
    public int getCount() {
        return complaint.length;
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
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView23);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView25);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView27);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView29);




        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);


        tv1.setText(complaintDate[i]);
        tv2.setText(complaint[i]);
        tv2.setText(replyDate[i]);
        tv3.setText(reply[i]);

//
        return gridView;
    }
}