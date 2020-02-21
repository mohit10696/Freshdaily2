package com.example.freshdaily.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.freshdaily.R;
import com.example.freshdaily.searchActivity;

public class HomeFragment extends Fragment {
    View view;
    EditText search;
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        search = view.findViewById(R.id.searchTextbox);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), searchActivity.class));
            }
        });
        return view;
    }
}
