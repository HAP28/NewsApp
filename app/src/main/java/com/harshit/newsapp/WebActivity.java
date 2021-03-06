package com.harshit.newsapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends Activity {

    private WebView webView;
    String url;
    private ProgressDialog progressDialog;
    Activity activity;

    @SuppressLint("SetJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        activity = this;
        progressDialog = ProgressDialog.show(this,"Loading","Please wait...",true);
        progressDialog.setCancelable(false);


        webView = findViewById(R.id.web_view);
        url = getIntent().getStringExtra("url");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressDialog.show();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view,final String url) {
                progressDialog.dismiss();
            }
        });

        webView.loadUrl(url);
    }
}