package com.example.freshdaily;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freshdaily.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        final WebView web=(WebView)findViewById(R.id.web);

        WebSettings setting=web.getSettings();
        setting.setJavaScriptEnabled(true);
        web.loadUrl("http://freshdaily1920.mywebcommunity.org/FAQ.html");

    }
}
