//package com.rijoksd.qrshopping;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.navigation.ui.AppBarConfiguration;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.ActivityNotFoundException;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.net.Uri;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.view.Menu;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.android.material.navigation.NavigationView;
//import com.squareup.picasso.Picasso;
//
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Scan extends AppCompatActivity {
//    private AppBarConfiguration mAppBarConfiguration;
//    private ActivityUserHomeBinding binding;
//
//
//    SharedPreferences sh;
//    String url, lid,ip,cid,tid;
//    String[] productID;
//    String[] productImage;
//    String[] productName;
//    String productQuantity;
//    String productPrice;
//    String[] productDetails;
//    String productOfferPrice;
//
//    TextView pro, sho, pri, de;
//
//    Button btscan,b,b1;
//
//    Button addToCart;
//    TextView qrProductName,qrProductDetails,qrProductQuantity,qrProductPrice,qrProductOfferPrice;
//    ImageView qrProductImage;
//    String contents="";
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityUserHomeBinding.inflate(getLayoutInflater());
//
//        setContentView(R.layout.activity_scan);
//        setSupportActionBar(binding.appBarUserHome.toolbar);
//
//
//        DrawerLayout drawer = binding.drawerLayout;
//        NavigationView navigationView = binding.navView;
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
//                .setOpenableLayout(drawer)
//                .build();
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_user_home);
////        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
////        NavigationUI.setupWithNavController(navigationView, navController);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        addToCart = (Button) findViewById(R.id.QrAddToCart);
//
//        qrProductName = (TextView) findViewById(R.id.QrProductName);
//        qrProductDetails = (TextView) findViewById(R.id.QrProductDetails);
//        qrProductQuantity = (TextView) findViewById(R.id.QrProductQuantity);
//        qrProductPrice = (TextView) findViewById(R.id.QrProductPrice);
//        qrProductOfferPrice = (TextView) findViewById(R.id.QrProductOfferPrice);
//        qrProductImage = (ImageView) findViewById(R.id.QrProductImage);
//
//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor ed = sh.edit();
//                ed.putString("tid", contents);
//                ed.putString("cid", cid);
//
//
//                ed.commit();
//
//
//
//                Intent i = new Intent(getApplicationContext().getApplicationContext(),add_cart.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getApplicationContext().startActivity(i);
//
//            }
//        });
//
////        b1=(Button) findViewById(R.id.button13);
////        b1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
////                SharedPreferences.Editor ed = sh.edit();
////                ed.putString("tid", contents);
////                ed.putString("cid", cid);
////
////
////                ed.commit();
////
////
////
////                Intent i = new Intent(getApplicationContext().getApplicationContext(),book.class);
////                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                getApplicationContext().startActivity(i);
////
////            }
////        });
//
//
//
//        btscan = (Button) findViewById(R.id.buttonHereBtn);
//
//        btscan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scanQR(v);
//            }
//        });
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.user_home, menu);
//        return true;
//    }
//
//
////    @Override
////    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////        int id = item.getItemId();
////
////        if (id == R.id.nav_home) {
////            Intent i = new Intent(getApplicationContext(), view_shop.class);
////            startActivity(i);
////        } else if (id == R.id.nav_gallery) {
////            Intent i = new Intent(getApplicationContext(), view_billmaster.class);
////            startActivity(i);
////        }
////        else if (id == R.id.nav_slideshow) {
////            Intent i = new Intent(getApplicationContext(), login.class);
////            startActivity(i);
////        }
////
////
////
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
////        drawer.closeDrawer(GravityCompat.START);
////        return true;
////    }
//    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
//
//    public void scanQR(View v) {
//        try {
//            Toast.makeText(getApplicationContext(), "Hiii", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(ACTION_SCAN);
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//            startActivityForResult(intent, 0);
//        } catch (ActivityNotFoundException anfe) {
//            showDialog(Scan.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
//        }
//    }
//
//    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
//        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
//        downloadDialog.setTitle(title);
//        downloadDialog.setMessage(message);
//        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                try {
//                    act.startActivity(intent);
//                } catch (ActivityNotFoundException anfe) {
//
//                }
//            }
//        });
//        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialogInterface, int i) {
//            }
//        });
//        return downloadDialog.show();
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                contents = data.getStringExtra("SCAN_RESULT");
//                String format = data.getStringExtra("SCAN_RESULT_FORMAT");
//
//                /////
//                sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                ip = sh.getString("ip", "");
//                url = "http://" + ip + ":4000/andviewpro";
//                lid = sh.getString("lid", "");
//
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
////                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                                try {
//                                    JSONObject jsonObj = new JSONObject(response);
////                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
////                                        JSONObject u = jsonObj.getJSONObject("data");
////
////                                        //dbcolumn name in double quotes
////                                        productID[i] = u.getString("product_id");
////                                        productImage[i] = u.getString("image");
////                                        productName[i] = u.getString("name");
////                                        productDetails[i] = u.getString("details");
////                                        productQuantity[i] = u.getString("quantity");
////                                        productPrice[i] = u.getString("price");
////                                        productOfferPrice[i] = u.getString("offer");
//////
////                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
////                                        String ip = sh.getString("ip", "");
////                                        String url = "http://" + ip + ":4000" + qrProductImage;
////                                        Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(qrProductImage);//circle
////
////                                    }
//                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                        JSONObject jj = jsonObj.getJSONObject("data");
//
////                                        productID.setText(jj.getString("product_id"));
////                                        productImage.setText(jj.getString("name"));
//                                        productName.setText(jj.getString("name"));
//                                        productDetails.setText(jj.getString("details"));
//                                        productQuantity=setText(jj.getString("quantity"));
//                                        productPrice=setText(jj.getString("price"));
//                                        productOfferPrice=setText(jj.getString("offer"));
//                                        String image = jj.getString("image");
//                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                        String ip = sh.getString("ip", "");
//                                        String url = "http://" + ip + ":4000" + image;
//                                        Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(qrProductImage);//circle
//
//                                    }
//                                    else {
//                                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                                    }
//
//                                } catch (Exception e) {
//                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                ) {
//
//                    //                value Passing android to python
//                    @Override
//                    protected Map<String, String> getParams() {
//                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                        ip = sh.getString("ip", "");
//                        url = "http://" + ip + ":4000/andviewpro";
//                        lid = sh.getString("lid", "");
//
//                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                                new Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
////                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                                        try {
//                                            JSONObject jsonObj = new JSONObject(response);
////                                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
////                                                JSONObject u = jsonObj.getJSONObject("data");
////
////                                                productID[i] = u.getString("product_id");
//////                                                productImage[i] = u.getString("image");
////                                                productName[i] = u.getString("name");
////                                                productDetails[i] = u.getString("details");
////                                                productQuantity[i] = u.getString("quantity");
////                                                productPrice[i] = u.getString("price");
////                                                productOfferPrice[i] = u.getString("offer");
////                                                String image = u.getString("image");
////                                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
////                                                String ip = sh.getString("ip", "");
////                                                String url = "http://" + ip + ":4000" + image;
////                                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(qrProductImage);//circle
////
////                                            }
//
//                                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                                JSONObject jj = jsonObj.getJSONObject("data");
//
//
//                                                productName.setText(jj.getString("name"));
//                                                productDetails.setText(jj.getString("details"));
//                                                productQuantity=setText(jj.getString("quantity"));
//                                                productPrice=setText(jj.getString("price"));
//                                                productOfferPrice=setText(jj.getString("offer"));
//                                                String image = jj.getString("image");
//                                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                                String ip = sh.getString("ip", "");
//                                                String url = "http://" + ip + ":4000" + image;
//                                                Picasso.with(getApplicationContext()).load(url).transform(new CircleTransform()).into(qrProductImage);//circle
//
//                                            }
//                                            else {
//                                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                                            }
//
//                                        } catch (Exception e) {
//                                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                },
//                                new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        // error
//                                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                        ) {
//
//                            //                value Passing android to python
//                            @Override
//                            protected Map<String, String> getParams() {
//                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                Map<String, String> params = new HashMap<String, String>();
//
//                                params.put("tid", contents);//passing to python
//
//                                return params;
//                            }
//                        };
//                        int MY_SOCKET_TIMEOUT_MS = 100000;
//
//                        postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                MY_SOCKET_TIMEOUT_MS,
//                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                        requestQueue.add(postRequest);
//
//
//                        return params;
//                    }
//                };
//                int MY_SOCKET_TIMEOUT_MS = 100000;
//
//                postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        MY_SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                requestQueue.add(postRequest);
//
//
//
//            }
//        }
//    }
//
//
//}