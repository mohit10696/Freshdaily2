package com.example.freshdaily.ui.RateOurApp1;

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


public class ShopByCategoryFragment extends Fragment {

    private ShopByCategoryViewModel xyzViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        xyzViewModel =
                ViewModelProviders.of(this).get(ShopByCategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        final TextView textView = root.findViewById(R.id.text_logout);
        xyzViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}