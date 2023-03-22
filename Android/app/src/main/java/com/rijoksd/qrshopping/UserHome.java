package com.rijoksd.qrshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.rijoksd.qrshopping.databinding.ActivityUserHomeBinding;

public class UserHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityUserHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarUserHome.toolbar);
        binding.appBarUserHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();\
                Toast.makeText(UserHome.this, "Scan the product using SCAN HERE button", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), viewProductInfoWhenScan.class);
                startActivity(i);
            }
        });
        Toolbar toolbar=findViewById(R.id.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow).setOpenableLayout(drawer).build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_user_home);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), about.class);
            startActivity(i);
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_user_home);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//
//                || super.onSupportNavigateUp();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_view_profile) {
            Intent i = new Intent(getApplicationContext(), viewProfile.class);
            startActivity(i);
        } else if (id == R.id.nav_shop) {
            Intent i = new Intent(getApplicationContext(), viewShop.class);
            startActivity(i);
        } else if (id == R.id.nav_scan) {
            Intent i = new Intent(getApplicationContext(), scanQr.class);
            startActivity(i);
        } else if (id == R.id.nav_bill) {
            Intent i = new Intent(getApplicationContext(), ViewBill.class);
            startActivity(i);
        } else if (id == R.id.nav_feedback) {
            Intent i = new Intent(getApplicationContext(), sendFeedback.class);
            startActivity(i);
        } else if (id == R.id.nav_complaint) {
            Intent i = new Intent(getApplicationContext(), viewReply.class);
            startActivity(i);
        } else if (id == R.id.nav_logout) {
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        } else if (id == R.id.nav_cart) {
            Intent i = new Intent(getApplicationContext(), cartShop.class);
            startActivity(i);
        }

        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), UserHome.class);
        startActivity(i);
    }
}