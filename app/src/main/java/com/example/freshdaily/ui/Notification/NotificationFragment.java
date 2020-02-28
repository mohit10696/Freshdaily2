package com.example.freshdaily.ui.Notification;

import com.example.freshdaily.API.*;
import com.example.freshdaily.R;
import com.example.freshdaily.subscribeActitivty;
import com.example.freshdaily.ui.dashboard.verticalProductList.adpaterProduct;
import com.example.freshdaily.ui.dashboard.verticalProductList.modelProduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.security.AccessController.getContext;


public class NotificationFragment extends Fragment {

    private NotificationViewModel toolsViewModel;

    Button pause,resume;
    static String date,status;
    View root;
    ProgressDialog dialog;
    public static final String mypreference = "userdetails";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(NotificationViewModel.class);
        root= inflater.inflate(R.layout.fragment_notification, container, false);
        sharedpreferences = this.getActivity().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        pause = (Button)root.findViewById(R.id.Pause);
        resume = (Button)root.findViewById(R.id.Resume);

        //setVacation();

        /*dialog = new ProgressDialog(root.getContext());
        dialog.setMessage("Loading Please Wait");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        dialog.setCancelable(false);*/

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                final DatePickerDialog datePickerDialog = new DatePickerDialog(root.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text

                        if ((monthOfYear + 1) < 10)
                            date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        else
                            date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        addPauseDate();
                        Toast.makeText(getContext(),setDateFromat(date),Toast.LENGTH_LONG).show();

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000 * 24 * 60 * 60);
                datePickerDialog.show();*/

                pauseDate();
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text

                        if ((monthOfYear + 1) < 10)
                            date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        else
                            date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        Toast.makeText(getContext(),setDateFromat(date),Toast.LENGTH_LONG).show();

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000 * 24 * 60 * 60);
                datePickerDialog.show();*/


            }
        });

        return root;
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

    void setVacation()
    {
        apinterface api = retrofit.getapi();
        RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"),sharedpreferences.getString("id","Not found"));
        Call<String> call = api.checkvaction(userid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    status = response.body().toString();
                    dialog.dismiss();

                    if(status.equals("1"))
                    {
                        checkResumeDate();
                    }
                    else
                    {
                        pause.setEnabled(false);
                        resume.setEnabled(false);
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    void checkResumeDate()
    {
        apinterface api = retrofit.getapi();
        RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"),sharedpreferences.getString("id","Not found"));
        Call<String> call = api.checkResumeDate(userid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if(response.body().toString().equals("0"))
                        resume.setEnabled(false);
                    else
                        pause.setEnabled(true);
                } catch (Exception e) {
                    Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    void addPauseDate()
    {
        apinterface api = retrofit.getapi();
        RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"),sharedpreferences.getString("id","Not found"));
        RequestBody pdate = RequestBody.create(MediaType.parse("multipart/form-data"),setDateFromat1(date));
        Call<String> call = api.checkResumeDate(userid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    String setDateFromat1(String temp)
    {
        String s1 = new String();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(temp);
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            s1= formatter.format(date);
        } catch (ParseException e) {e.printStackTrace();}
        return s1;
    }

    void pauseDate()
    {
        apinterface api = retrofit.getapi();
        RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"),sharedpreferences.getString("id","Not found"));
        RequestBody pdate = RequestBody.create(MediaType.parse("multipart/form-data"),new Date().toString());
        Call<Object> call = api.pausedate(userid,pdate);
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}