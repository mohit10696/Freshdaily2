package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit extends AppCompatActivity {


    EditText firstname,lastname,email;
    TextView number;
    Button button;
    String num;
    ProgressDialog dialog;
    public static final String mypreference = "userdetails";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        firstname = findViewById(R.id.first);
        lastname = findViewById(R.id.last);
        email = findViewById(R.id.emailid);
        number = findViewById(R.id.mobile);
        button = findViewById(R.id.save);
        num = getIntent().getStringExtra("mobile");
        number.setText(num);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("fname",firstname.getText().toString());
                editor.putString("lname",lastname.getText().toString());
                editor.putString("mobile",num);
                editor.putString("email",email.getText().toString());
                apinterface api = retrofit.getapi();
                RequestBody fname = RequestBody.create(MediaType.parse("multipart/form-data"),firstname.getText().toString());
                RequestBody lname = RequestBody.create(MediaType.parse("multipart/form-data"),lastname.getText().toString());
                RequestBody mail = RequestBody.create(MediaType.parse("multipart/form-data"),email.getText().toString());
                RequestBody number = RequestBody.create(MediaType.parse("multipart/form-data"),num);
                Call<String> call = api.adduser(fname,lname,number,mail);
                dialog = new ProgressDialog(edit.this);
                dialog.setMessage("Loading Please Wait");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
                dialog.setCancelable(false);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        startActivity(new Intent(edit.this,DashBord.class));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        startActivity(new Intent(edit.this,DashBord.class));
                    }
                });
            }
        });

    }
}
