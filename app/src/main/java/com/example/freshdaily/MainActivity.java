package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import static com.example.freshdaily.Otp.mypreference;

public class MainActivity extends AppCompatActivity {
    Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notification = Notification.getInstance();



        if(!getSharedPreferences(mypreference, Context.MODE_PRIVATE).getString("mobile","null").equals("null")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this,DashBord.class));
                }
            },2000);

        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,SignIn.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            },2000);
        }
    }
}
