package com.rijoksd.qrshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class customViewOffer extends BaseAdapter {
    String[] offerPercentage,offerPrice,offerStartDate,offerEndDate,productID,sId;
    private Context context;


    public customViewOffer(Context applicationContext, String[] offerPercentage, String[] offerPrice, String[] offerStartDate, String[] offerEndDate, String[] productID,String[] sId) {
        this.context = applicationContext;
        this.offerPercentage = offerPercentage;
        this.offerPrice = offerPrice;
        this.offerStartDate = offerStartDate;
        this.offerEndDate = offerEndDate;
        this.productID = productID;
        this.sId = sId;

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

        Button buyWithOfferPrice = (Button) gridView.findViewById(R.id.buyWithOffer);
        buyWithOfferPrice.setTag(i);


        buyWithOfferPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("productID",productID[pos]);
                ed.putString("shopID",sId[pos]);
                ed.putString("productPrice",offerPrice[pos]);
                ed.commit();
                Intent i=new Intent(context.getApplicationContext(),singleBuyQuantity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });



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
