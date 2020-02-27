package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.freshdaily.ui.MySubscription.MySubscriptionFragment;

public class DialogForCardView extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;

    public DialogForCardView(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_for_card_view);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
