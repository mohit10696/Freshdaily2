package com.example.freshdaily.cart;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;

public class holdercart extends RecyclerView.ViewHolder {

    TextView name,quantity,price,category,no_of_quntity;
    Button minus,plus;
    ImageView image;
    public holdercart(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.pname);
        quantity = itemView.findViewById(R.id.volume1);
        price = itemView.findViewById(R.id.price1);
        category = itemView.findViewById(R.id.company1);
        image = itemView.findViewById(R.id.img1);
        minus = itemView.findViewById(R.id.minus1);
        plus = itemView.findViewById(R.id.pluse1);
        no_of_quntity = itemView.findViewById(R.id.quantity1);
    }


}
