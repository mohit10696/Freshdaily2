package com.example.freshdaily.ui.dashboard.verticalProductList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;

import java.util.List;

public class adpaterProduct extends RecyclerView.Adapter<holderProduct> {
    List<modelProduct> modelProductList;

    public adpaterProduct(List<modelProduct> list) {
        this.modelProductList = list;
    }

    @NonNull
    @Override
    public holderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.productbox, parent, false);
        return new holderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderProduct holder, int position) {
        final modelProduct modelProduct = modelProductList.get(position);
        holder.bind(modelProduct);

    }

    @Override
    public int getItemCount() {
        return modelProductList.size();
    }
}
