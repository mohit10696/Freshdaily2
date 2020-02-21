package com.example.freshdaily.ui.dashboard.verticalProductList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;

public class holderProduct extends RecyclerView.ViewHolder {
    ImageView productimage;
    TextView productname,productprize;


    public holderProduct(@NonNull View itemView) {
        super(itemView);
        productimage = itemView.findViewById(R.id.productimage);
        productname = itemView.findViewById(R.id.productname);
        productprize = itemView.findViewById(R.id.productprize);
    }

    public void bind(modelProduct modelProduct) {

    }
}
