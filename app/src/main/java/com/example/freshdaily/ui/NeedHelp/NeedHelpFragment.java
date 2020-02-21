package com.example.freshdaily.ui.NeedHelp;

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
import com.example.freshdaily.ui.MyAccount.MyAccountViewModel;

public class NeedHelpFragment extends Fragment {

    private NeedHelpViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(NeedHelpViewModel.class);
        View root = inflater.inflate(R.layout.fragment_needhelp, container, false);
        final TextView textView = root.findViewById(R.id.text_need);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}