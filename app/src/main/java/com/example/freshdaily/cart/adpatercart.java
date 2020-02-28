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
    int amount;

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
    public void onBindViewHolder(@NonNull final holdercart holder, final int position) {
        final modelcart modelcart = list.get(position);
        
        holder.name.setText(modelcart.getProductname());
        holder.category.setText(modelcart.getCompany());
        holder.quantity.setText(modelcart.getQuantity());
        holder.price.setText(modelcart.getPrice());
        holder.no_of_quntity.setText("1");
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
                if(temp == 10 ){
                    holder.no_of_quntity.setText(String.valueOf(temp));
                    Toast.makeText(context,"You can't add more than 10 product.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    temp++;
                    holder.no_of_quntity.setText(String.valueOf(temp));
                    modelcart.setCount(holder.no_of_quntity.getText().toString());
                    //amount = Integer.parseInt(holder.no_of_quntity.getText().toString())*Integer.parseInt(holder.price.getText().toString());
                }
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp= Integer.parseInt(holder.no_of_quntity.getText().toString());
                if(temp == 1 ){
                    db.delete(modelcart.getId());
                    Toast.makeText(view.getContext(),"temp",Toast.LENGTH_LONG).show();
                    //context.getApplicationContext();
//                    activity.finish();
//                    activity.startActivity(new Intent(context,cart.class));
                    //activity.startActivity(new Intent(context,adpatercart.class));
                    list.remove(position);//here only i tried to remove the row in custom listview
//                    itemprice.remove(position);
//                    itemimage.remove(position);
                    notifyDataSetChanged();
                    

                }
                else
                {
                    temp--;
                    holder.no_of_quntity.setText(String.valueOf(temp));
                    modelcart.setCount(holder.no_of_quntity.getText().toString());
                  //  amount = Integer.parseInt(holder.no_of_quntity.getText().toString())*Integer.parseInt(holder.price.getText().toString());
                }
            }
        });

       // amount = Integer.parseInt(holder.no_of_quntity.getText().toString())*Integer.parseInt(holder.price.getText().toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
