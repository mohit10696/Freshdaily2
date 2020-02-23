package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.SmsMessage;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Locale;

public class Otp extends AppCompatActivity {
    Button btn;
    static EditText OTP;
    ProgressDialog dialog;
    Toast myToast;
    private Context context;
    SliderLayout sliderLayout;
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        sliderLayout = findViewById(R.id.imageSlider1);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        OTP = (EditText) findViewById(R.id.otp);

        setSliderViews();


        Intent intent = getIntent();
        final String i = intent.getStringExtra(SignIn.EXTRA_TEXT);

        btn = findViewById(R.id.Continue);
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
        super.onBackPressed();
        //Toast.makeText(this,"back key is pressed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String message = intent.getStringExtra("message");
                OTP.setText(message);
                //Do whatever you want with the code here
            }
        }
    };
}





