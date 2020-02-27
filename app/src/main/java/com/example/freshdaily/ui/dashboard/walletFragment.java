package com.example.freshdaily.ui.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Button button,addMoney1,addMoney2,addMoney3;
    public static walletFragment newInstance(String param1, String param2) {
        walletFragment fragment = new walletFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wallet, container, false);
        addmoney = (EditText) view.findViewById(R.id.addmoney);
        button = (Button) view.findViewById(R.id.addmoneybutton);
        textView = (TextView) view.findViewById(R.id.walletbal);
        addMoney1 = (Button) view.findViewById(R.id.addmoneybutton1);
        addMoney2 = (Button) view.findViewById(R.id.addmoneybutton2);
        addMoney3 = (Button) view.findViewById(R.id.addmoneybutton3);

        money = Integer.parseInt(textView.getText().toString());

        button.setEnabled(false);
        button.setBackground(getActivity().getDrawable(R.drawable.button2));
        button.setTextColor(Color.rgb(0, 183, 235));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money += Integer.parseInt(addmoney.getText().toString());
                textView.setText(String.valueOf(money));
            }
        });

        addMoney1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmoney.setText("500");
            }
        });


        addMoney2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmoney.setText("1000");
            }
        });

        addMoney3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmoney.setText("2000");
            }
        });

        addmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String temp = addmoney.getText().toString();
                        //Integer.parseInt(addmoney.getText().toString());
                if(!temp.isEmpty())
                {
                    if(Integer.parseInt(temp) > 10000 ) {
                        Toast.makeText(getActivity(), "Maximum recharge amount per transaction is ₹10000", Toast.LENGTH_LONG).show();
                        //addmoney.setText(temp.substring(0,5));
                        button.setEnabled(false);
                        button.setBackground(getActivity().getDrawable(R.drawable.button2));
                        button.setTextColor(Color.rgb(0, 183, 235));
                    }
                    else {
                        button.setEnabled(true);
                        button.setBackground(getActivity().getDrawable(R.drawable.button));
                        button.setTextColor(Color.rgb(255, 255, 255));
                    }
                }
                else
                {
                    button.setEnabled(false);
                    button.setBackground(getActivity().getDrawable(R.drawable.button2));
                    button.setTextColor(Color.rgb(0, 183, 235));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;

    }

}
