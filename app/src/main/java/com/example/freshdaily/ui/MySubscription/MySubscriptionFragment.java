package com.example.freshdaily.ui.MySubscription;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshdaily.API.apinterface;
import com.example.freshdaily.API.retrofit;
import com.example.freshdaily.R;

import org.json.JSONArray;
import org.json.JSONException;

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

public class MySubscriptionFragment extends Fragment {

    static int no_of_quantity = 1 ;
    static String date,dateWeekly;
    static boolean flag = false;
    RecyclerView recyclerView;


    View root;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_mysubscription
                , container, false);
        recyclerView = root.findViewById(R.id.subbox);
        apinterface api = retrofit.getapi();
        RequestBody cat = RequestBody.create(MediaType.parse("multipart/form-data"),"33");
        Call<Object> call = api.getallsub(cat);
        final List<modelsub> modelsubs = new ArrayList<>();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response.body().toString());
                    for(int i = 0 ; i< jsonArray.length();i++)
                    {

                        modelsubs.add(new modelsub(
                                jsonArray.getJSONObject(i).getString("sid"),
                                jsonArray.getJSONObject(i).getString("userid"),
                                jsonArray.getJSONObject(i).getString("product_id").replace("_"," "),
                                jsonArray.getJSONObject(i).getString("quantity").replace("_"," "),
                                jsonArray.getJSONObject(i).getString("startdate").replace("_"," "),
                                jsonArray.getJSONObject(i).getString("type").replace("_"," ")
                                ));

                    }
                    Toast.makeText(getContext(),"make test",Toast.LENGTH_SHORT).show();
                    addsub adapter = new addsub(modelsubs,getContext(),getActivity(),getView());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

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
}