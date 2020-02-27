package com.example.freshdaily.ui.Logout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freshdaily.R;
import com.example.freshdaily.SignIn;

public class RateOurAppFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rateourapp, container, false);
        String mypreference = "userdetails";
        SharedPreferences sharedpreferences;
        SharedPreferences.Editor editor;
        sharedpreferences = getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        getActivity().startActivity(new Intent(getContext(), SignIn.class));
        return root;
    }
}