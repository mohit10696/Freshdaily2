package com.example.freshdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.example.freshdaily.ui.MySubscription.CustomDialogActivity;
import com.example.freshdaily.ui.MySubscription.CustomDialogMonthlyActivity;
import com.example.freshdaily.ui.address;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class subscribeActitivty extends AppCompatActivity {

    //private MySubscriptionViewModel galleryViewModel;
    static int no_of_quantity = 1 ;
    public static String date,dateWeekly;
    public static final String mypreference = "userdetails";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Button minus,plus,daily,alternetDay,everyThreeDay,weekly,monthly,subsciption;
    TextView quantity;
    LinearLayout satrtDateCard,checkout;
    TextView startDate,sdate;
    ImageButton back;
    ProgressDialog dialog;
    boolean isDailySet=false,isAlternetDaySet=false,isEveryThreeDaySet=false,isWeeklySet=false,isMonthlySet=false;
    EditText promo_text;
    String product_id,product_name,photo,stock,quantity2,company_name,price,category,out_of_stock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_actitivty);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.pluse);
        daily = (Button) findViewById(R.id.daily);
        alternetDay = (Button) findViewById(R.id.alternet_day);
        everyThreeDay = (Button) findViewById(R.id.every_three_day);
        quantity = (TextView) findViewById(R.id.quantity);
        satrtDateCard = (LinearLayout) findViewById(R.id.start_date_card);
        startDate = (TextView) findViewById(R.id.dat);
        weekly = (Button) findViewById(R.id.weekly);
        monthly = (Button) findViewById(R.id.monthly);
        promo_text = (EditText) findViewById(R.id.promo_text);
        sdate = (TextView) findViewById(R.id.sdate);
        checkout = (LinearLayout) findViewById(R.id.checkout);
        subsciption = (Button) findViewById(R.id.subscribe);
        back = (ImageButton) findViewById(R.id.back);
        checkout.setVisibility(View.GONE);

        getProductData();

        dialog = new ProgressDialog(subscribeActitivty.this);
        dialog.setMessage("Loading Please Wait");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        dialog.setCancelable(false);

/*        if(no_of_quantity==0)
            minus.setEnabled(false);
        if(no_of_quantity==11)
            plus.setEnabled(false);*/

        subsciption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),String.valueOf(Integer.parseInt(price)*no_of_quantity),Toast.LENGTH_LONG).show();
                String temp = " ";
                if(isDailySet)
                    temp="Daily";
                else if(isAlternetDaySet)
                    temp="Alternet days";
                else if(isEveryThreeDaySet)
                    temp = "Every three days";
                else if(isMonthlySet)
                    temp = "Monthly";
                else if(isWeeklySet)
                    temp = "Weekly";

                apinterface api = retrofit.getapi();
                RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"),sharedpreferences.getString("id","Not found"));
                RequestBody prodid = RequestBody.create(MediaType.parse("multipart/form-data"),product_id);
                RequestBody quntit = RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(no_of_quantity));
                RequestBody sdate = RequestBody.create(MediaType.parse("multipart/form-data"),setDateFromat1(date));
                RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"),temp);
                Call<Object> call = api.addSubscription(userid,prodid,quntit,type,sdate);

                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        promo_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                return false;
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no_of_quantity--;
                quantity.setText(Integer.toString(no_of_quantity));
            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no_of_quantity++;
                quantity.setText(Integer.toString(no_of_quantity));
            }
        });

        quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(no_of_quantity==0)
                {
                    minus.setEnabled(false);
                    no_of_quantity=1;
                    quantity.setText(Integer.toString(no_of_quantity));
                    Toast.makeText(subscribeActitivty.this,"You can't subscribe 0 product.",Toast.LENGTH_LONG).show();
                }
                else if(no_of_quantity==11)
                {
                    plus.setEnabled(false);
                    no_of_quantity=10;
                    quantity.setText(Integer.toString(no_of_quantity));
                    Toast.makeText(subscribeActitivty.this,"You can't add more than 10 quantity for this product.",Toast.LENGTH_LONG).show();
                }
                else {
                    minus.setEnabled(true);
                    plus.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {  }

            @Override
            public void afterTextChanged(Editable s) {  }
        });


        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDailySet) {
                    isDailySet = false;
                    daily.setBackground( getDrawable(R.drawable.rounded_button1));
                    daily.setTextColor(Color.rgb(160,160,160));
                    satrtDateCard.setVisibility(View.GONE);
                    checkout.setVisibility(View.GONE);
                } else {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(subscribeActitivty.this,new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            isDailySet = true;
                            isAlternetDaySet = isEveryThreeDaySet= isWeeklySet = isMonthlySet = false;
                            monthly.setBackground( getDrawable(R.drawable.rounded_button1));
                            monthly.setTextColor(Color.rgb(160,160,160));
                            alternetDay.setBackground( getDrawable(R.drawable.rounded_button1));
                            alternetDay.setTextColor(Color.rgb(160,160,160));
                            everyThreeDay.setBackground( getDrawable(R.drawable.rounded_button1));
                            everyThreeDay.setTextColor(Color.rgb(160,160,160));
                            weekly.setBackground( getDrawable(R.drawable.rounded_button1));
                            weekly.setTextColor(Color.rgb(160,160,160));
                            daily.setBackground( getDrawable(R.drawable.rounded_button2));
                            daily.setTextColor(Color.rgb(0,183,235));

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            satrtDateCard.setVisibility(View.VISIBLE);
                            startDate.setText(setDateFromat(date));
                            checkout.setVisibility(View.VISIBLE);
                            sdate.setText(setDateFromat(date));

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000 * 24 * 60 * 60);
                    datePickerDialog.show();
                }
            }
        });


        alternetDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAlternetDaySet) {
                    isAlternetDaySet = false;
                    alternetDay.setBackground( getDrawable(R.drawable.rounded_button1));
                    alternetDay.setTextColor(Color.rgb(160,160,160));
                    satrtDateCard.setVisibility(View.GONE);
                    checkout.setVisibility(View.GONE);
                } else {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(subscribeActitivty.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text


                            isAlternetDaySet = true;
                            alternetDay.setBackground( getDrawable(R.drawable.rounded_button2));
                            alternetDay.setTextColor(Color.rgb(0,183,235));
                            isDailySet = isEveryThreeDaySet = isWeeklySet= isMonthlySet = false;
                            monthly.setBackground( getDrawable(R.drawable.rounded_button1));
                            monthly.setTextColor(Color.rgb(160,160,160));
                            everyThreeDay.setBackground( getDrawable(R.drawable.rounded_button1));
                            everyThreeDay.setTextColor(Color.rgb(160,160,160));
                            weekly.setBackground( getDrawable(R.drawable.rounded_button1));
                            weekly.setTextColor(Color.rgb(160,160,160));
                            daily.setBackground( getDrawable(R.drawable.rounded_button1));
                            daily.setTextColor(Color.rgb(160,160,160));

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            satrtDateCard.setVisibility(View.VISIBLE);
                            startDate.setText(setDateFromat(date));
                            checkout.setVisibility(View.VISIBLE);
                            sdate.setText(setDateFromat(date));

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000* 24 * 60 * 60);
                    datePickerDialog.show();
                }
            }
        });

        everyThreeDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEveryThreeDaySet) {
                    isEveryThreeDaySet = false;
                    everyThreeDay.setBackground( getDrawable(R.drawable.rounded_button1));
                    everyThreeDay.setTextColor(Color.rgb(160,160,160));
                    satrtDateCard.setVisibility(View.GONE);
                    checkout.setVisibility(View.GONE);
                } else {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(subscribeActitivty.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            isEveryThreeDaySet = true;
                            everyThreeDay.setBackground( getDrawable(R.drawable.rounded_button2));
                            everyThreeDay.setTextColor(Color.rgb(0,183,235));
                            isDailySet = isAlternetDaySet =isWeeklySet = isMonthlySet = false;
                            monthly.setBackground( getDrawable(R.drawable.rounded_button1));
                            monthly.setTextColor(Color.rgb(160,160,160));
                            alternetDay.setBackground( getDrawable(R.drawable.rounded_button1));
                            alternetDay.setTextColor(Color.rgb(160,160,160));
                            weekly.setBackground( getDrawable(R.drawable.rounded_button1));
                            weekly.setTextColor(Color.rgb(160,160,160));
                            daily.setBackground( getDrawable(R.drawable.rounded_button1));
                            daily.setTextColor(Color.rgb(160,160,160));

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            satrtDateCard.setVisibility(View.VISIBLE);
                            startDate.setText(setDateFromat(date));
                            checkout.setVisibility(View.VISIBLE);
                            sdate.setText(setDateFromat(date));

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000* 24 * 60 * 60);
                    datePickerDialog.show();
                }
            }
        });

        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWeeklySet)
                {
                    isWeeklySet = false;
                    weekly.setBackground(getDrawable(R.drawable.rounded_button1));
                    weekly.setTextColor(Color.rgb(160, 160, 160));
                    satrtDateCard.setVisibility(View.GONE);
                    checkout.setVisibility(View.GONE);
                }
                else
                {
                    CustomDialogActivity cdd=new CustomDialogActivity(subscribeActitivty.this);
                    cdd.show();
                    cdd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            {

                                weekly.setBackground(getDrawable(R.drawable.rounded_button2));
                                weekly.setTextColor(Color.rgb(0,183,235));
                                isWeeklySet = true;
                                isEveryThreeDaySet = isDailySet = isEveryThreeDaySet = isMonthlySet = false;
                                monthly.setBackground(getDrawable(R.drawable.rounded_button1));
                                monthly.setTextColor(Color.rgb(160,160,160));
                                satrtDateCard.setVisibility(View.VISIBLE);
                                alternetDay.setBackground(getDrawable(R.drawable.rounded_button1));
                                alternetDay.setTextColor(Color.rgb(160, 160, 160));
                                everyThreeDay.setBackground(getDrawable(R.drawable.rounded_button1));
                                everyThreeDay.setTextColor(Color.rgb(160, 160, 160));
                                daily.setBackground(getDrawable(R.drawable.rounded_button1));
                                daily.setTextColor(Color.rgb(160, 160, 160));
                                date = dateWeekly ;
                                startDate.setText(setDateFromat(date));
                                checkout.setVisibility(View.VISIBLE);
                                sdate.setText(setDateFromat(date));
                            }

                            //Toast.makeText(MainActivity.this,"hello"+dateWeekly,Toast.LENGTH_LONG).show();
                        }
                    });
                    /*while(!flag)
                    {    }*/

                    //if(dateWeekly != null)
                }
            }
        });


        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMonthlySet)
                {
                    isMonthlySet = false;
                    monthly.setBackground( getDrawable(R.drawable.rounded_button1));
                    monthly.setTextColor(Color.rgb(160, 160, 160));
                    satrtDateCard.setVisibility(View.GONE);
                    checkout.setVisibility(View.GONE);
                }
                else
                {
                    CustomDialogMonthlyActivity cdd=new CustomDialogMonthlyActivity(subscribeActitivty.this);
                    cdd.show();

                    cdd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            {
                                monthly.setBackground( getDrawable(R.drawable.rounded_button2));
                                monthly.setTextColor(Color.rgb(0,183,235));
                                isMonthlySet = true;
                                isEveryThreeDaySet = isDailySet = isEveryThreeDaySet =isWeeklySet = false;
                                satrtDateCard.setVisibility(View.VISIBLE);
                                alternetDay.setBackground( getDrawable(R.drawable.rounded_button1));
                                alternetDay.setTextColor(Color.rgb(160, 160, 160));
                                everyThreeDay.setBackground( getDrawable(R.drawable.rounded_button1));
                                everyThreeDay.setTextColor(Color.rgb(160, 160, 160));
                                weekly.setBackground( getDrawable(R.drawable.rounded_button1));
                                weekly.setTextColor(Color.rgb(160, 160, 160));
                                daily.setBackground( getDrawable(R.drawable.rounded_button1));
                                daily.setTextColor(Color.rgb(160, 160, 160));
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                date = formatter.format(new Date());
                                startDate.setText(setDateFromat(date));
                                checkout.setVisibility(View.VISIBLE);
                                sdate.setText(setDateFromat(date));
                            }

                            //Toast.makeText(MainActivity.this,"hello"+dateWeekly,Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashBord.class));
            }
        });
        
    }

    private void getProductData() {
        apinterface api = retrofit.getapi();
        RequestBody product = RequestBody.create(MediaType.parse("multipart/form-data"),getIntent().getStringExtra("productid"));
        Call<Object> call = api.getsingleproduct(product);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                try {
                    JSONObject myResponse = new JSONObject(response.body().toString());
                    product_id = myResponse.getString("product_id");
                    product_name = myResponse.getString("product_name");
                    photo = myResponse.getString("photo");
                    stock = myResponse.getString("stock");
                    quantity2 = myResponse.getString("quantity");
                    company_name = myResponse.getString("company_name");
                    price = myResponse.getString("price");
                    category = myResponse.getString("category");
                    out_of_stock = myResponse.getString("out_of_stock");

                    dialog.dismiss();

                    TextView tx = findViewById(R.id.category);
                    tx.setText(product_name.replace("_"," "));
                    tx = findViewById(R.id.company);
                    tx.setText(company_name);
                    tx = findViewById(R.id.volume);
                    tx.setText(quantity2.replace("_"," "));
                    tx = findViewById(R.id.price);
                    tx.setText(price);
                    ImageView iv = (ImageView) findViewById(R.id.img);
                    String dburl = "http://18.213.183.26/assets/images/products/";
                    Glide.with(getApplicationContext())
                            .load(Uri.parse(dburl+photo))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(iv);
                    tx = (TextView) findViewById(R.id.addr);
                    tx.setText(sharedpreferences.getString("address","Not Found"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    String setDateFromat(String temp)
    {
        String s1 = new String();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(temp);
            formatter = new SimpleDateFormat("E, dd MMM yyyy");
            s1= formatter.format(date);
        } catch (ParseException e) {e.printStackTrace();}
        return s1;
    }

    String setDateFromat1(String temp)
    {
        String s1 = new String();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(temp);
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            s1= formatter.format(date);
        } catch (ParseException e) {e.printStackTrace();}
        return s1;
    }

    public void address(View view) {
        startActivity(new Intent(subscribeActitivty.this, address.class));
    }
}
