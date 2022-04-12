package com.example.sharemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class investing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investing);

        WebView investingview = findViewById(R.id.investingview);

        investingview.getSettings().setJavaScriptEnabled(true);

        investingview.setWebViewClient(new WebViewClient());

        investingview.loadUrl("https://www.investing.com");
    }
}