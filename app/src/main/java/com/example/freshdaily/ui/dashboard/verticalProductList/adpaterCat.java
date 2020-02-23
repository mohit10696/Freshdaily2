package com.example.freshdaily.ui.dashboard.verticalProductList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;

import java.util.List;

public class adpaterCat extends RecyclerView.Adapter<holderCat> {
    List<modelCat> modelCatList;

    public adpaterCat(List<modelCat> list) {
        this.modelCatList = list;
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
        holder.bind(modelCat2);

    }

    @Override
    public int getItemCount() {
        return modelCatList.size();
    }
}
