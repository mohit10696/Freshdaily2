package com.example.freshdaily.cart;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.R;


import java.util.List;

public class adpatercart extends RecyclerView.Adapter<holdercart> {

    List<modelcart> list;
    Context context;
    Activity activity;
    View view;
    public static String dburl = "http://18.213.183.26/assets/images/products/";
    public adpatercart(List<modelcart> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public holdercart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cartbox, parent, false);
        return new holdercart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holdercart holder, int position) {
        final modelcart modelcart = list.get(position);
        
        holder.name.setText(modelcart.getProductname());
        holder.category.setText(modelcart.getCompany());
        holder.quantity.setText(modelcart.getQuantity());
        holder.price.setText(modelcart.getPrice());
        Glide.with(this.context)
                .load(Uri.parse(dburl+modelcart.getImage()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
