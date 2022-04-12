package com.example.sharemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NSE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nse);


        WebView nsewebview = findViewById(R.id.nsewebview);

        nsewebview.getSettings().setJavaScriptEnabled(true);

        nsewebview.setWebViewClient(new WebViewClient());



            nsewebview.loadUrl("https://www.nseindia.com");
        }


}