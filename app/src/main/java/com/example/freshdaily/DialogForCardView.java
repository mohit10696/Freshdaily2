package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.ui.MySubscription.MySubscriptionFragment;

public class DialogForCardView extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    private String company,image,desc,name;
    Button ok ;
    public DialogForCardView(Activity a, String name, String company, String image, String desc) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.name=name;
        this.company=company;
        this.image = image;
        this.desc = desc;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_for_card_view);

        TextView tx = (TextView) findViewById(R.id.name);
        tx.setText(name);
        tx = (TextView) findViewById(R.id.cname);
        tx.setText(company);
        tx = (TextView) findViewById(R.id.desc);
        tx.setText(desc);
        ImageView iv = (ImageView) findViewById(R.id.img);
        String dburl = "http://18.213.183.26/assets/images/products/";
        Glide.with(c)
                .load(Uri.parse(dburl+image))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);

        ok= (Button)findViewById(R.id.ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }



    @Override
    public void onClick(View v) {
        dismiss();
    }
}
