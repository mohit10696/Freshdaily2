package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.freshdaily.API.retrofit.getapi;

public class Otp extends AppCompatActivity {
    Button btn;
    static EditText OTP;
    ProgressDialog dialog;
    Toast myToast;
    private Context context;
    SliderLayout sliderLayout;
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;
    String number;
    public static final String mypreference = "userdetails";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        sliderLayout = findViewById(R.id.imageSlider1);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        btn = findViewById(R.id.Continue);
        btn.setEnabled(false);
        btn.setBackground(getDrawable(R.drawable.button2));
        OTP = (EditText) findViewById(R.id.otp);

        OTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!OTP.getText().toString().isEmpty())
                {
                    if(OTP.getText().toString().length()==4)
                    {
                        btn.setEnabled(true);
                        btn.setBackground(getDrawable(R.drawable.button));
                    }
                    else
                    {
                        btn.setEnabled(false);
                        btn.setBackground(getDrawable(R.drawable.button2));
                    }
                }
                else
                {
                    btn.setEnabled(false);
                    btn.setBackground(getDrawable(R.drawable.button2));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setSliderViews();


        Intent intent = getIntent();
        final String i = intent.getStringExtra(SignIn.EXTRA_TEXT);

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
                    Intent intent = getIntent();
                    number = intent.getStringExtra("number");
                    checkusertype(number);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid OTP", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void checkusertype(String number2) {
        apinterface api = retrofit.getapi();
        RequestBody numberparam = RequestBody.create(MediaType.parse("multipart/form-data"),number2);
        Call<Object> call = api.getUserLogin(numberparam);
       // Toast.makeText(getApplicationContext(),number,Toast.LENGTH_LONG).show();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                try {
                    Log.d("****", "onResponse: "+response.body());
                    JSONObject myResponse = new JSONObject(response.body().toString());
                    if(myResponse.getString("status").equals("valid")){
                        JSONObject jsonObject = myResponse.getJSONObject("data");
                        editor.putString("fname",jsonObject.getString("fname"));
                        editor.putString("lname",jsonObject.getString("lname"));
                        editor.putString("mobile",jsonObject.getString("mobile"));
                        editor.putString("email",jsonObject.getString("email"));
                        editor.putString("landmark",jsonObject.getString("landmark"));
                        editor.putString("address",jsonObject.getString("address"));
                        //editor.putString("subscriprion",jsonObject.getString("subscriprion"));
                        editor.commit();
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Welcome back: "+jsonObject.getString("fname"),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),DashBord.class));
                    }else{
                        Intent intent = new Intent(Otp.this,edit.class);
                        intent.putExtra("mobile",number);
                        dialog.dismiss();
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
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





