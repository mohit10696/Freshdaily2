package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Otp extends AppCompatActivity {
    Button btn;
    EditText OTP;
    ProgressDialog dialog;
    Toast myToast;
    private Context context;
    SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        sliderLayout = findViewById(R.id.imageSlider1);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();


        Intent intent = getIntent();
        final String i = intent.getStringExtra(SignIn.EXTRA_TEXT);

        btn = findViewById(R.id.Continue);
        OTP = findViewById(R.id.otp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = OTP.getText().toString();
                if(temp.equals(i)){
                    dialog = new ProgressDialog(Otp.this);
                    dialog.setMessage("Loading Please Wait");
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.show();
                    dialog.setCancelable(false);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            dialog.dismiss();
                        }
                    }).start();
                    Toast.makeText(getApplicationContext(),"OTP Detected", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(Otp.this,DashBord.class);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid OTP", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);


            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://i.postimg.cc/L5xzt3pL/png1.png");
                    break;
                case 1:
                    sliderView.setImageUrl("https://i.postimg.cc/jdt6BB1v/png3-2.png");
                    break;
                case 2:
                    sliderView.setImageUrl("https://i.postimg.cc/V6SmRYn6/png2-2.png");
                    break;

            }
            sliderLayout.addSliderView(sliderView);
        }
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage("Are you sure you want to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
