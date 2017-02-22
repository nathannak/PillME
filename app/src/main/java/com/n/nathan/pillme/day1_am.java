package com.n.nathan.pillme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;

public class day1_am extends Fragment {

    //
    ImageButton add;
    //
    TextView med1_text_view;
    TextView med2_text_view;
    TextView med3_text_view;
    TextView med4_text_view;
    TextView med5_text_view;
    //
    TextView alarm_time_med_1_text_view;
    TextView alarm_time_med_2_text_view;
    TextView alarm_time_med_3_text_view;
    TextView alarm_time_med_4_text_view;
    TextView alarm_time_med_5_text_view;
    //
    TextView current_day_textview;
    //

    //
    ImageView pill_symbol_1;
    ImageView med1_alarm_imageview;
    //
    ImageView pill_symbol_2;
    ImageView med2_alarm_imageview;
    //
    ImageView pill_symbol_3;
    ImageView med3_alarm_imageview;
    //
    ImageView pill_symbol_4;
    ImageView med4_alarm_imageview;
    //
    ImageView pill_symbol_5;
    ImageView med5_alarm_imageview;
    //

    //
    int currentHour;
    int currentMinute;
    //

    //
    Boolean onCreateRan = false;
    //

    public day1_am()
    {
        // Required empty public constructor
    }
    //

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        EDUCATIONAL LEGACY CODE
//        //
//        obsInt = new ObservableInteger();
//        //
//        obsInt.setOnIntegerChangeListener(new OnIntegerChangeListener()
//        {
//            @Override
//            public void onIntegerChanged(int newValue)
//            {
//
//            Toast.makeText(getContext() , "new value: " + newValue , Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//       //
//       obsInt.set(2);
//       //

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day1_am, container, false);
        //
        SharedPreferences pref_0 = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
        TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));

        final Calendar calendar = Calendar.getInstance(tz);

        //calendar.setTime(date);

        //
        currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        currentMinute = calendar.get(Calendar.MINUTE);
        //

        //
        Log.v("day1_am", " current time - onCreateView - currentHour " + currentHour + " and Time Zone is: " + pref_0.getString("initialtimezone","notimezone") );
        Log.v("day1_am", " current time - onCreateView - currentMinute " + currentMinute + " and Time Zone is: " + pref_0.getString("initialtimezone","notimezone") );
        Log.v("day1_am", " tz.toString(): " + tz.toString() );
        //

        //
        med1_text_view = (TextView) view.findViewById(R.id.pill1_text_view);
        med1_alarm_imageview = (ImageView) view.findViewById(R.id.alarm_med1);
        med1_alarm_imageview.setVisibility(View.GONE);
        //
        alarm_time_med_1_text_view = (TextView) view.findViewById(R.id.alarm_time_med1);
        //
        med2_text_view = (TextView) view.findViewById(R.id.pill2_text_view);
        med2_alarm_imageview = (ImageView) view.findViewById(R.id.alarm_med2);
        med2_alarm_imageview.setVisibility(View.GONE);
        //
        alarm_time_med_2_text_view = (TextView) view.findViewById(R.id.alarm_time_med2);
        //
        med3_text_view = (TextView) view.findViewById(R.id.pill3_text_view);
        med3_alarm_imageview = (ImageView) view.findViewById(R.id.alarm_med3);
        med3_alarm_imageview.setVisibility(View.GONE);
        //
        alarm_time_med_3_text_view = (TextView) view.findViewById(R.id.alarm_time_med3);
        //
        med4_text_view = (TextView) view.findViewById(R.id.pill4_text_view);
        med4_alarm_imageview = (ImageView) view.findViewById(R.id.alarm_med4);
        med4_alarm_imageview.setVisibility(View.GONE);
        //
        alarm_time_med_4_text_view = (TextView) view.findViewById(R.id.alarm_time_med4);
        //
        med5_text_view = (TextView) view.findViewById(R.id.pill5_text_view);
        med5_alarm_imageview = (ImageView) view.findViewById(R.id.alarm_med5);
        med5_alarm_imageview.setVisibility(View.GONE);
        //
        alarm_time_med_5_text_view = (TextView) view.findViewById(R.id.alarm_time_med5);
        //

        //
        current_day_textview = (TextView) view.findViewById(R.id.current_day_textview);
        current_day_textview.setText("  " + MainActivity.whichDay + " - am");
        //

        //
        pill_symbol_1 = (ImageView) view.findViewById(R.id.med1_symbol);
        pill_symbol_2 = (ImageView) view.findViewById(R.id.med2_symbol);
        pill_symbol_3 = (ImageView) view.findViewById(R.id.med3_symbol);
        pill_symbol_4 = (ImageView) view.findViewById(R.id.med4_symbol);
        pill_symbol_5 = (ImageView) view.findViewById(R.id.med5_symbol);
        //

        //setup drug name
        final SharedPreferences pref = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE);
        //

        //
        final String med1 = pref.getString("med1", null);
        //
        if (med1 != null) {
            med1_text_view.setText(med1);
        }
        //

        //setup drug symbol - complete for 12 categories
        final String med1_symbol = pref.getString("med1-symbol", null);
        if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("capsole")) {
            pill_symbol_1.setImageResource(R.mipmap.capsole);

        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("regularRoundPill")) {
            pill_symbol_1.setImageResource(R.mipmap.regularroundpill);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("granolacapsole")) {
            pill_symbol_1.setImageResource(R.mipmap.granolacapsole);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("calciumpill")) {
            pill_symbol_1.setImageResource(R.mipmap.calciumpill);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("rectangularpill")) {
            pill_symbol_1.setImageResource(R.mipmap.rectangularpill);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("ointment")) {
            pill_symbol_1.setImageResource(R.mipmap.ointment);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("dropper")) {
            pill_symbol_1.setImageResource(R.mipmap.dropper);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("inhaler")) {
            pill_symbol_1.setImageResource(R.mipmap.inhaler);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("syringe")) {
            pill_symbol_1.setImageResource(R.mipmap.syringe);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("syrup")) {
            pill_symbol_1.setImageResource(R.mipmap.syrup);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("spray")) {
            pill_symbol_1.setImageResource(R.mipmap.spray);
        } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("bloodsugar")) {
            pill_symbol_1.setImageResource(R.mipmap.bloodsugar);
        }
        //

        //
        Boolean med1_alarm = pref.getBoolean("med1-alarm", false);
        //

        //show alarm or not depending on the time of the day

        //if(MainActivity.todayIs.equals("day1") && med1_alarm && show_alarm_image_or_not(getContext(),"day1-am","med1",currentHour,currentMinute))
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med1_alarm)

        {
            //show the bell image
            med1_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med1", currentHour, currentMinute)) {
            med1_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //

        //
        if (med1_alarm) {
            //
            alarm_time_med_1_text_view.setVisibility(View.VISIBLE);
            //

            //print the time if IT HAS ALARM
            int hour = pref.getInt("med1-hour", 0);
            Log.v("myapp", "med1-hour from shared pref is:" + hour);
            int minute = pref.getInt("med1-minute", 0);
            Log.v("myapp", "med1-minute from shared pref is:" + minute);

            //
            alarm_time_med_1_text_view.setText(hour + ":" + cook_minute(minute) );
            //

            // NOW i need todayIs - very important
            if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {

                //
                alarm_time_med_1_text_view.setBackgroundColor(Color.RED);
                med1_text_view.setBackgroundColor(Color.RED);
                //
                play_alarm_red_text(getActivity());
                //

                //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                Handler mVolHandler = new Handler();
                Runnable mVolRunnable = new Runnable() {
                    public void run() {
                        //

                        //
                        SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
                        TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));
                        final Calendar calendar = Calendar.getInstance(tz);

//                        Date date = new Date();
//                        calendar.setTime(date);
                        //

                        //
                        Log.v("myap", "handler ran");
                        //
                        currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                        Log.v("myapp", "current time (hour) " + currentHour);
                        //
                        currentMinute = calendar.get(Calendar.MINUTE);
                        Log.v("myapp", "current time (minute) " + currentMinute);
                        //

                        // I had to get values from sharedpref anyways
                        int hour = pref.getInt("med1-hour", 0);
                        Log.v("myapp", "med1-hour from shared pref is:" + hour);
                        int minute = pref.getInt("med1-minute", 0);
                        Log.v("myapp", "med1-minute from shared pref is:" + minute);
                        //

                        //
                        if ((hour == currentHour && (currentMinute > (minute + 2))) || (hour != currentHour)) {

                            Log.v("myap", "handler ran & insde if statement to make text_1 white");

                            alarm_time_med_1_text_view.setBackgroundColor(Color.WHITE);
                            med1_text_view.setBackgroundColor(Color.WHITE);
                            //
                            med1_alarm_imageview.setVisibility(View.GONE);

                        }
                        //
                    }
                };

                //
                //mVolHandler.removeCallbacks(mVolRunnable);
                mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                //

            }
        }
        //

        //
        final String med2 = pref.getString("med2", null);
        if (med2 != null) {
            med2_text_view.setText(med2);
        }
        //

        //
        final String med2_symbol = pref.getString("med2-symbol", null);
        if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("capsole")) {
            pill_symbol_2.setImageResource(R.mipmap.capsole);

        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("regularRoundPill")) {
            pill_symbol_2.setImageResource(R.mipmap.regularroundpill);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("granolacapsole")) {
            pill_symbol_2.setImageResource(R.mipmap.granolacapsole);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("calciumpill")) {
            pill_symbol_2.setImageResource(R.mipmap.calciumpill);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("rectangularpill")) {
            pill_symbol_2.setImageResource(R.mipmap.rectangularpill);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("ointment")) {
            pill_symbol_2.setImageResource(R.mipmap.ointment);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("dropper")) {
            pill_symbol_2.setImageResource(R.mipmap.dropper);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("inhaler")) {
            pill_symbol_2.setImageResource(R.mipmap.inhaler);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("syringe")) {
            pill_symbol_2.setImageResource(R.mipmap.syringe);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("syrup")) {
            pill_symbol_2.setImageResource(R.mipmap.syrup);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("spray")) {
            pill_symbol_2.setImageResource(R.mipmap.spray);
        } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("bloodsugar")) {
            pill_symbol_2.setImageResource(R.mipmap.bloodsugar);
        }
        //

        //
        Boolean med2_alarm = pref.getBoolean("med2-alarm", false);
        //

        //show alarm or not depending on the time of the day

        //if(MainActivity.todayIs.equals(MainActivity.whichDay) && med2_alarm && show_alarm_image_or_not(getContext(),MainActivity.whichDay+"-am","med2",currentHour,currentMinute))
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med2_alarm) {
            //show the bell image
            med2_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //

        //
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med2", currentHour, currentMinute)) {
            med2_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //

        //
        if (med2_alarm) {
            //show the bell image
            alarm_time_med_2_text_view.setVisibility(View.VISIBLE);
            //

            //print the time if IT HAS ALARM
            int hour = pref.getInt("med2-hour", 0);
            Log.v("myapp", "med2-hour from shared pref is:" + hour);
            int minute = pref.getInt("med2-minute", 0);
            Log.v("myapp", "med2-minute from shared pref is:" + minute);

            //
            alarm_time_med_2_text_view.setText(hour + ":" + cook_minute(minute) );
            //

            if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {
                alarm_time_med_2_text_view.setBackgroundColor(Color.RED);
                med2_text_view.setBackgroundColor(Color.RED);
                //
                play_alarm_red_text(getActivity());
                //

                //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                Handler mVolHandler = new Handler();
                Runnable mVolRunnable = new Runnable() {
                    public void run() {
                        //

                        //
                        SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);

//                        Date date = new Date();
//                        calendar.setTime(date);
                        //

                        //
                        Log.v("myap", "handler ran");
                        //
                        currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                        Log.v("myapp", "current time (hour) " + currentHour);

                        currentMinute = calendar.get(Calendar.MINUTE);
                        Log.v("myapp", "current time (minute) " + currentMinute);

                        //

                        // I had to get values from sharedpref anyways
                        int hour = pref.getInt("med2-hour", 0);
                        Log.v("myapp", "med2-hour from shared pref is:" + hour);
                        int minute = pref.getInt("med2-minute", 0);
                        Log.v("myapp", "med2-minute from shared pref is:" + minute);
                        //

                        //
                        if ((hour == currentHour && (currentMinute > (minute + 2))) || (hour != currentHour)) {
                            Log.v("myap", "handler ran & insde if statement to make text_1 white");

                            alarm_time_med_2_text_view.setBackgroundColor(Color.WHITE);
                            med2_text_view.setBackgroundColor(Color.WHITE);

                            med2_alarm_imageview.setVisibility(View.GONE);

                        }
                        //
                    }
                };

                //
                //mVolHandler.removeCallbacks(mVolRunnable);
                mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                //
            }
        }
        //
        final String med3 = pref.getString("med3", null);
        if (med3 != null) {
            med3_text_view.setText(med3);
        }
        //

        //
        final String med3_symbol = pref.getString("med3-symbol", null);
        if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("capsole")) {
            pill_symbol_3.setImageResource(R.mipmap.capsole);

        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("regularRoundPill")) {
            pill_symbol_3.setImageResource(R.mipmap.regularroundpill);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("granolacapsole")) {
            pill_symbol_3.setImageResource(R.mipmap.granolacapsole);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("calciumpill")) {
            pill_symbol_3.setImageResource(R.mipmap.calciumpill);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("rectangularpill")) {
            pill_symbol_3.setImageResource(R.mipmap.rectangularpill);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("ointment")) {
            pill_symbol_3.setImageResource(R.mipmap.ointment);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("dropper")) {
            pill_symbol_3.setImageResource(R.mipmap.dropper);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("inhaler")) {
            pill_symbol_3.setImageResource(R.mipmap.inhaler);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("syringe")) {
            pill_symbol_3.setImageResource(R.mipmap.syringe);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("syrup")) {
            pill_symbol_3.setImageResource(R.mipmap.syrup);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("spray")) {
            pill_symbol_3.setImageResource(R.mipmap.spray);
        } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("bloodsugar")) {
            pill_symbol_3.setImageResource(R.mipmap.bloodsugar);
        }
        //

        //
        Boolean med3_alarm = pref.getBoolean("med3-alarm", false);
        //

        //show alarm or not depending on the time of the day
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med3_alarm) {
            //show the bell image
            med3_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //

        //
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med3", currentHour, currentMinute)) {
            med3_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //

        if (med3_alarm) {
            //show the bell image
            alarm_time_med_3_text_view.setVisibility(View.VISIBLE);
            //

            //print the time if IT HAS ALARM
            int hour = pref.getInt("med3-hour", 0);
            Log.v("myapp", "med3-hour from shared pref is:" + hour);
            int minute = pref.getInt("med3-minute", 0);
            Log.v("myapp", "med3-minute from shared pref is:" + minute);

            //
            alarm_time_med_3_text_view.setText(hour + ":" +  cook_minute(minute) );
            //

            if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {
                alarm_time_med_3_text_view.setBackgroundColor(Color.RED);
                med3_text_view.setBackgroundColor(Color.RED);
                //
                play_alarm_red_text(getActivity());
                //


                //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                Handler mVolHandler = new Handler();
                Runnable mVolRunnable = new Runnable() {
                    public void run() {
                        //

                        //
                        SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);

//                        Date date = new Date();
//                        calendar.setTime(date);
                        //

                        //
                        Log.v("myap", "handler ran");
                        //
                        currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                        Log.v("myapp", "current time (hour) " + currentHour);

                        currentMinute = calendar.get(Calendar.MINUTE);
                        Log.v("myapp", "current time (minute) " + currentMinute);

                        //

                        // I had to get values from sharedpref anyways
                        int hour = pref.getInt("med3-hour", 0);
                        Log.v("myapp", "med3-hour from shared pref is:" + hour);
                        int minute = pref.getInt("med3-minute", 0);
                        Log.v("myapp", "med3-minute from shared pref is:" + minute);
                        //

                        //
                        if ((hour == currentHour && (currentMinute > (minute + 2))) || (hour != currentHour)) {
                            Log.v("myap", "handler ran & insde if statement to make text_1 white");

                            alarm_time_med_3_text_view.setBackgroundColor(Color.WHITE);
                            med3_text_view.setBackgroundColor(Color.WHITE);
                            //
                            med3_alarm_imageview.setVisibility(View.GONE);

                        }
                        //


                        //
                    }
                };

                //
                //mVolHandler.removeCallbacks(mVolRunnable);
                mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                //


                //
            }
        }
        //

        //

        //
        final String med4 = pref.getString("med4", null);
        if (med4 != null) {
            med4_text_view.setText(med4);
        }
        //


        //
        final String med4_symbol = pref.getString("med4-symbol", null);
        if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("capsole")) {
            pill_symbol_4.setImageResource(R.mipmap.capsole);

        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("regularRoundPill")) {
            pill_symbol_4.setImageResource(R.mipmap.regularroundpill);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("granolacapsole")) {
            pill_symbol_4.setImageResource(R.mipmap.granolacapsole);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("calciumpill")) {
            pill_symbol_4.setImageResource(R.mipmap.calciumpill);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("rectangularpill")) {
            pill_symbol_4.setImageResource(R.mipmap.rectangularpill);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("ointment")) {
            pill_symbol_4.setImageResource(R.mipmap.ointment);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("dropper")) {
            pill_symbol_4.setImageResource(R.mipmap.dropper);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("inhaler")) {
            pill_symbol_4.setImageResource(R.mipmap.inhaler);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("syringe")) {
            pill_symbol_4.setImageResource(R.mipmap.syringe);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("syrup")) {
            pill_symbol_4.setImageResource(R.mipmap.syrup);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("spray")) {
            pill_symbol_4.setImageResource(R.mipmap.spray);
        } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("bloodsugar")) {
            pill_symbol_4.setImageResource(R.mipmap.bloodsugar);
        }
        //
        //

        //
        Boolean med4_alarm = pref.getBoolean("med4-alarm", false);
        //

        //show alarm or not depending on the time of the day
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med4_alarm) {
            //show the bell image
            med4_alarm_imageview.setVisibility(View.VISIBLE);
        }

        //
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med4", currentHour, currentMinute)) {
            med4_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //

        if (med4_alarm) {
            alarm_time_med_4_text_view.setVisibility(View.VISIBLE);
            //

            //print the time if IT HAS ALARM
            int hour = pref.getInt("med4-hour", 0);
            Log.v("myapp", "med4-hour from shared pref is:" + hour);
            int minute = pref.getInt("med4-minute", 0);
            Log.v("myapp", "med4-minute from shared pref is:" + minute);

            //
            alarm_time_med_4_text_view.setText(hour + ":" +  cook_minute(minute)  );
            //

            if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {

                alarm_time_med_4_text_view.setBackgroundColor(Color.RED);
                med4_text_view.setBackgroundColor(Color.RED);
                //
                play_alarm_red_text(getActivity());
                //

                //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                Handler mVolHandler = new Handler();
                Runnable mVolRunnable = new Runnable() {
                    public void run() {

                        //

                        //
                        SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                        Date date = new Date();
//                        calendar.setTime(date);
                        //

                        //
                        Log.v("myap", "handler ran");
                        //
                        currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                        Log.v("myapp", "current time (hour) " + currentHour);

                        currentMinute = calendar.get(Calendar.MINUTE);
                        Log.v("myapp", "current time (minute) " + currentMinute);

                        //

                        // I had to get values from sharedpref anyways
                        int hour = pref.getInt("med4-hour", 0);
                        Log.v("myapp", "med4-hour from shared pref is:" + hour);
                        int minute = pref.getInt("med4-minute", 0);
                        Log.v("myapp", "med4-minute from shared pref is:" + minute);
                        //

                        //
                        if ((hour == currentHour && (currentMinute > (minute + 2))) || (hour != currentHour)) {
                            Log.v("myap", "handler ran & insde if statement to make text_1 white");

                            alarm_time_med_4_text_view.setBackgroundColor(Color.WHITE);
                            med4_text_view.setBackgroundColor(Color.WHITE);
                            //
                            med4_alarm_imageview.setVisibility(View.GONE);

                        }
                        //


                        //
                    }
                };

                //
                //mVolHandler.removeCallbacks(mVolRunnable);
                mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                //


                //
            }
        }
        //


        //
        final String med5 = pref.getString("med5", null);
        if (med5 != null) {
            med5_text_view.setText(med5);
        }
        //

        //
        final String med5_symbol = pref.getString("med5-symbol", null);
        if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("capsole")) {
            pill_symbol_5.setImageResource(R.mipmap.capsole);

        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("regularRoundPill")) {
            pill_symbol_5.setImageResource(R.mipmap.regularroundpill);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("granolacapsole")) {
            pill_symbol_5.setImageResource(R.mipmap.granolacapsole);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("calciumpill")) {
            pill_symbol_5.setImageResource(R.mipmap.calciumpill);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("rectangularpill")) {
            pill_symbol_5.setImageResource(R.mipmap.rectangularpill);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("ointment")) {
            pill_symbol_5.setImageResource(R.mipmap.ointment);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("dropper")) {
            pill_symbol_5.setImageResource(R.mipmap.dropper);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("inhaler")) {
            pill_symbol_5.setImageResource(R.mipmap.inhaler);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("syringe")) {
            pill_symbol_5.setImageResource(R.mipmap.syringe);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("syrup")) {
            pill_symbol_5.setImageResource(R.mipmap.syrup);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("spray")) {
            pill_symbol_5.setImageResource(R.mipmap.spray);
        } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("bloodsugar")) {
            pill_symbol_5.setImageResource(R.mipmap.bloodsugar);
        }
        //

        //
        Boolean med5_alarm = pref.getBoolean("med5-alarm", false);
        //

        //show alarm or not depending on the time of the day
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med5_alarm) {
            //show the bell image
            med5_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //
        //
        if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med5", currentHour, currentMinute)) {
            med5_alarm_imageview.setVisibility(View.VISIBLE);
        }
        //

        //
        if (med5_alarm) {
            alarm_time_med_5_text_view.setVisibility(View.VISIBLE);
            //

            //print the time if IT HAS ALARM
            int hour = pref.getInt("med5-hour", 0);
            Log.v("myapp", "med5-hour from shared pref is:" + hour);
            int minute = pref.getInt("med5-minute", 0);
            Log.v("myapp", "med5-minute from shared pref is:" + minute);

            //
            alarm_time_med_5_text_view.setText(hour + ":" +  cook_minute(minute)  );
            //

            if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {
                alarm_time_med_5_text_view.setBackgroundColor(Color.RED);
                med5_text_view.setBackgroundColor(Color.RED);
                //
                play_alarm_red_text(getActivity());
                //


                //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                Handler mVolHandler = new Handler();
                Runnable mVolRunnable = new Runnable() {
                    public void run() {
                        //

                        //
                        SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                        Date date = new Date();
//                        calendar.setTime(date);
                        //

                        //
                        Log.v("myap", "handler ran");
                        //
                        currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                        Log.v("myapp", "current time (hour) " + currentHour);

                        currentMinute = calendar.get(Calendar.MINUTE);
                        Log.v("myapp", "current time (minute) " + currentMinute);

                        //

                        // I had to get values from sharedpref anyways
                        int hour = pref.getInt("med5-hour", 0);
                        Log.v("myapp", "med5-hour from shared pref is:" + hour);
                        int minute = pref.getInt("med5-minute", 0);
                        Log.v("myapp", "med5-minute from shared pref is:" + minute);
                        //

                        //
                        if ((hour == currentHour && (currentMinute > (minute + 2))) || (hour != currentHour)) {
                            Log.v("myap", "handler ran & insde if statement to make text_1 white");

                            alarm_time_med_5_text_view.setBackgroundColor(Color.WHITE);
                            med5_text_view.setBackgroundColor(Color.WHITE);
                            //
                            med5_alarm_imageview.setVisibility(View.GONE);

                        }
                        //


                        //
                    }
                };

                //
                //mVolHandler.removeCallbacks(mVolRunnable);
                mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                //


                //
            }
        }
        //

        //LONG CLICK LISTENER ON TEXT1
        med1_text_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE).edit();
                editor.putString("med1", null);
                editor.putString("med1-symbol", null);
                //

                //
                editor.putBoolean("med1-alarm", false);
                //

                //
                editor.putString("med1-hour", null);
                editor.putString("med1-minute", null);
                //

                //
                editor.commit();
                //

                med1_text_view.setText(" ");
                pill_symbol_1.setImageResource(R.mipmap.white_image);
                //

                //hide alarm symbol if visible -- add the spme for TextView 2,3,4,5
                if (med1_alarm_imageview.getVisibility() == View.VISIBLE) {

                    med1_alarm_imageview.setVisibility(View.GONE);

                }
                //

                //
                alarm_time_med_1_text_view.setBackgroundColor(Color.WHITE);
                med1_text_view.setBackgroundColor(Color.WHITE);
                //
                alarm_time_med_1_text_view.setVisibility(View.INVISIBLE);
                //

                //cancell all alarms

                //AlarmManager AlarmsInWeekAlarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent i1 = new Intent(getActivity(), PollReceiver.class);
                //
                SharedPreferences pref = getActivity().getSharedPreferences("alarmnumber",MODE_PRIVATE);
                int alarmnumber = pref.getInt("alarmnumber",0);
                //
                Log.v("day1_am","alarmnumber from sharedpref is: " + alarmnumber);
                //
                for(int i =0; i<alarmnumber; i++)
                {
                    //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), i , i1, PendingIntent.FLAG_CANCEL_CURRENT);
                    PendingIntent pi = PendingIntent.getBroadcast(getActivity(), i , i1 , PendingIntent.FLAG_CANCEL_CURRENT);
                    mgr.cancel(pi);
                    //
                }
                //

                //re-schedule all alarms again, but now the deleted one will not be scheduled.
                Intent intent = new Intent(getActivity(),RescheduleAlarms.class);
                intent.putExtra("rescheduleanyways",true);
                getActivity().startService(intent);
                //

                //
                return false;
                //

            }
        });

        //

        //
        //LONG CLICK LISTENER FOR TEXT2
        //
        med2_text_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE).edit();
                editor.putString("med2", null);
                editor.putString("med2-symbol", null);
                editor.commit();
                //

                //
                editor.putBoolean("med2-alarm", false);
                //

                //
                editor.putString("med2-hour", null);
                editor.putString("med2-minute", null);
                //

                //
                editor.commit();
                //

                med2_text_view.setText(" ");
                pill_symbol_2.setImageResource(R.mipmap.white_image);
                //

                //hide alarm symbol if visible -- add the spme for TextView 2,3,4,5
                if (med2_alarm_imageview.getVisibility() == View.VISIBLE) {

                    med2_alarm_imageview.setVisibility(View.GONE);

                }
                //

                //
                alarm_time_med_2_text_view.setBackgroundColor(Color.WHITE);
                med2_text_view.setBackgroundColor(Color.WHITE);
                //
                alarm_time_med_2_text_view.setVisibility(View.INVISIBLE);
                //

                //
                //cancell all alarms

                //AlarmManager AlarmsInWeekAlarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent i1 = new Intent(getActivity(), PollReceiver.class);
                //
                SharedPreferences pref = getActivity().getSharedPreferences("alarmnumber",MODE_PRIVATE);
                int alarmnumber = pref.getInt("alarmnumber",0);
                //
                Log.v("day1_am","alarmnumber from sharedpref is: " + alarmnumber);
                //
                for(int i =0; i<alarmnumber; i++)
                {
                    //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), i , i1, PendingIntent.FLAG_CANCEL_CURRENT);
                    PendingIntent pi = PendingIntent.getBroadcast(getActivity(), i , i1 , PendingIntent.FLAG_CANCEL_CURRENT);
                    mgr.cancel(pi);
                    //
                }
                //

                //re-schedule all alarms again, but now the deleted one will not be scheduled.
                Intent intent = new Intent(getActivity(),RescheduleAlarms.class);
                intent.putExtra("rescheduleanyways",true);
                getActivity().startService(intent);
                //
                //

                //
                return false;
                //

            }
        });


        //
        //LONG CLICK LISTENER FOR TEXT3
        //
        med3_text_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE).edit();
                editor.putString("med3", null);
                editor.putString("med3-symbol", null);
                editor.commit();
                //

                //
                editor.putBoolean("med3-alarm", false);
                //


                //
                editor.putString("med3-hour", null);
                editor.putString("med3-minute", null);
                //

                //
                editor.commit();
                //

                med3_text_view.setText(" ");
                pill_symbol_3.setImageResource(R.mipmap.white_image);
                //

                //hide alarm symbol if visible -- add the spme for TextView 2,3,4,5
                if (med3_alarm_imageview.getVisibility() == View.VISIBLE) {

                    med3_alarm_imageview.setVisibility(View.GONE);

                }
                //

                //
                alarm_time_med_3_text_view.setBackgroundColor(Color.WHITE);
                med3_text_view.setBackgroundColor(Color.WHITE);
                //
                alarm_time_med_3_text_view.setVisibility(View.INVISIBLE);
                //

                //
                //cancell all alarms

                //AlarmManager AlarmsInWeekAlarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent i1 = new Intent(getActivity(), PollReceiver.class);
                //
                SharedPreferences pref = getActivity().getSharedPreferences("alarmnumber",MODE_PRIVATE);
                int alarmnumber = pref.getInt("alarmnumber",0);
                //
                Log.v("day1_am","alarmnumber from sharedpref is: " + alarmnumber);
                //
                for(int i =0; i<alarmnumber; i++)
                {
                    //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), i , i1, PendingIntent.FLAG_CANCEL_CURRENT);
                    PendingIntent pi = PendingIntent.getBroadcast(getActivity(), i , i1 , PendingIntent.FLAG_CANCEL_CURRENT);
                    mgr.cancel(pi);
                    //
                }
                //

                //re-schedule all alarms again, but now the deleted one will not be scheduled.
                Intent intent = new Intent(getActivity(),RescheduleAlarms.class);
                intent.putExtra("rescheduleanyways",true);
                getActivity().startService(intent);
                //
                //

                //
                return false;
                //

            }
        });


        //
        //LONG CLICK LISTENER FOR TEXT4
        //
        med4_text_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE).edit();
                editor.putString("med4", null);
                editor.putString("med4-symbol", null);
                editor.commit();
                //

                //
                editor.putBoolean("med4-alarm", false);
                //


                //
                editor.putString("med4-hour", null);
                editor.putString("med4-minute", null);
                //

                //
                editor.commit();
                //

                med4_text_view.setText(" ");
                pill_symbol_4.setImageResource(R.mipmap.white_image);
                //

                //hide alarm symbol if visible -- add the spme for TextView 2,3,4,5
                if (med4_alarm_imageview.getVisibility() == View.VISIBLE) {

                    med4_alarm_imageview.setVisibility(View.GONE);

                }
                //

                //
                alarm_time_med_4_text_view.setBackgroundColor(Color.WHITE);
                med4_text_view.setBackgroundColor(Color.WHITE);
                //
                alarm_time_med_4_text_view.setVisibility(View.INVISIBLE);
                //

                //
                //cancell all alarms

                //AlarmManager AlarmsInWeekAlarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent i1 = new Intent(getActivity(), PollReceiver.class);
                //
                SharedPreferences pref = getActivity().getSharedPreferences("alarmnumber",MODE_PRIVATE);
                int alarmnumber = pref.getInt("alarmnumber",0);
                //
                Log.v("day1_am","alarmnumber from sharedpref is: " + alarmnumber);
                //
                for(int i =0; i<alarmnumber; i++)
                {
                    //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), i , i1, PendingIntent.FLAG_CANCEL_CURRENT);
                    PendingIntent pi = PendingIntent.getBroadcast(getActivity(), i , i1 , PendingIntent.FLAG_CANCEL_CURRENT);
                    mgr.cancel(pi);
                    //
                }
                //

                //re-schedule all alarms again, but now the deleted one will not be scheduled.
                Intent intent = new Intent(getActivity(),RescheduleAlarms.class);
                intent.putExtra("rescheduleanyways",true);
                getActivity().startService(intent);
                //
                //

                //
                return false;
                //

            }
        });


        //
        //LONG CLICK LISTENER FOR TEXT5
        //
        med5_text_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE).edit();
                editor.putString("med5", null);
                editor.putString("med5-symbol", null);
                editor.commit();
                //

                //
                editor.putBoolean("med5-alarm", false);
                //

                //
                editor.putString("med5-hour", null);
                editor.putString("med5-minute", null);
                //

                //
                editor.commit();
                //

                med5_text_view.setText(" ");
                pill_symbol_5.setImageResource(R.mipmap.white_image);
                //

                //hide alarm symbol if visible -- add the spme for TextView 2,3,4,5
                if (med5_alarm_imageview.getVisibility() == View.VISIBLE) {

                    med5_alarm_imageview.setVisibility(View.GONE);

                }
                //

                //
                alarm_time_med_5_text_view.setBackgroundColor(Color.WHITE);
                med5_text_view.setBackgroundColor(Color.WHITE);
                //
                alarm_time_med_5_text_view.setVisibility(View.INVISIBLE);
                //

                //
                //cancell all alarms

                //AlarmManager AlarmsInWeekAlarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent i1 = new Intent(getActivity(), PollReceiver.class);
                //
                SharedPreferences pref = getActivity().getSharedPreferences("alarmnumber",MODE_PRIVATE);
                int alarmnumber = pref.getInt("alarmnumber",0);
                //
                Log.v("day1_am","alarmnumber from sharedpref is: " + alarmnumber);
                //
                for(int i =0; i<alarmnumber; i++)
                {
                    //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), i , i1, PendingIntent.FLAG_CANCEL_CURRENT);
                    PendingIntent pi = PendingIntent.getBroadcast(getActivity(), i , i1 , PendingIntent.FLAG_CANCEL_CURRENT);
                    mgr.cancel(pi);
                    //
                }
                //

                //re-schedule all alarms again, but now the deleted one will not be scheduled.
                Intent intent = new Intent(getActivity(),RescheduleAlarms.class);
                intent.putExtra("rescheduleanyways",true);
                getActivity().startService(intent);
                //
                //

                //
                return false;
                //

            }
        });

        //

        //
        add = (ImageButton) view.findViewById(R.id.top_add);
        //

        //
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //
                        Intent intent = new Intent(getActivity(), AddPillActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("day_time", MainActivity.whichDay + "-am");
                        getActivity().startActivity(intent);
                        //

                    }
                });
                //
            }
        });

        //
        new MakeRedOperation(getActivity()).execute();
        //

        //
        onCreateRan = true;
        //

        //
        return view;
        //


        //end of onCreateView
        //
    }
    //

    //
    @Override
    public void onPause()
    {
        //

        //
        super.onPause();
        //

        onCreateRan = false;

        //
    }
    //

    //
    @Override
    public void onStop()
    {
        //
        super.onStop();
        //

        //
        onCreateRan = false;
        //

    }
    //

    //
    @Override
    public void onResume() {

        //
        super.onResume();
        //

        if (!onCreateRan) {
            //

            SharedPreferences pref_0 = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
            TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));
            final Calendar calendar = Calendar.getInstance(tz);

//            Date date = new Date();
//            calendar.setTime(date);
            //

            //update currentHour
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            Log.v("myapp", "current time is (hour):" + currentHour);

            //update currentMinute
            currentMinute = calendar.get(Calendar.MINUTE);
            Log.v("myapp", "current time is (minute):" + currentMinute);

            //
            //setup med npme
            final SharedPreferences pref = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE);
            //

            //
            final String med1 = pref.getString("med1", null);
            //
            if (med1 != null) {
                med1_text_view.setText(med1);
            }
            //

            //ToDo
            //setup drug symbol - complete for 12 categories
            final String med1_symbol = pref.getString("med1-symbol", null);
            if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("capsole")) {
                pill_symbol_1.setImageResource(R.mipmap.capsole);

            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("regularRoundPill")) {
                pill_symbol_1.setImageResource(R.mipmap.regularroundpill);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("granolacapsole")) {
                pill_symbol_1.setImageResource(R.mipmap.granolacapsole);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("calciumpill")) {
                pill_symbol_1.setImageResource(R.mipmap.calciumpill);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("rectangularpill")) {
                pill_symbol_1.setImageResource(R.mipmap.rectangularpill);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("ointment")) {
                pill_symbol_1.setImageResource(R.mipmap.ointment);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("dropper")) {
                pill_symbol_1.setImageResource(R.mipmap.dropper);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("inhaler")) {
                pill_symbol_1.setImageResource(R.mipmap.inhaler);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("syringe")) {
                pill_symbol_1.setImageResource(R.mipmap.syringe);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("syrup")) {
                pill_symbol_1.setImageResource(R.mipmap.syrup);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("spray")) {
                pill_symbol_1.setImageResource(R.mipmap.spray);
            } else if (pill_symbol_1 != null && med1_symbol != null && med1_symbol.equals("bloodsugar")) {
                pill_symbol_1.setImageResource(R.mipmap.bloodsugar);
            }
            //

            //
            Boolean med1_alarm = pref.getBoolean("med1-alarm", false);
            //

            //show alarm or not depending on the time of the day

            if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med1_alarm) {
                //show the bell image
                med1_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //

            //
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med1", currentHour, currentMinute)) {
                med1_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //

            if (med1_alarm) {

                //show the bell image
                alarm_time_med_1_text_view.setVisibility(View.VISIBLE);
                //

                //print the time if IT HAS ALARM
                int hour = pref.getInt("med1-hour", 0);
                Log.v("myapp", "med1-hour from shared pref is:" + hour);
                int minute = pref.getInt("med1-minute", 0);
                Log.v("myapp", "med1-minute from shared pref is:" + minute);
                //

                //
                alarm_time_med_1_text_view.setText(hour + ":" +  cook_minute(minute) );
                //

                if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {

                    //
                    alarm_time_med_1_text_view.setBackgroundColor(Color.RED);
                    med1_text_view.setBackgroundColor(Color.RED);
                    //

                    //
                    play_alarm_red_text(getActivity());
                    //

                    //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                            Date date = new Date();
//                            calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);

                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);

                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med1-hour", 0);
                            Log.v("myapp", "med1-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med1-minute", 0);
                            Log.v("myapp", "med1-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && ((currentMinute > (minute + 2)))) || (currentHour != hour)) {
                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_1_text_view.setBackgroundColor(Color.WHITE);
                                med1_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med1_alarm_imageview.setVisibility(View.GONE);
                                //
                            }
                            //

                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                    //


                    //

                    //
                }
            }
            //


            //
            final String med2 = pref.getString("med2", null);
            if (med2 != null) {
                med2_text_view.setText(med2);
            }
            //

            //
            final String med2_symbol = pref.getString("med2-symbol", null);
            if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("capsole")) {
                pill_symbol_2.setImageResource(R.mipmap.capsole);

            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("regularRoundPill")) {
                pill_symbol_2.setImageResource(R.mipmap.regularroundpill);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("granolacapsole")) {
                pill_symbol_2.setImageResource(R.mipmap.granolacapsole);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("calciumpill")) {
                pill_symbol_2.setImageResource(R.mipmap.calciumpill);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("rectangularpill")) {
                pill_symbol_2.setImageResource(R.mipmap.rectangularpill);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("ointment")) {
                pill_symbol_2.setImageResource(R.mipmap.ointment);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("dropper")) {
                pill_symbol_2.setImageResource(R.mipmap.dropper);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("inhaler")) {
                pill_symbol_2.setImageResource(R.mipmap.inhaler);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("syringe")) {
                pill_symbol_2.setImageResource(R.mipmap.syringe);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("syrup")) {
                pill_symbol_2.setImageResource(R.mipmap.syrup);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("spray")) {
                pill_symbol_2.setImageResource(R.mipmap.spray);
            } else if (pill_symbol_2 != null && med2_symbol != null && med2_symbol.equals("bloodsugar")) {
                pill_symbol_2.setImageResource(R.mipmap.bloodsugar);
            }
            //
            //
            //
            Boolean med2_alarm = pref.getBoolean("med2-alarm", false);
            //

            //show alarm or not depending on the time of the day
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med2_alarm) {
                //show the bell image
                med2_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //

            //
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med2", currentHour, currentMinute)) {
                med2_alarm_imageview.setVisibility(View.VISIBLE);
            }

            //

            if (med2_alarm) {

                alarm_time_med_2_text_view.setVisibility(View.VISIBLE);
                //

                //print the time if IT HAS ALARM
                int hour = pref.getInt("med2-hour", 0);
                Log.v("myapp", "med2-hour from shared pref is:" + hour);
                int minute = pref.getInt("med2-minute", 0);
                Log.v("myapp", "med2-minute from shared pref is:" + minute);
                //

                //
                alarm_time_med_2_text_view.setText(hour + ":" +  cook_minute(minute) );
                //

                if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {
                    //
                    alarm_time_med_2_text_view.setBackgroundColor(Color.RED);
                    med2_text_view.setBackgroundColor(Color.RED);
                    //
                    play_alarm_red_text(getActivity());
                    //

                    //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                            Date date = new Date();
//                            calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);

                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);

                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med2-hour", 0);
                            Log.v("myapp", "med1-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med2-minute", 0);
                            Log.v("myapp", "med1-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && ((currentMinute > (minute + 2)))) || (currentHour != hour)) {
                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_2_text_view.setBackgroundColor(Color.WHITE);
                                med2_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med2_alarm_imageview.setVisibility(View.GONE);

                            }
                            //

                        }
                    };

                    //

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                    //

                    //
                }
            }
            //

            //
            //
            final String med3 = pref.getString("med3", null);
            if (med3 != null) {
                med3_text_view.setText(med3);
            }
            //

            //

            //
            final String med3_symbol = pref.getString("med3-symbol", null);
            if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("capsole")) {
                pill_symbol_3.setImageResource(R.mipmap.capsole);

            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("regularRoundPill")) {
                pill_symbol_3.setImageResource(R.mipmap.regularroundpill);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("granolacapsole")) {
                pill_symbol_3.setImageResource(R.mipmap.granolacapsole);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("calciumpill")) {
                pill_symbol_3.setImageResource(R.mipmap.calciumpill);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("rectangularpill")) {
                pill_symbol_3.setImageResource(R.mipmap.rectangularpill);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("ointment")) {
                pill_symbol_3.setImageResource(R.mipmap.ointment);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("dropper")) {
                pill_symbol_3.setImageResource(R.mipmap.dropper);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("inhaler")) {
                pill_symbol_3.setImageResource(R.mipmap.inhaler);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("syringe")) {
                pill_symbol_3.setImageResource(R.mipmap.syringe);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("syrup")) {
                pill_symbol_3.setImageResource(R.mipmap.syrup);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("spray")) {
                pill_symbol_3.setImageResource(R.mipmap.spray);
            } else if (pill_symbol_3 != null && med3_symbol != null && med3_symbol.equals("bloodsugar")) {
                pill_symbol_3.setImageResource(R.mipmap.bloodsugar);
            }
            //

            //
            Boolean med3_alarm = pref.getBoolean("med3-alarm", false);
            //

            //show alarm or not depending on the time of the day
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med3_alarm) {
                //show the bell image
                med3_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //

            //
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med3", currentHour, currentMinute)) {
                med3_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //

            //
            if (med3_alarm) {
                //
                alarm_time_med_3_text_view.setVisibility(View.VISIBLE);
                //

                //print the time if IT HAS ALARM
                int hour = pref.getInt("med3-hour", 0);
                Log.v("myapp", "med3-hour from shared pref is:" + hour);
                int minute = pref.getInt("med3-minute", 0);
                Log.v("myapp", "med3-minute from shared pref is:" + minute);
                //

                //
                alarm_time_med_3_text_view.setText(hour + ":" +  cook_minute(minute) );
                //

                if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {
                    //
                    alarm_time_med_3_text_view.setBackgroundColor(Color.RED);
                    med3_text_view.setBackgroundColor(Color.RED);
                    //
                    play_alarm_red_text(getActivity());
                    //

                    //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                            Date date = new Date();
//                            calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);

                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);

                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med3-hour", 0);
                            Log.v("myapp", "med3-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med3-minute", 0);
                            Log.v("myapp", "med3-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && ((currentMinute > (minute + 2)))) || (currentHour != hour)) {
                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_3_text_view.setBackgroundColor(Color.WHITE);
                                med3_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med3_alarm_imageview.setVisibility(View.GONE);

                            }
                            //

                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                    //

                    //
                }
            }
            //

            //
            final String med4 = pref.getString("med4", null);
            if (med4 != null) {
                med4_text_view.setText(med4);
            }
            //


            //
            final String med4_symbol = pref.getString("med4-symbol", null);
            if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("capsole")) {
                pill_symbol_4.setImageResource(R.mipmap.capsole);

            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("regularRoundPill")) {
                pill_symbol_4.setImageResource(R.mipmap.regularroundpill);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("granolacapsole")) {
                pill_symbol_4.setImageResource(R.mipmap.granolacapsole);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("calciumpill")) {
                pill_symbol_4.setImageResource(R.mipmap.calciumpill);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("rectangularpill")) {
                pill_symbol_4.setImageResource(R.mipmap.rectangularpill);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("ointment")) {
                pill_symbol_4.setImageResource(R.mipmap.ointment);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("dropper")) {
                pill_symbol_4.setImageResource(R.mipmap.dropper);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("inhaler")) {
                pill_symbol_4.setImageResource(R.mipmap.inhaler);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("syringe")) {
                pill_symbol_4.setImageResource(R.mipmap.syringe);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("syrup")) {
                pill_symbol_4.setImageResource(R.mipmap.syrup);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("spray")) {
                pill_symbol_4.setImageResource(R.mipmap.spray);
            } else if (pill_symbol_4 != null && med4_symbol != null && med4_symbol.equals("bloodsugar")) {
                pill_symbol_4.setImageResource(R.mipmap.bloodsugar);
            }
            //

            Boolean med4_alarm = pref.getBoolean("med4-alarm", false);
            //

            //show alarm or not depending on the time of the day
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med4_alarm) {
                //show the bell image
                med4_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //

            //
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med4", currentHour, currentMinute)) {
                med4_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //


            if (med4_alarm) {

                //
                alarm_time_med_4_text_view.setVisibility(View.VISIBLE);
                //

                //print the time if IT HAS ALARM
                int hour = pref.getInt("med4-hour", 0);
                Log.v("myapp", "med4-hour from shared pref is:" + hour);
                int minute = pref.getInt("med4-minute", 0);
                Log.v("myapp", "med4-minute from shared pref is:" + minute);
                //

                //
                alarm_time_med_4_text_view.setText(hour + ":" +  cook_minute(minute) );
                //

                if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {
                    //
                    alarm_time_med_4_text_view.setBackgroundColor(Color.RED);
                    med4_text_view.setBackgroundColor(Color.RED);
                    //
                    play_alarm_red_text(getActivity());
                    //

                    //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                            Date date = new Date();
//                            calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);

                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);

                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med4-hour", 0);
                            Log.v("myapp", "med4-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med4-minute", 0);
                            Log.v("myapp", "med4-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && ((currentMinute > (minute + 2)))) || (currentHour != hour)) {
                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_4_text_view.setBackgroundColor(Color.WHITE);
                                med4_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med4_alarm_imageview.setVisibility(View.GONE);

                            }
                            //

                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                    //

                    //
                }
            }
            //

            //
            final String med5 = pref.getString("med5", null);
            if (med5 != null) {
                med5_text_view.setText(med5);
            }
            //

            //

            //
            final String med5_symbol = pref.getString("med5-symbol", null);
            if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("capsole")) {
                pill_symbol_5.setImageResource(R.mipmap.capsole);

            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("regularRoundPill")) {
                pill_symbol_5.setImageResource(R.mipmap.regularroundpill);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("granolacapsole")) {
                pill_symbol_5.setImageResource(R.mipmap.granolacapsole);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("calciumpill")) {
                pill_symbol_5.setImageResource(R.mipmap.calciumpill);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("rectangularpill")) {
                pill_symbol_5.setImageResource(R.mipmap.rectangularpill);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("ointment")) {
                pill_symbol_5.setImageResource(R.mipmap.ointment);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("dropper")) {
                pill_symbol_5.setImageResource(R.mipmap.dropper);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("inhaler")) {
                pill_symbol_5.setImageResource(R.mipmap.inhaler);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("syringe")) {
                pill_symbol_5.setImageResource(R.mipmap.syringe);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("syrup")) {
                pill_symbol_5.setImageResource(R.mipmap.syrup);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("spray")) {
                pill_symbol_5.setImageResource(R.mipmap.spray);
            } else if (pill_symbol_5 != null && med5_symbol != null && med5_symbol.equals("bloodsugar")) {
                pill_symbol_5.setImageResource(R.mipmap.bloodsugar);
            }
            //

            //
            //
            Boolean med5_alarm = pref.getBoolean("med5-alarm", false);
            //

            //show alarm or not depending on the time of the day
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) < Integer.valueOf(MainActivity.whichDay.substring(3)) && med5_alarm) {
                //show the bell image
                med5_alarm_imageview.setVisibility(View.VISIBLE);
            }

            //

            //
            if (Integer.valueOf(MainActivity.todayIs.substring(3)) == Integer.valueOf(MainActivity.whichDay.substring(3)) && show_alarm_image_or_not(getActivity(), MainActivity.whichDay + "-am", "med5", currentHour, currentMinute)) {
                med5_alarm_imageview.setVisibility(View.VISIBLE);
            }
            //

            if (med5_alarm) {

                //
                alarm_time_med_5_text_view.setVisibility(View.VISIBLE);
                //

                //print the time if IT HAS ALARM
                int hour = pref.getInt("med5-hour", 0);
                Log.v("myapp", "med5-hour from shared pref is:" + hour);
                int minute = pref.getInt("med5-minute", 0);
                Log.v("myapp", "med5-minute from shared pref is:" + minute);
                //

                //
                alarm_time_med_5_text_view.setText(hour + ":" +  cook_minute(minute) );
                //

                if (hour == currentHour && ((currentMinute > (minute - 4)) && (currentMinute < (minute + 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {
                    //
                    alarm_time_med_5_text_view.setBackgroundColor(Color.RED);
                    med5_text_view.setBackgroundColor(Color.RED);
                    //
                    play_alarm_red_text(getActivity());
                    //

                    //MAKE "alarm_time_med_1_text_view" TEXT VIEW WHITE AGAIN if it is red
                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                            Date date = new Date();
//                            calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);

                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);

                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med5-hour", 0);
                            Log.v("myapp", "med5-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med5-minute", 0);
                            Log.v("myapp", "med5-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && ((currentMinute > (minute + 2)))) || (currentHour != hour)) {
                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_5_text_view.setBackgroundColor(Color.WHITE);
                                med5_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med5_alarm_imageview.setVisibility(View.GONE);

                            }
                            //
                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 6 * 60000);
                    //

                    //
                }
                //
            }
            //

            //
            new MakeRedOperation(getActivity()).execute();
            //


        //end of checking for onCreateRan boolean
        }


        //preventing inneccessary repeat
        onCreateRan = true;
        //

    //end of onResume
    }
    //


    //


    //


    public Boolean show_alarm_image_or_not(Context ctx, String day_time, String med_number, int currentHour, int currentMinute) {
        //
        Boolean show_alarm = false;
        //
        final SharedPreferences pref = ctx.getSharedPreferences(day_time, MODE_PRIVATE);
        //
        int pref_hour = pref.getInt(med_number + "-hour", 0);
        int pref_minute = pref.getInt(med_number + "-minute", 0);
        //
        Log.v("myapp", "pref_hour:" + pref_hour);
        Log.v("myapp", "pref_minute:" + pref_minute);
        //
        if (pref_hour == currentHour && pref_minute < currentMinute) {
            show_alarm = false;

        } else if (pref_hour == currentHour && pref_minute > currentMinute) {

            show_alarm = true;

        }
        //
        if (pref_hour > currentHour) {
            show_alarm = true;
        }
        //
        if (pref_hour < currentHour) {
            show_alarm = false;
        }
        //
        Log.v("myapp", "show_alarm:" + show_alarm);
        return show_alarm;
        //

    }
    //

    //

    public class MakeRedOperation extends AsyncTask<Void, Integer, String> {

        //
        //Date date = new Date();

        SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
        TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));
        final Calendar calendar = Calendar.getInstance(tz);

        //
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int currentMinute = calendar.get(Calendar.MINUTE);
        //

        //
        Boolean makeOrange_med1 = false;
        Boolean makeOrange_med2 = false;
        Boolean makeOrange_med3 = false;
        Boolean makeOrange_med4 = false;
        Boolean makeOrange_med5 = false;
        //

        //
        Context ctx;
        //

        //
        public MakeRedOperation(Context contex) {
            this.ctx = contex;
        }
        //

        //
        @Override
        protected void onPreExecute() {

            //calendar.setTime(date);

        }

        @Override
        protected String doInBackground(Void... params) {
            //

            //
            //final MediaPlayer mp = MediaPlayer.create(ctx, R.raw.music1);
            //

            //
            SharedPreferences pref = ctx.getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE);
            //

            //
            for (int i = 1; i < 6; i++) {

                //
                Boolean med_alarm = false;
                med_alarm = pref.getBoolean("med" + i + "-alarm", false);
                //

                //
                if (med_alarm) {

                    //
                    int hour = pref.getInt("med" + i + "-hour", 0);
                    int minute = pref.getInt("med" + i + "-minute", 0);
                    //

                    //

                    // NOW i need todayIs - very important
                    if (hour == currentHour && ((currentMinute >= (minute - 14)) && (currentMinute <= (minute - 4))) && MainActivity.todayIs.equals(MainActivity.whichDay)) {

                        //
                        switch (i) {
                            case 1:

                                makeOrange_med1 = true;
                                //
                                //mp.start();
                                play_alarm_orange_text(getActivity());
                                //

                                break;
                            case 2:

                                makeOrange_med2 = true;
                                //
                                //mp.start();
                                play_alarm_orange_text(getActivity());
                                //

                                break;
                            case 3:

                                makeOrange_med3 = true;
                                //
                                //mp.start();
                                play_alarm_orange_text(getActivity());
                                //

                                break;
                            case 4:

                                makeOrange_med4 = true;
                                //
                                //mp.start();
                                play_alarm_orange_text(getActivity());
                                //

                                break;
                            case 5:

                                makeOrange_med5 = true;
                                //
                                //mp.start();
                                play_alarm_orange_text(getActivity());
                                //

                                break;
                            default:

                                //

                                break;
                        }


                    }
                }


                //end of for loop
            }

            //
            return "inPostExecute";
            //

        }

        protected void onProgressUpdate(Integer... a) {

        }

        protected void onPostExecute(String result) {

            //
            if (getActivity() != null) {

                //

                final SharedPreferences pref = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE);

                //final SharedPreferences pref = getActivity().getSharedPreferences(MainActivity.whichDay + "-am", MODE_PRIVATE);

                //
                if (makeOrange_med1) {
                    alarm_time_med_1_text_view.setBackgroundColor(Color.parseColor("#FFA500"));
                    //

                    //make it white again
                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);                         TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));                         final Calendar calendar = Calendar.getInstance(tz);
//                            Date date = new Date();
//                            calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);
                            //
                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);
                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med1-hour", 0);
                            Log.v("myapp", "med1-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med1-minute", 0);
                            Log.v("myapp", "med1-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && (currentMinute >= (minute + 26))) || (hour != currentHour)) {

                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_1_text_view.setBackgroundColor(Color.WHITE);
                                med1_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med1_alarm_imageview.setVisibility(View.GONE);

                            }
                            //
                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 26 * 60000);
                    //

                    //
                }
                //
                //
                if (makeOrange_med2) {
                    alarm_time_med_2_text_view.setBackgroundColor(Color.parseColor("#FFA500"));

                    //


                    //make it white again

                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref_0 = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
                            TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));
                            final Calendar calendar = Calendar.getInstance(tz);
//                            Date date = new Date();
//                            calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);
                            //
                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);
                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med1-hour", 0);
                            Log.v("myapp", "med1-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med1-minute", 0);
                            Log.v("myapp", "med1-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && (currentMinute >= (minute + 26))) || (hour != currentHour)) {

                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_2_text_view.setBackgroundColor(Color.WHITE);
                                med2_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med2_alarm_imageview.setVisibility(View.GONE);
                                //
                            }
                            //
                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 26 * 60000);
                    //


                    //
                }
                //
                //
                if (makeOrange_med3) {
                    alarm_time_med_3_text_view.setBackgroundColor(Color.parseColor("#FFA500"));

                    //

                    //make it white again

                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref_0 = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
                            TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));
                            final Calendar calendar = Calendar.getInstance(tz);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);
                            //
                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);
                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med1-hour", 0);
                            Log.v("myapp", "med1-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med1-minute", 0);
                            Log.v("myapp", "med1-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && (currentMinute >= (minute + 26))) || (hour != currentHour)) {

                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_3_text_view.setBackgroundColor(Color.WHITE);
                                med3_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med3_alarm_imageview.setVisibility(View.GONE);

                            }
                            //
                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 26 * 60000);
                    //


                    //
                }
                //
                //
                if (makeOrange_med4) {
                    alarm_time_med_4_text_view.setBackgroundColor(Color.parseColor("#FFA500"));

                    //

                    //make it white again

                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref_0 = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
                            TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));
                            final Calendar calendar = Calendar.getInstance(tz);

                            //Date date = new Date();
                            //calendar.setTime(date);

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);
                            //
                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);
                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med1-hour", 0);
                            Log.v("myapp", "med1-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med1-minute", 0);
                            Log.v("myapp", "med1-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && (currentMinute >= (minute + 26))) || (hour != currentHour)) {

                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_4_text_view.setBackgroundColor(Color.WHITE);
                                med4_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med4_alarm_imageview.setVisibility(View.GONE);

                            }
                            //
                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 26 * 60000);
                    //

                    //
                }
                //
                //
                if (makeOrange_med5) {
                    alarm_time_med_5_text_view.setBackgroundColor(Color.parseColor("#FFA500"));

                    //


                    //make it white again

                    Handler mVolHandler = new Handler();
                    Runnable mVolRunnable = new Runnable() {
                        public void run() {
                            //

                            //
                            SharedPreferences pref_0 = getActivity().getSharedPreferences("initialtimezone", MODE_PRIVATE);
                            TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));
                            final Calendar calendar = Calendar.getInstance(tz);
                            //

                            //Date date = new Date();
                            //calendar.setTime(date);
                            //

                            //
                            Log.v("myap", "handler ran");
                            //
                            currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                            Log.v("myapp", "current time (hour) " + currentHour);
                            //
                            currentMinute = calendar.get(Calendar.MINUTE);
                            Log.v("myapp", "current time (minute) " + currentMinute);
                            //

                            // I had to get values from sharedpref anyways
                            int hour = pref.getInt("med1-hour", 0);
                            Log.v("myapp", "med1-hour from shared pref is:" + hour);
                            int minute = pref.getInt("med1-minute", 0);
                            Log.v("myapp", "med1-minute from shared pref is:" + minute);
                            //

                            //
                            if ((hour == currentHour && (currentMinute >= (minute + 26))) || (hour != currentHour)) {

                                Log.v("myap", "handler ran & insde if statement to make text_1 white");

                                alarm_time_med_5_text_view.setBackgroundColor(Color.WHITE);
                                med5_text_view.setBackgroundColor(Color.WHITE);
                                //
                                med5_alarm_imageview.setVisibility(View.GONE);
                                //
                            }
                            //
                        }
                    };

                    //
                    //mVolHandler.removeCallbacks(mVolRunnable);
                    mVolHandler.postDelayed(mVolRunnable, 26 * 60000);
                    //


                    //
                }


                //
            }

            //

        }

        //end of day1_am fragment

    }

    //
    public void play_alarm_red_text(Context ctx) {

        //
        MediaPlayer mp = MediaPlayer.create(ctx, R.raw.music1);
        mp.start();

        //
        long current_tme = System.currentTimeMillis();
        while (System.currentTimeMillis() < current_tme + 3000) {
            //
        }
        //

        if (mp.isPlaying()) {
            mp.stop();
        }

    }
    //

    //

    //

    //
    public void play_alarm_orange_text(Context ctx) {

        //
        MediaPlayer mp = MediaPlayer.create(ctx, R.raw.music1);
        mp.start();

        //
        long current_tme = System.currentTimeMillis();
        while (System.currentTimeMillis() < current_tme + 3000) {
            //
        }
        //

        if (mp.isPlaying()) {
            mp.stop();
        }

    }
    //

    //
    public String cook_minute (int minute)
    {
        String cooked_minute=null;
        //
        if(minute < 10)
        {
            cooked_minute = "0" + minute;
        }else
        {
            cooked_minute = "" + minute;
        }
        //
        return cooked_minute;
    }

    //end of fragment

}
    //








