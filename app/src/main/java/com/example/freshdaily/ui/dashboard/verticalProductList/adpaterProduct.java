package com.example.freshdaily.ui.dashboard.verticalProductList;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.R;
import com.example.freshdaily.subscribeActitivty;
import com.google.android.material.snackbar.Snackbar;
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
    public void onBindViewHolder(@NonNull final holderProduct holder, int position) {
        final modelProduct modelProduct = modelProductList.get(position);
        Glide.with(this.context)
                .load(Uri.parse(dburl+modelProduct.getImage()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.productimage);
        //holder.productimage.setImageURI(Uri.parse(url+modelProduct.getImage()));
        holder.productcompany.setText(modelProduct.getCompany());
        holder.productqunitity.setText(modelProduct.getQuntity());
        holder.productprize.setText(modelProduct.getPrice().replace('_',' '));
        holder.productname.setText(modelProduct.getName().replace('_',' '));
        holder.subscribebutton.setText("Subscribe @"+modelProduct.getPrice());
        holder.subscribebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, subscribeActitivty.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("productid",modelProduct.getId());
                context.startActivity(intent);
            }
        });
        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"Add to cart successfully",Snackbar.LENGTH_SHORT).show();
                holder.addtocart.setText("Added");
                holder.addtocart.setEnabled(false);
            }
        });
        holder.bind(modelProduct);

    }

    @Override
    public int getItemCount() {
        return modelProductList.size();
    }
}
