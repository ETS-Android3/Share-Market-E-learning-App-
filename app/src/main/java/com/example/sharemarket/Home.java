package com.example.sharemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    CardView nsebutton, bsebutton, investingbutton, tradingbutton;
    BottomNavigationView bottomNavigationView;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nsebutton = findViewById(R.id.nse);
        bsebutton = findViewById(R.id.bse);
        investingbutton= findViewById(R.id.investingviewcard);
        tradingbutton= findViewById(R.id.tradingviewcard);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Home");


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        return true;

                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(),course.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ebook:
                        startActivity(new Intent(getApplicationContext(),Ebook.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(),shorts.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.shortsss:
                        startActivity(new Intent(getApplicationContext(),shorts.class));
                        overridePendingTransition(0,0);
                        return true;




                }

                return false;
            }
        });

        bsebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,BSE.class);
                startActivity(intent);
            }
        });
        nsebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,NSE.class);
                startActivity(intent);
            }
        });
 investingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,investing.class);
                startActivity(intent);
            }
        });
 tradingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,trading.class);
                startActivity(intent);
            }
        });


    }



}