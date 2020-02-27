package com.example.freshdaily;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshdaily.ui.NeedHelp.CustomDialogActivity2;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SignIn extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.freshdaily.EXTRA_TEXT";
    private long backPressedTime = 0;
    private Context context;
    SliderLayout sliderLayout;
    Button LOGIN;
    Button TERM;
    EditText et;
    TextView TV;
    ProgressDialog dialog;
    public static String phoneNo, otp;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    final int min = 1111;
    final int max = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        TV =(TextView)findViewById(R.id.textView2);

            TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogActivity2 cda = new CustomDialogActivity2(SignIn.this,"http://freshdaily1920.mywebcommunity.org/policy.html");
                cda.show();
            }
        });

        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();

        LOGIN = findViewById(R.id.LOgin);
        et = findViewById(R.id.mobieno);
        LOGIN.setBackground(getDrawable(R.drawable.button2));
        LOGIN.setEnabled(false);

        final CheckBox ch =(CheckBox) findViewById(R.id.check);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch.isChecked())
                    sendSMSMessage();
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!et.getText().toString().isEmpty())
                {
                    if(et.getText().toString().length()==10)
                    {
                        LOGIN.setBackground(getDrawable(R.drawable.button));
                        LOGIN.setEnabled(true);
                    }
                    else
                    {
                        LOGIN.setBackground(getDrawable(R.drawable.button2));
                        LOGIN.setEnabled(false);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);


            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://i.postimg.cc/zX1SfZ9f/png1.png");
                    break;
                case 1:
                    sliderView.setImageUrl("https://i.postimg.cc/cJJP58mP/png2.png");
                    break;
                case 2:
                    sliderView.setImageUrl("https://i.postimg.cc/857cDc10/png3.png");
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
                intent.putExtra("number",phoneNo);
                Toast.makeText(getApplicationContext(),phoneNo,Toast.LENGTH_LONG).show();
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
                    otp = String.format("%04d", rand.nextInt(10000));


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
                        intent.putExtra("number",phoneNo);
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
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {
            backPressedTime = t;
            Toast.makeText(this, "Press back again to close ", Toast.LENGTH_SHORT).show();
        } else {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
}






