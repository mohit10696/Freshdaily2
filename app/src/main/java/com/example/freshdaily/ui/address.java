package com.example.freshdaily.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.example.freshdaily.DashBord;
import com.example.freshdaily.Otp;
import com.example.freshdaily.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.google.gson.JsonObject;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class address extends AppCompatActivity {

    String[] SPINNERLIST = {"Vavol Post Office", "Akshardham BAPS Temple", "Infocity", "Kudasan","SRPF Parade Ground","Panchdev Temple"};
    public static final String mypreference = "userdetails";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    EditText address1,address2;
    String ADD,landmark2;
    ProgressDialog dialog;
    Button submit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        final MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);
        address1= findViewById(R.id.ad1);
        address2= findViewById(R.id.ad2);
        submit= findViewById(R.id.submit);
        dialog = new ProgressDialog(address.this);
        dialog.setMessage("Loading Please Wait");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        dialog.setCancelable(false);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                ADD = address1.getText().toString() + address2.getText().toString();
                landmark2 = materialDesignSpinner.getText().toString();
                Toast.makeText(getApplicationContext(),sharedpreferences.getString("id","0")+ADD+landmark2,Toast.LENGTH_LONG).show();
                apinterface api = retrofit.getapi();
                RequestBody number = RequestBody.create(MediaType.parse("multipart/form-data"),sharedpreferences.getString("mobile","0"));
                Call<Object> call2 = api.getUserLogin(number);
                call2.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        try {
                            JSONObject myResponse = new JSONObject(response.body().toString());
                            JSONObject jsonObject = myResponse.getJSONObject("data");
                            editor.putString("id",jsonObject.getString("userid"));
                            update();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Please try again",Toast.LENGTH_SHORT);
                    }
                });
            }
        });




    }

    private void update() {
        apinterface api = retrofit.getapi();
        final RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"),ADD);
        RequestBody landmark = RequestBody.create(MediaType.parse("multipart/form-data"),landmark2);

        RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"),sharedpreferences.getString("id","0"));
        Call<String> call = api.updateaddress(id,landmark,address);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                editor.putString("address",ADD);
                editor.putString("landmark",landmark2);
                editor.commit();
                dialog.dismiss();
                startActivity(new Intent(address.this, DashBord.class));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}