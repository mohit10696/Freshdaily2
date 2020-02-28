package com.example.freshdaily.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.example.freshdaily.DashBord;
import com.example.freshdaily.DbAdapter;
import com.example.freshdaily.R;
import com.example.freshdaily.subscribeActitivty;
import com.example.freshdaily.ui.dashboard.ProductList.adpaterProduct;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cart extends AppCompatActivity implements PaymentResultListener {


    public static final String mypreference = "userdetails";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    RecyclerView recyclerView;
    DbAdapter db;
    LinearLayout chaekout;
    ImageButton back;
    Button clear,payment,update;
    TextView address,update_date,sdate;
    String pidList[],date;
    Activity activity;
    List<modelcart> modelcarts;
    int amount = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        recyclerView = findViewById(R.id.recyclecart);
        address = findViewById(R.id.addresshome);
        clear = (Button) findViewById(R.id.clear_cart);
        payment = (Button) findViewById(R.id.payment);
        back = (ImageButton) findViewById(R.id.back);
        update = (Button) findViewById(R.id.upda);
        update_date = (TextView) findViewById(R.id.dat1);
        sdate = (TextView) findViewById(R.id.sdate);
        chaekout = (LinearLayout) findViewById(R.id.checkout);
        address.setText(sharedpreferences.getString("address","not found"));
        activity = cart.this;
        db = new DbAdapter(getApplicationContext());
        db.open();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date = formatter.format(new Date());
        update_date.setText(setDateFromat(date));
        sdate.setText(setDateFromat(date));

        try {
            Cursor row = db.fetch();
            pidList = new String[row.getCount()+1];
            int i = 0;
            do {
                pidList[i] = row.getString(row.getColumnIndexOrThrow("pid"));
                i++;
            } while (row.moveToNext());

            modelcarts = new ArrayList<>();
            getDataofmodel(pidList);


        }
        catch (Exception e)
        {
            chaekout.setVisibility(View.GONE);
            //startActivity(new Intent(getApplicationContext(), DashBord.class));
        }
        //Toast.makeText(getApplicationContext(),pidList.length,Toast.LENGTH_LONG).show();

//        adpatercart adapter = new adpatercart(modelcarts,getApplicationContext(),getParent());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
//        recyclerView.setAdapter(adapter);

        payment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                amount =0;
                for(int i=0;i<pidList.length-1;i++)
                    amount += Integer.parseInt(modelcarts.get(i).getCount())*Integer.parseInt(modelcarts.get(i).getPrice());
                //Toast.makeText(getApplicationContext(),String.valueOf(amount),Toast.LENGTH_LONG).show();
                amount = amount *100;
                startPayment();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAll();
                startActivity(new Intent(cart.this, DashBord.class));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(cart.this,new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            update_date.setText(setDateFromat(date));
                            sdate.setText(setDateFromat(date));

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000 * 24 * 60 * 60);
                    datePickerDialog.show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DashBord.class));
            }
        });

    }

    private void getDataofmodel(final String[] pidList) {
        for(int i=0;i<pidList.length-1;i++){

            apinterface api = retrofit.getapi();
            RequestBody product = RequestBody.create(MediaType.parse("multipart/form-data"),pidList[i]);
            Call<Object> call = api.getsingleproduct(product);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    try {
                        JSONObject myResponse = new JSONObject(response.body().toString());
                        modelcarts.add(new modelcart(
                                myResponse.getString("product_id"),
                                myResponse.getString("product_name"),
                                myResponse.getString("company_name"),
                                "1",
                                myResponse.getString("quantity"),
                                myResponse.getString("price"),
                                myResponse.getString("photo")));
                        Toast.makeText(getApplicationContext(),"responces",Toast.LENGTH_LONG).show();
                        backtowork();

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    private void backtowork() {
        adpatercart adapter = new adpatercart(modelcarts,getApplicationContext(),activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"payment sucess",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"payment error",Toast.LENGTH_LONG).show();
    }

    public void startPayment() {
        //checkout.setKeyID("<YOUR_KEY_ID>");
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        //checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "fresh daily");

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "fresh daily order");
            //options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_9A33XWu170gUtm");
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", String.valueOf(amount));

            checkout.open(activity, options);
        } catch(Exception e) {
            //Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }
}
