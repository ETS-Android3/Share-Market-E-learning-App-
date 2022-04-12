package com.example.sharemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BSE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bse);

        WebView bscwebview = findViewById(R.id.bsewebview);

        bscwebview.getSettings().setJavaScriptEnabled(true);

        bscwebview.setWebViewClient(new WebViewClient());

        bscwebview.loadUrl("https://www.bseindia.com");
    }
}