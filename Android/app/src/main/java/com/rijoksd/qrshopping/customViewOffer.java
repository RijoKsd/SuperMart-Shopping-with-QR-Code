package com.rijoksd.qrshopping;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class customViewOffer extends BaseAdapter {
    String[] offerPercentage,offerPrice,offerStartDate,offerEndDate;
    private Context context;


    public customViewOffer(Context applicationContext, String[] offerPercentage, String[] offerPrice, String[] offerStartDate, String[] offerEndDate) {
        this.context = applicationContext;
        this.offerPercentage = offerPercentage;
        this.offerPrice = offerPrice;
        this.offerStartDate = offerStartDate;
        this.offerEndDate = offerEndDate;

    }

    @Override
    public int getCount() {
        return offerPercentage.length;
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
            gridView=inflator.inflate(R.layout. activity_custom_view_offer,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.offerPer);
        TextView tv2=(TextView)gridView.findViewById(R.id.offerPrice);
        TextView tv3=(TextView)gridView.findViewById(R.id.offerStart);
        TextView tv4=(TextView)gridView.findViewById(R.id.offerEnd);



        tv1.setTextColor(Color.BLACK);//color setting
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);



        tv1.setText(offerPercentage[i]);
        tv2.setText(offerPrice[i]);
        tv3.setText(offerStartDate[i]);
        tv4.setText(offerEndDate[i]);

        return gridView;

    }

}
