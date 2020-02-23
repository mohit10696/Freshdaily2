package com.example.freshdaily.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.freshdaily.R;
import com.github.badoualy.datepicker.DatePickerTimeline;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class subscripFragment extends Fragment {

    View view;
    Calendar c = Calendar.getInstance();
    int day = c.get(Calendar.DAY_OF_MONTH);
    int month = c.get(Calendar.MONTH);
    int year = c.get(Calendar.YEAR);
    public static subscripFragment newInstance(String param1, String param2) {
        subscripFragment fragment = new subscripFragment();
        return fragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_subsrication, container, false);
        com.github.badoualy.datepicker.DatePickerTimeline datePickerTimeline1 = view.findViewById(R.id.datePickerTimeline2);
        datePickerTimeline1.setFirstVisibleDate(2020, month-1, day);
        datePickerTimeline1.setLastVisibleDate(2020, month+1, day);
        datePickerTimeline1.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                Toast.makeText(view.getContext(),String.valueOf(day),Toast.LENGTH_LONG).show();
            }
        });

        datePickerTimeline1.setSelectedDate(year,month,day);

        return view;
    }
    }

