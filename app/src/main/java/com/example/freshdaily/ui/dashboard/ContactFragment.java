package com.example.freshdaily.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.freshdaily.R;
import com.zoho.salesiqembed.ZohoSalesIQ;

public class ContactFragment extends Fragment {
    View view;

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        return fragment;

    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_contact, container, false);

        final WebView web = (WebView)view.findViewById(R.id.web_chat);

        WebSettings setting=web.getSettings();
        setting.setJavaScriptEnabled(true);
        web.loadUrl("http://freshdaily1920.mywebcommunity.org/chat.html");

        hideNavigationBar();

        return view;


    }

    private void hideNavigationBar(){

        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
    }
}
