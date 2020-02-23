package com.example.freshdaily.ui.NeedHelp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freshdaily.R;
import com.example.freshdaily.ui.MyAccount.MyAccountViewModel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NeedHelpFragment extends Fragment {

    private MyAccountViewModel homeViewModel;
    Button call,mail,faq,terms,privacy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(MyAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_needhelp, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        call=(Button) root.findViewById(R.id.call);
        mail=(Button) root.findViewById(R.id.mail);
        faq = (Button) root.findViewById(R.id.faq);
        terms = (Button) root.findViewById(R.id.terms);
        privacy = (Button) root.findViewById(R.id.priv);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mobileIntent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel: +917016894202"));
                startActivity(Intent.createChooser(mobileIntent, "Select dialer..."));
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String[] TO={"freshdaily1920@gmail.com"};
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,TO );
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Fresh Daily");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://freshdaily1920.mywebcommunity.org/FAQ.html")));
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://freshdaily1920.mywebcommunity.org/policy.html")));
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://freshdaily1920.mywebcommunity.org/privacy_policy.html")));
            }
        });


        return root;
    }
}