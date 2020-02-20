package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Otp extends AppCompatActivity {
    Button btn;
    EditText OTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Intent intent = getIntent();
        final String i = intent.getStringExtra(SignIn.EXTRA_TEXT);

        btn = findViewById(R.id.Continue);
        OTP = findViewById(R.id.otp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = OTP.getText().toString();
                if(temp.equals(i)){
                    Toast.makeText(getApplicationContext(),"OTP verify", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(Otp.this,DashBord.class);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
