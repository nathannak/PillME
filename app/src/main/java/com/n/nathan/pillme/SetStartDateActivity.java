package com.n.nathan.pillme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.TimeZone;

public class SetStartDateActivity extends AppCompatActivity {
    //
    DatePicker datePicker;
    TextView displayDate;
    Button btn;
    //
    int month;
    //
    String todayIs = null;
    //

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_start_date);
        //

        //
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        btn = (Button) findViewById(R.id.add_button_start_new_plan);
        //
        displayDate = (TextView) findViewById(R.id.display_date);
        displayDate.setText(currentDate());
        //
        String donotmoveon = null;
        Intent i = getIntent();
        //
        if(i != null)
        {

            donotmoveon = i.getStringExtra("donotmoveon");

        }
        //
        if( ( donotmoveon != null && donotmoveon.equals("donotmoveon" ) ) || todayIs_definer().equals("planfinished") || todayIs_definer().equals("plannotstarted") )
        {

            //get and save timezone ToDO
            //WhatsTimeZone.getTimezone();

            //
            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("initialtimezone", MODE_PRIVATE).edit();
            editor.putString("initialtimezone", WhatsTimeZone.getTimezone() );
            editor.commit();
            //
            Log.v("SetStartDateActivity","WhatsTimeZone.getTimezone() is: " + WhatsTimeZone.getTimezone() );
            //

            TimeZone tz = TimeZone.getTimeZone(WhatsTimeZone.getTimezone());
            //TimeZone tz = TimeZone.getTimeZone("GMT-08:00");

            final Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(tz);
            //

            //Date date = new Date();
            //calendar.setTime(date);

            //initializing datePicker
            Log.v("SetStartDateActivity", "calendar.get(Calendar.YEAR) : " + calendar.get(Calendar.YEAR) + " calendar.get(Calendar.MONTH) : "  + calendar.get(Calendar.MONTH) + " calendar.get(Calendar.DAY_OF_MONTH) : " + calendar.get(Calendar.DAY_OF_MONTH));

            datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    //
                    displayDate.setText(currentDate());
                    Log.v("SetStartDateActivity", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);

//                //
//                //save new date to shared pref
//                SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("startday", MODE_PRIVATE).edit();
//                editor.putInt("year",year);
//                editor.putInt("month",month);
//                editor.putInt("day",dayOfMonth);
//                editor.commit();
//                //

                }
            });
            //

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    int year = datePicker.getYear();
                    int month = datePicker.getMonth();
                    int dayOfMonth = datePicker.getDayOfMonth();
                    //
                    Log.v("SetStartDateActivity", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                    //
                    //if there is not change on date picker i need to save current date
                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("startday", MODE_PRIVATE).edit();
                    //
                    int Day_Of_Week = calendar.get(Calendar.DAY_OF_WEEK);
                    editor.putInt("weekday", Day_Of_Week);
                    //
                    editor.putInt("year", year);
                    editor.putInt("month", month + 1);
                    editor.putInt("day", dayOfMonth);
                    editor.commit();
                    //

                    //
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
                    //
                }
            });
            //
        }else
        {

            //
            finish();
            //
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //

        }

        //
        //end of onCreate
        //

    }

    //
    public String currentDate() {
        StringBuilder mcurrentDate = new StringBuilder();
        month = datePicker.getMonth() + 1;
        mcurrentDate.append("Your start day is:\n" + "   " + month + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear());
        return mcurrentDate.toString();
    }
    //

    //

    public String todayIs_definer() {

        //very important function, very important part of the function

        SharedPreferences pref = getSharedPreferences("initialtimezone", MODE_PRIVATE);
        String timeZone = pref.getString("initialtimezone","notimezone");

        //used to get NOW
        Calendar calendar_1;

        //used for both NOW and data from sharedpref
        TimeZone tz;

        //first time app is running
        if(timeZone.equals("notimezone"))
        {

            calendar_1 = Calendar.getInstance();
            tz = TimeZone.getTimeZone(WhatsTimeZone.getTimezone());
            calendar_1.setTimeZone(tz);

        }else{

            //get the saved time zone
            tz = TimeZone.getTimeZone(timeZone);
            calendar_1 = Calendar.getInstance(tz);

        }

        //Date date_1 = calendar_1.getTime();

        //Date date_1 = new Date();
        //calendar_1.setTime(date_1);

        //get first day of our plan from shared pref
        SharedPreferences pref_1 = getSharedPreferences("startday", MODE_PRIVATE);
        int sharedpref_year = pref_1.getInt("year", 0);
        int sharedpref_month = pref_1.getInt("month", 0);
        int sharedpref_day = pref_1.getInt("day", 0);
        //
        Log.v("SetStartDateActivity", "in SetStartDateActivity, values from shared pref: day,month,year is: " + sharedpref_day + " " + sharedpref_month + " " + sharedpref_year);
        //
        Calendar cal = Calendar.getInstance();
        //

        //months is ZERO based in JAVA
        //create date object
        cal.set(sharedpref_year, sharedpref_month - 1, sharedpref_day);
        //

        //new line added
        cal.setTimeZone(tz);

        //Date date_2 = cal.getTime();

        //
        if( ( (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) && calendar_1.get(Calendar.DAY_OF_MONTH) < cal.get(Calendar.DAY_OF_MONTH) ) || ( (calendar_1.get(Calendar.MONTH) < cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) && calendar_1.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH) ) ||  ( (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) < cal.get(Calendar.YEAR) ) && calendar_1.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH) ) )
        {

            todayIs = "plannotstarted";

        }else if( (calendar_1.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) && (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day1
            todayIs = "day1";
            //
        }else if( ((calendar_1.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==1) && (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day 2
            todayIs = "day2";
            //
        }else if( ((calendar_1.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==2) && (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day3
            todayIs = "day3";
            //
        }else if( ((calendar_1.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==3) && (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day4
            todayIs = "day4";
            //
        }else if( ((calendar_1.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==4) && (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day5
            todayIs = "day5";
            //
        }else if( ((calendar_1.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==5) && (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day6
            todayIs = "day6";
            //
        }else if( ((calendar_1.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==6) && (calendar_1.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar_1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day7
            todayIs = "day7";
            //
        }
        else
        {
            //
            Toast.makeText(this, "Your plan has finished", Toast.LENGTH_LONG).show();
            //
            todayIs = "planfinished";
            //
        }

//        old method
//        //retired due to inaccuracy concerns
//        //long hoursPassed = (calendar_1.getTimeInMillis() - date_2.getTime()) / (60000 * 60);
//        long hoursPassed = (date_1.getTime() - date_2.getTime()) / (60000 * 60);
//
//        //
//        //returns a crazy number first time called
//        Log.v("setStartDateActivity", "hours passed since start day: " + ((calendar_1.getTimeInMillis() - date_2.getTime()) / (60000 * 60)));
//        //
//        if (hoursPassed < 0) {
//
//            todayIs = "plannotstarted";
//
//        } else if (hoursPassed == 0) {
//
//            //Day1
//            todayIs = "day1";
//            //
//
//        } else if ((hoursPassed > 0) && (hoursPassed <= 25)) {
//
//            //Day 2
//            todayIs = "day2";
//            //
//
//        } else if (hoursPassed > 25 && hoursPassed <= 49) {
//
//            //Day3
//            todayIs = "day3";
//            //
//
//        } else if (hoursPassed > 49 && hoursPassed <= 73) {
//
//            //
//            todayIs = "day4";
//            //
//
//        } else if (hoursPassed > 73 && hoursPassed <= 97) {
//
//            //
//            todayIs = "day5";
//            //
//
//        } else if (hoursPassed > 97 && hoursPassed <= 121) {
//
//            //
//            todayIs = "day6";
//            //
//
//
//        } else if (hoursPassed > 121 && hoursPassed <= 145) {
//
//            //
//            todayIs = "day7";
//            //
//
//        }else{
//
//            //
//            todayIs = "planfinished";
//            //
//
//        }

          return todayIs;
    }
    //
}


