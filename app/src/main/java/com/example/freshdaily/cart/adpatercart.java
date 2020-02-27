package com.example.freshdaily.cart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.DbAdapter;
import com.example.freshdaily.R;


import java.util.List;

public class adpatercart extends RecyclerView.Adapter<holdercart> {

    List<modelcart> list;
    Context context;
    Activity activity;
    View view;
    DbAdapter db;
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
    public void onBindViewHolder(@NonNull final holdercart holder, int position) {
        final modelcart modelcart = list.get(position);
        
        holder.name.setText(modelcart.getProductname());
        holder.category.setText(modelcart.getCompany());
        holder.quantity.setText(modelcart.getQuantity());
        holder.price.setText(modelcart.getPrice());
        db = new DbAdapter(context);
        db.open();
        Glide.with(this.context)
                .load(Uri.parse(dburl+modelcart.getImage()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp= Integer.parseInt(holder.no_of_quntity.getText().toString());
                if(temp == 11 ){
                    holder.no_of_quntity.setText("10");
                    Toast.makeText(activity,"You can't add more than 10 product.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    temp++;
                    holder.no_of_quntity.setText(String.valueOf(temp));
                }
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp= Integer.parseInt(holder.no_of_quntity.getText().toString());
                if(temp == 0 ){
                    db.delete(modelcart.getId());
                    //activity.startActivity(new Intent(context,adpatercart.class));
                }
                else
                {
                    temp--;
                    holder.no_of_quntity.setText(String.valueOf(temp));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
