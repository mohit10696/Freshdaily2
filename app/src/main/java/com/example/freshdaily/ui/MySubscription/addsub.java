package com.example.freshdaily.ui.MySubscription;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;
import com.example.freshdaily.cart.holdercart;

import java.util.List;

public class addsub extends RecyclerView.Adapter<viewmodelsub>  {

    List<modelsub> list;
    Context context;
    Activity activity;
    View view;

    public addsub(List<modelsub> list, Context context, Activity activity, View view) {
        this.list = list;
        this.context = context;
        this.activity = activity;
        this.view = view;
    }

    @NonNull
    @Override
    public viewmodelsub onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.subbox, parent, false);
        return new viewmodelsub(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewmodelsub holder, final int position) {
        final modelsub modelsub = list.get(position);
        final int num = position;
        holder.startdate.setText(modelsub.startdate.toString());
        holder.product_id.setText(modelsub.product_id.toString());
        holder.type.setText(modelsub.type.toString());
        holder.quantity.setText(modelsub.quantity.toString());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ListsDatabaseList theRemovedItem = list.get(position);
                // remove your item from data base
                list.remove(position);  // remove the item from list
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,list.size());
                // notify the adapter about the removed item
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
