package com.example.freshdaily.ui.MySubscription;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.example.freshdaily.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MySubscriptionFragment extends Fragment {

    private MySubscriptionViewModel galleryViewModel;
    static int no_of_quantity = 1 ;
    static String date,dateWeekly;
    static boolean flag = false;

    Button minus,plus,daily,alternetDay,everyThreeDay,weekly,monthly;
    TextView quantity;
    LinearLayout satrtDateCard;
    TextView startDate;
    View root;
    boolean isDailySet=false,isAlternetDaySet=false,isEveryThreeDaySet=false,isWeeklySet=false,isMonthlySet=false;
    EditText promo_text;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(MySubscriptionViewModel.class);
        root = inflater.inflate(R.layout.fragment_mysubscription, container, false);
        minus = (Button)root.findViewById(R.id.minus);
        plus = (Button)root.findViewById(R.id.pluse);
        daily = (Button)root.findViewById(R.id.daily);
        alternetDay = (Button)root.findViewById(R.id.alternet_day);
        everyThreeDay = (Button)root.findViewById(R.id.every_three_day);
        quantity = (TextView)root.findViewById(R.id.quantity);
        satrtDateCard = (LinearLayout)root.findViewById(R.id.start_date_card);
        startDate = (TextView)root.findViewById(R.id.dat);
        weekly = (Button)root.findViewById(R.id.weekly);
        monthly = (Button)root.findViewById(R.id.monthly);
        promo_text = (EditText)root.findViewById(R.id.promo_text);

/*        if(no_of_quantity==0)
            minus.setEnabled(false);
        if(no_of_quantity==11)
            plus.setEnabled(false);*/

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
                    Toast.makeText(getContext(),"You can't subscribe 0 product.",Toast.LENGTH_LONG).show();
                }
                else if(no_of_quantity==11)
                {
                    plus.setEnabled(false);
                    no_of_quantity=10;
                    quantity.setText(Integer.toString(no_of_quantity));
                    Toast.makeText(getContext(),"You can't add more than 10 quantity for this product.",Toast.LENGTH_LONG).show();
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
                    daily.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                    daily.setTextColor(Color.rgb(160,160,160));
                    satrtDateCard.setVisibility(View.GONE);
                } else {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            isDailySet = true;
                            isAlternetDaySet = isEveryThreeDaySet= isWeeklySet = isMonthlySet = false;
                            monthly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            monthly.setTextColor(Color.rgb(160,160,160));
                            alternetDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            alternetDay.setTextColor(Color.rgb(160,160,160));
                            everyThreeDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            everyThreeDay.setTextColor(Color.rgb(160,160,160));
                            weekly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            weekly.setTextColor(Color.rgb(160,160,160));
                            daily.setBackground(getActivity().getDrawable(R.drawable.rounded_button2));
                            daily.setTextColor(Color.rgb(0,183,235));

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            satrtDateCard.setVisibility(View.VISIBLE);
                            startDate.setText(setDateFromat(date));

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
                    alternetDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                    alternetDay.setTextColor(Color.rgb(160,160,160));
                    satrtDateCard.setVisibility(View.GONE);
                } else {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text


                            isAlternetDaySet = true;
                            alternetDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button2));
                            alternetDay.setTextColor(Color.rgb(0,183,235));
                            isDailySet = isEveryThreeDaySet = isWeeklySet= isMonthlySet = false;
                            monthly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            monthly.setTextColor(Color.rgb(160,160,160));
                            everyThreeDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            everyThreeDay.setTextColor(Color.rgb(160,160,160));
                            weekly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            weekly.setTextColor(Color.rgb(160,160,160));
                            daily.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            daily.setTextColor(Color.rgb(160,160,160));

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            satrtDateCard.setVisibility(View.VISIBLE);
                            startDate.setText(setDateFromat(date));

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
                    everyThreeDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                    everyThreeDay.setTextColor(Color.rgb(160,160,160));
                    satrtDateCard.setVisibility(View.GONE);
                } else {
                    // calender class's instance and get current date , month and year from calender
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            isEveryThreeDaySet = true;
                            everyThreeDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button2));
                            everyThreeDay.setTextColor(Color.rgb(0,183,235));
                            isDailySet = isAlternetDaySet =isWeeklySet = isMonthlySet = false;
                            monthly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            monthly.setTextColor(Color.rgb(160,160,160));
                            alternetDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            alternetDay.setTextColor(Color.rgb(160,160,160));
                            weekly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            weekly.setTextColor(Color.rgb(160,160,160));
                            daily.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                            daily.setTextColor(Color.rgb(160,160,160));

                            if ((monthOfYear + 1) < 10)
                                date= dayOfMonth + "-0" + (monthOfYear + 1) + "-" + year;
                            else
                                date= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            satrtDateCard.setVisibility(View.VISIBLE);
                            startDate.setText(setDateFromat(date));

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
                    weekly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                    weekly.setTextColor(Color.rgb(160, 160, 160));
                    satrtDateCard.setVisibility(View.GONE);
                }
                else
                {
                    CustomDialogActivity cdd=new CustomDialogActivity(getActivity());
                    cdd.show();
                    cdd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            {

                                weekly.setBackground(getActivity().getDrawable(R.drawable.rounded_button2));
                                weekly.setTextColor(Color.rgb(0,183,235));
                                isWeeklySet = true;
                                isEveryThreeDaySet = isDailySet = isEveryThreeDaySet = isMonthlySet = false;
                                monthly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                monthly.setTextColor(Color.rgb(160,160,160));
                                satrtDateCard.setVisibility(View.VISIBLE);
                                alternetDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                alternetDay.setTextColor(Color.rgb(160, 160, 160));
                                everyThreeDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                everyThreeDay.setTextColor(Color.rgb(160, 160, 160));
                                daily.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                daily.setTextColor(Color.rgb(160, 160, 160));
                                startDate.setText(setDateFromat(dateWeekly));
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
                    monthly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                    monthly.setTextColor(Color.rgb(160, 160, 160));
                    satrtDateCard.setVisibility(View.GONE);
                }
                else
                {
                    CustomDialogMonthlyActivity cdd=new CustomDialogMonthlyActivity(getActivity());
                    cdd.show();

                    cdd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            {
                                monthly.setBackground(getActivity().getDrawable(R.drawable.rounded_button2));
                                monthly.setTextColor(Color.rgb(0,183,235));
                                isMonthlySet = true;
                                isEveryThreeDaySet = isDailySet = isEveryThreeDaySet =isWeeklySet = false;
                                satrtDateCard.setVisibility(View.VISIBLE);
                                alternetDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                alternetDay.setTextColor(Color.rgb(160, 160, 160));
                                everyThreeDay.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                everyThreeDay.setTextColor(Color.rgb(160, 160, 160));
                                weekly.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                weekly.setTextColor(Color.rgb(160, 160, 160));
                                daily.setBackground(getActivity().getDrawable(R.drawable.rounded_button1));
                                daily.setTextColor(Color.rgb(160, 160, 160));
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                date = formatter.format(new Date());
                                startDate.setText(setDateFromat(date));
                            }

                            //Toast.makeText(MainActivity.this,"hello"+dateWeekly,Toast.LENGTH_LONG).show();
                        }
                    });

                }
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