package com.example.freshdaily.ui.MyAccount;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.freshdaily.IOnBackPressed;
import com.example.freshdaily.R;
import com.example.freshdaily.edit;
import com.example.freshdaily.ui.address;
import com.example.freshdaily.ui.dashboard.HomeFragment;
import com.zoho.livechat.android.ui.fragments.BaseFragment;

import java.util.List;

public class MyAccountFragment extends Fragment implements IOnBackPressed {
    Button btn;
    CardView car;
    TextView name,address,number,email;
    private MyAccountViewModel homeViewModel;
    public static final String mypreference = "userdetails";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(MyAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myaccount, container, false);
        sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        car = root.findViewById(R.id.card2);
        btn = root.findViewById(R.id.EDIT);
        name = root.findViewById(R.id.customername);
        address = root.findViewById(R.id.Address);
        number = root.findViewById(R.id.number);
        email = root.findViewById(R.id.mail);
        name.setText(sharedpreferences.getString("fname","User")+" "+sharedpreferences.getString("lname","User"));
        address.setText(sharedpreferences.getString("address","Add address"));
        number.setText(sharedpreferences.getString("mobile","User"));
        email.setText(sharedpreferences.getString("email","add email"));
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), com.example.freshdaily.ui.address.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), edit.class);
                startActivity(intent);

            }
        });

        return root;
    }


    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }
    }



