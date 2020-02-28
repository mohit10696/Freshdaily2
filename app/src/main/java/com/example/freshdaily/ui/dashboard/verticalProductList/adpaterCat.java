package com.example.freshdaily.ui.dashboard.verticalProductList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.R;
import com.example.freshdaily.ui.dashboard.ProductList.product;

import java.util.List;


public class adpaterCat extends RecyclerView.Adapter<holderCat> {
    List<modelCat> modelCatList;
    Context context;

    public static String dburl = "http://18.213.183.26/assets/images/category/";
    public adpaterCat(List<modelCat> list, Context context) {
        this.modelCatList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holderCat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.categorybox, parent, false);
        return new holderCat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderCat holder, int position) {
        final modelCat modelCat2 = modelCatList.get(position);
        Glide.with(this.context)
                .load(Uri.parse(dburl+modelCat2.getImage()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.productimage);
        holder.productname.setText(modelCat2.getName().replace('_',' '));
        holder.bind(modelCat2);
        holder.productimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,modelCat2.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, product.class);
                intent.putExtra("category",modelCat2.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelCatList.size();
    }
}
