package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Random;

public class SignIn extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.freshdaily.EXTRA_TEXT";
    private Context context;
    SliderLayout sliderLayout;
    Button LOGIN;
    EditText et;
    TextView TV1, TV2;
    ProgressDialog dialog;
    public static String phoneNo, otp;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();

        LOGIN = findViewById(R.id.LOgin);
        et = findViewById(R.id.mobieno);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
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



    protected void sendSMSMessage() {


        phoneNo = et.getText().toString();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                Intent intent = new Intent(SignIn.this,Otp.class);
                startActivity(intent);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    Random rand = new Random();
                    otp = Integer.toString(rand.nextInt(9999));

                    //Toast.makeText(getApplicationContext(),"heo" , Toast.LENGTH_LONG).show();
                    if(phoneNo.matches("^[0-9]{10}")) {
                        dialog = new ProgressDialog(SignIn.this);
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
                        smsManager.sendTextMessage(phoneNo, null, "Your One Time Password for FreshDaily is : " + otp, null, null);
                        Toast.makeText(getApplicationContext(), "OTP Sent Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignIn.this, Otp.class);
                        //Bundle bundle = new Bundle();
                        intent.putExtra(EXTRA_TEXT, otp);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Enter Valid Number", Toast.LENGTH_LONG).show();
                        return;
                }
                }
            }
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






