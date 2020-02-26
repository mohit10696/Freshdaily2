package com.example.freshdaily.ui.dashboard.verticalProductList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;

public class holderCat extends RecyclerView.ViewHolder {
    ImageView productimage;
    TextView productname;


    public holderCat(@NonNull View itemView) {
        super(itemView);
        productimage = itemView.findViewById(R.id.catimage);
        productname = itemView.findViewById(R.id.catname);
    }

    public void bind(modelCat modelProduct) {

    }
}
