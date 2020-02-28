package com.example.freshdaily.ui.MySubscription;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.R;


public class viewmodelsub extends RecyclerView.ViewHolder {
    TextView product_id,quantity,startdate,type;
    ImageButton delete;
    public viewmodelsub(@NonNull View itemView) {
        super(itemView);
        product_id = itemView.findViewById(R.id.productname);
        quantity = itemView.findViewById(R.id.quantity);
        startdate = itemView.findViewById(R.id.startdate);
        type = itemView.findViewById(R.id.subtype);
        delete = itemView.findViewById(R.id.delete);
    }
}
