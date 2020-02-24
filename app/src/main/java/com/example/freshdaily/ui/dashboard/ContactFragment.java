package com.example.freshdaily.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        Fragment newFragment = new ContactFragment();
        //Fragment newFragment = ContactFragment.newInstance();

        view =  inflater.inflate(R.layout.fragment_contact, container, false);
        return view;
        //ZohoSalesIQ.init(  , "45xrGyIS2DrHrSx8LPVYaefKyTIzb8ApgFTT6rbYnyJOhOhAkZQdt2uYKKrBFIAb_in", "VXYedrQX8Skkl8hBUChWx%2FThmma%2FSCn9zV4pxPsw3Vyl91l4IjM8fpwgsYjLNXW8BZS2ind7lJppFjtNO685sskC%2B7h%2Fw%2FZUuMUmoCX%2BuTAiUtoObo%2BxyK4o5sR%2BW23AuOYA1YNKpgCrqkBaCMosdxvJqLssWmb4vAoMmiENoA4%3D");


    }
}
