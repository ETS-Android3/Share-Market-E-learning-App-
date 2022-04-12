package com.example.sharemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class trading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading);

        WebView tradingview = findViewById(R.id.tradingview);

        tradingview.getSettings().setJavaScriptEnabled(true);

        tradingview.setWebViewClient(new WebViewClient());

        tradingview.loadUrl("https://www.tradingview.com");
    }
}