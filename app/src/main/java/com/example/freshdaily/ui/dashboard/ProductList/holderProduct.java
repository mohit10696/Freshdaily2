package com.example.freshdaily.ui.dashboard.ProductList;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;

public class holderProduct extends RecyclerView.ViewHolder {
    ImageView productimage;
    TextView productname,productprize,productqunitity,productcompany;
    Button subscribebutton;

    public holderProduct(@NonNull View itemView) {
        super(itemView);
            productimage = itemView.findViewById(R.id.productimage);
            productname = itemView.findViewById(R.id.productname);
            productqunitity = itemView.findViewById(R.id.productweight);
            productcompany = itemView.findViewById(R.id.productcategory);
            productprize = itemView.findViewById(R.id.productprice);
            subscribebutton = itemView.findViewById(R.id.subscribebutton);

    }

    public void bind(modelProduct modelProduct) {
    }
}
