package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.example.freshdaily.ui.MyAccount.MyAccountFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
    ImageButton back;
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
        back = (ImageButton) findViewById(R.id.back);
        button.setEnabled(false);
        button.setBackground(getDrawable(R.drawable.button2));
        button.setTextColor(Color.rgb(0, 183, 235));


        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enabeButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enabeButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enabeButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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


    void enabeButton()
    {
        if(firstname.getText().toString().isEmpty() || lastname.getText().toString().isEmpty() || email.getText().toString().isEmpty())
        {
            button.setTextColor(Color.rgb(0, 183, 235));
            button.setEnabled(false);
            button.setBackground(getDrawable(R.drawable.button2));
        }
        else
        {
            button.setTextColor(Color.rgb(255, 255, 255));
            button.setEnabled(true);
            button.setBackground(getDrawable(R.drawable.button));
        }
    }


}
