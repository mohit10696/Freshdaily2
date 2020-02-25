package com.example.freshdaily.ui.dashboard.verticalProductList;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;

import java.util.List;

public class adpaterProduct extends RecyclerView.Adapter<holderProduct> {
    List<modelProduct> modelProductList;
    public static String url = "http://18.213.183.26/assets/images/products/";
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
//        holder.productimage.setImageURI(Uri.parse(url+modelProduct.getImage()));
//        holder.productcompany.setText(modelProduct.getCompany());
//        holder.productqunitity.setText(modelProduct.getQuntity());
//        holder.productprize.setText(modelProduct.getPrice());
//        holder.productname.setText(modelProduct.getName());
//        holder.bind(modelProduct);

    }

    @Override
    public int getItemCount() {
        return modelProductList.size();
    }
}
