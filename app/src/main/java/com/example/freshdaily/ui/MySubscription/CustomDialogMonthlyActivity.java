package com.example.freshdaily.ui.MySubscription;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshdaily.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDialogMonthlyActivity extends Dialog implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public String date[]=new String[5];

    ImageButton imb0,imb1,imb2,imb3,imb4;
    TextView date_text0,date_text1,date_text2,date_text3,date_text4;
    boolean isDateSet = false;

    Button yes,no;

    public CustomDialogMonthlyActivity(Activity a) {
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
        setContentView(R.layout.custom_dialog_monthly);

        yes = (Button)findViewById(R.id.yes);
        no = (Button)findViewById(R.id.no);

        imb0=(ImageButton) findViewById(R.id.dialog_date0);
        date_text0=(TextView) findViewById(R.id.dialog_date_text0);
        imb1 = (ImageButton) findViewById(R.id.dialog_date1);
        date_text1=(TextView) findViewById(R.id.dialog_date_text1);
        imb2=(ImageButton) findViewById(R.id.dialog_date2);
        date_text2=(TextView) findViewById(R.id.dialog_date_text2);
        imb3 = (ImageButton) findViewById(R.id.dialog_date3);
        date_text3=(TextView) findViewById(R.id.dialog_date_text3);
        imb4 = (ImageButton) findViewById(R.id.dialog_date4);
        date_text4=(TextView) findViewById(R.id.dialog_date_text4);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(c.getApplicationContext(),date[0]+" "+date[1]+" "+date[2]+" "+date[3]+" "+date[4],Toast.LENGTH_LONG).show();
                if(isDateSet)
                {
                    //MainActivity.dateWeekly =date;
                    //MainActivity.flag = true;
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

        imb0.setOnClickListener(new View.OnClickListener() {
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
                            date[0]= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        else
                            date[0]= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        date_text0.setText(setDateFromat(date[0])+" ");
                    }
                }, mYear, mMonth, mDay);

                Date today = new Date();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1 );
                long minDate = c.getTime().getTime(); // Twice!
                c.add( Calendar.MONTH, 1 );
                long maxDate = c.getTime().getTime(); // Twice!

                datePickerDialog.getDatePicker().setMaxDate(maxDate);
                datePickerDialog.getDatePicker().setMinDate(minDate);
                datePickerDialog.show();
            }
        });

        imb1.setOnClickListener(new View.OnClickListener() {
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
                            date[1]= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        else
                            date[1]= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        date_text1.setText(setDateFromat(date[1])+" ");
                    }
                }, mYear, mMonth, mDay);

                Date today = new Date();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1 );
                long minDate = c.getTime().getTime(); // Twice!
                c.add( Calendar.MONTH, 1 );
                long maxDate = c.getTime().getTime(); // Twice!

                datePickerDialog.getDatePicker().setMaxDate(maxDate);
                datePickerDialog.getDatePicker().setMinDate(minDate);
                datePickerDialog.show();
            }
        });

        imb2.setOnClickListener(new View.OnClickListener() {
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
                            date[2]= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        else
                            date[2]= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        date_text2.setText(setDateFromat(date[2])+" ");
                    }
                }, mYear, mMonth, mDay);

                Date today = new Date();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1 );
                long minDate = c.getTime().getTime(); // Twice!
                c.add( Calendar.MONTH, 1 );
                long maxDate = c.getTime().getTime(); // Twice!

                datePickerDialog.getDatePicker().setMaxDate(maxDate);
                datePickerDialog.getDatePicker().setMinDate(minDate);
                datePickerDialog.show();
            }
        });


        imb3.setOnClickListener(new View.OnClickListener() {
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
                            date[3]= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        else
                            date[3]= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        date_text3.setText(setDateFromat(date[3])+" ");
                    }
                }, mYear, mMonth, mDay);

                Date today = new Date();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1 );
                long minDate = c.getTime().getTime(); // Twice!
                c.add( Calendar.MONTH, 1 );
                long maxDate = c.getTime().getTime(); // Twice!

                datePickerDialog.getDatePicker().setMaxDate(maxDate);
                datePickerDialog.getDatePicker().setMinDate(minDate);
                datePickerDialog.show();
            }
        });

        imb4.setOnClickListener(new View.OnClickListener() {
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
                            date[4]= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                        else
                            date[4]= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                        date_text4.setText(setDateFromat(date[4])+" ");
                    }
                }, mYear, mMonth, mDay);

                Date today = new Date();
                c.setTime(today);
                c.add(Calendar.DAY_OF_MONTH, 1 );
                long minDate = c.getTime().getTime(); // Twice!
                c.add( Calendar.MONTH, 1 );
                long maxDate = c.getTime().getTime(); // Twice!

                datePickerDialog.getDatePicker().setMaxDate(maxDate);
                datePickerDialog.getDatePicker().setMinDate(minDate);
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
