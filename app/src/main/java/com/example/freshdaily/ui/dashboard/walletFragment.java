package com.example.freshdaily.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;
import com.example.freshdaily.searchActivity;
import com.example.freshdaily.ui.dashboard.verticalProductList.adpaterCat;
import com.example.freshdaily.ui.dashboard.verticalProductList.adpaterProduct;
import com.example.freshdaily.ui.dashboard.verticalProductList.modelCat;
import com.example.freshdaily.ui.dashboard.verticalProductList.modelProduct;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

public class walletFragment extends Fragment {
    SliderLayout sliderLayout;
    View view;
    EditText search;
    int money;
    EditText addmoney;
    TextView textView;
    Button button;
    public static walletFragment newInstance(String param1, String param2) {
        walletFragment fragment = new walletFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wallet, container, false);
        addmoney = view.findViewById(R.id.addmoney);
        button = view.findViewById(R.id.addmoneybutton);
        textView = view.findViewById(R.id.walletbal);
        money = Integer.parseInt(textView.getText().toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money += Integer.parseInt(addmoney.getText().toString());
                textView.setText(String.valueOf(money));
            }
        });


        return view;

    }

}
