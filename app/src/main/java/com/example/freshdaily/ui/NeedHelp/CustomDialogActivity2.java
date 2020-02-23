package com.example.freshdaily.ui.NeedHelp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.freshdaily.R;

public class CustomDialogActivity2 extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;

    WebView web;
    String url;
    public CustomDialogActivity2(Activity a,String url) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.url= url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog2);

        web=(WebView)findViewById(R.id.web);

        WebSettings setting=web.getSettings();
        setting.setJavaScriptEnabled(true);
        web.loadUrl(url);

    }

    @Override
    public void onClick(View v) {
        dismiss();
    }


}
