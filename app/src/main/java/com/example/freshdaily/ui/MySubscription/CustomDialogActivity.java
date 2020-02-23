package com.example.freshdaily.ui.MySubscription;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshdaily.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDialogActivity extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public String date,day;
    boolean isDateSet = false;

    ImageButton imb;
    TextView date_text;    CheckBox sun,mon,tue,wen,thr,fri,sat;
    Button yes,no;

    public CustomDialogActivity(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        MySubscriptionFragment.dateWeekly = null;
        MySubscriptionFragment.flag = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        yes = (Button)findViewById(R.id.yes);
        no = (Button)findViewById(R.id.no);

        imb=(ImageButton)findViewById(R.id.dialog_date);
        date_text =(TextView)findViewById(R.id.dialog_date_text);

        sun=(CheckBox)findViewById(R.id.sun);
        mon=(CheckBox)findViewById(R.id.mon);
        tue=(CheckBox)findViewById(R.id.tue);
        wen=(CheckBox)findViewById(R.id.wen);
        thr=(CheckBox)findViewById(R.id.thr);
        fri=(CheckBox)findViewById(R.id.fri);
        sat=(CheckBox)findViewById(R.id.sat);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="";
                if(sun.isChecked())
                    day+="Sunday ";
                if(mon.isChecked())
                    day+="Monday ";
                if(tue.isChecked())
                    day+="Tuesday ";
                if(wen.isChecked())
                    day+="Wednessday ";
                if(thr.isChecked())
                    day+="Thrused ";
                if(fri.isChecked())
                    day+="Friday ";
                if(sat.isChecked())
                    day+="Saturday ";

                //Toast.makeText(c.getApplicationContext(),date+"\n"+day,Toast.LENGTH_LONG).show();
                if(isDateSet && !(day.isEmpty()))
                {
                    MySubscriptionFragment.dateWeekly =date;
                    MySubscriptionFragment.flag = true;
                    onBackPressed();
                }
                else
                    Toast.makeText(c.getApplicationContext(),"Please fill all the detail.",Toast.LENGTH_LONG).show();
            }

        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySubscriptionFragment.flag = true;
                dismiss();
            }
        });

        imb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            isDateSet = true;

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            date_text.setText(setDateFromat(date)+" ");
                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 1000 * 24 * 60 * 60 );
                    datePickerDialog.show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        MySubscriptionFragment.flag = true;
        dismiss();
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
