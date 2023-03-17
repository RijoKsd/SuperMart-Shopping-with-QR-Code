//package com.rijoksd.qrshopping;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class scanQr extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_qr);
//    }
//}

package com.rijoksd.qrshopping;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class scanQr extends AppCompatActivity {

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
//    static final String ACTION_SCAN = "com.google.twmobile.client.android.SCAN";
    public static String contents = "";
    Button b11;
    TextView t1, t2;
    SharedPreferences sh;

    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);

        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
//                Uri uri = Uri.parse("market://search?q" + "com.google.twmobile.client.android");
//
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        try {
            if (Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        } catch (Exception e) {

        }

//        contents="32";
//        startActivity(new Intent(Scan_qr.this,Student_details.class));
        scanQR();
    }

    public void scanQR() {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(scanQr.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                contents = intent.getStringExtra("SCAN_RESULT");//hidden id from qr code is generated in 'contents'
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Toast.makeText(this, contents, Toast.LENGTH_SHORT).show();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(this);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("contents",contents);
                ed.commit();
                startActivity(new Intent(scanQr.this, viewProductInfoWhenScan.class));
//        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//       String ip = sh.getString("ip", "");
//      String  url = "http://" + ip + ":4000/and_view_product_with_qr";
//      String  lid = sh.getString("lid", "");
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                        try {
//                            JSONObject jsonObj = new JSONObject(response);
//                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                JSONObject jj = jsonObj.getJSONObject("data");
//                                Toast.makeText(scanQr.this, ""+jj, Toast.LENGTH_SHORT).show();
//
////                                scannedProductName.setText(jj.getString("n"));
////                                scannedProductDetails.setText(jj.getString("details"));
////                                scannedProductQuantity.setText(jj.getString("quantity"));
////                                scannedProductPrice.setText(jj.getString("price"));
////                                scannedProductOfferPrice.setText(jj.getString("price"));
////
//
////                                String image = jj.getString("im");
////                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
////                                String ip = sh.getString("ip", "");
////                                String url = "http://" + ip + ":4000" + image;
////                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(scannedProductImage);//circle
//
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                            }
//
//                        } catch (Exception e) {
//                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//
//            //                value Passing android to python
//            @Override
//            protected Map<String, String> getParams() {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                Map<String, String> params = new HashMap<String, String>();
//
////                params.put("login", lid);//passing to python
//                params.put("contents", sh.getString("contents", ""));//passing to python
//                params.put("login", sh.getString("lid", ""));//passing to python
//                return params;
//            }
//        };
//        int MY_SOCKET_TIMEOUT_MS = 100000;
//
//        postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(postRequest);

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), UserHome.class);
        startActivity(i);
    }
}

