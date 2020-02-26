package com.example.freshdaily.ui.dashboard.verticalProductList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.MainActivity;
import com.example.freshdaily.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class adpaterProduct extends RecyclerView.Adapter<holderProduct> {
    List<modelProduct> modelProductList;
    Context context;
    View view;
    public static String dburl = "http://18.213.183.26/assets/images/products/";
    public adpaterProduct(List<modelProduct> list, Context context) {
        this.modelProductList = list;
        this.context = context;
    }



    @NonNull
    @Override
    public holderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.productbox, parent, false);
        return new holderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderProduct holder, int position) {
        final modelProduct modelProduct = modelProductList.get(position);
        Glide.with(this.context)
                .load(Uri.parse(dburl+modelProduct.getImage()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.productimage);
        //holder.productimage.setImageURI(Uri.parse(url+modelProduct.getImage()));
        holder.productcompany.setText(modelProduct.getCompany());
        holder.productqunitity.setText(modelProduct.getQuntity());
        holder.productprize.setText(modelProduct.getPrice());
        holder.productname.setText(modelProduct.getName());
        holder.subscribebutton.setText("Subscribe @"+modelProduct.getPrice());
        holder.bind(modelProduct);

    }

    @Override
    public int getItemCount() {
        return modelProductList.size();
    }
}
