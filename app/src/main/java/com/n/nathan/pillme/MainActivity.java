package com.n.nathan.pillme;

import android.animation.ValueAnimator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    //
    FrameLayout frameLayout;
    FrameLayout frameLayout_bottom;
    FrameLayout frameLayout_main;
    //

    //
    ImageButton NextDayButton;
    ImageButton PreviousDayButton;
    //

    //
    public static String whichDay = null;
    public static String todayIs = null;
    public static String AM_PM = null;
    //

    //
    ImageView bg; //= (ImageView) findViewById(R.id.bg);
    ImageView bg1; //= (ImageView) findViewById(R.id.bg1);
    //

    //
    TextView middle_textview;
    //

    //test
    VideoView videoView;
    //
    Boolean timeZoneChanged = false;
    Boolean show_warning = false;
    //

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //

        //
        frameLayout_main = (FrameLayout) findViewById(R.id.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout_top);
        frameLayout_bottom = (FrameLayout) findViewById(R.id.framelayout_bottom);
        //
        NextDayButton = (ImageButton) findViewById(R.id.nextDayButton);
        PreviousDayButton = (ImageButton) findViewById(R.id.previousDayButton);
        //
        bg = (ImageView) findViewById(R.id.bg1);
        bg1 = (ImageView) findViewById(R.id.bg2);
        //
        middle_textview = (TextView) findViewById(R.id.middle_textview);
        //

        //
        //START OF SHOWING TUTORIAL
        SharedPreferences pref_ = getSharedPreferences("show_tutorial", MODE_PRIVATE);
        //Retrieve the values
        String tutorial_number = pref_.getString("tutorial_number", Integer.toString(0));

        //ToDO change this number later
        if ( Integer.parseInt(tutorial_number) <= 1 ) {

            //show activity
            //
            Intent intent = new Intent(this, TutorialActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //
            Log.v("myapp", "tutoriual number: " + tutorial_number);
            //incerment tutorial number
            SharedPreferences.Editor editor = getSharedPreferences("show_tutorial", MODE_PRIVATE).edit();
            int tutorial_number_incremented = Integer.parseInt(tutorial_number) + 1;
            editor.putString("tutorial_number", Integer.toString(tutorial_number_incremented));
            editor.commit();
        }
        //END OF SHOWING TUTORIA
        //

        //start of which day to load

        //Get CURRENT day, month, and year - curren time
        SharedPreferences pref = getSharedPreferences("initialtimezone", MODE_PRIVATE);
        TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(tz);

        Log.v("myapp"," calendar.get(Calendar.DAY_OF_MONTH); " + calendar.get(Calendar.DAY_OF_MONTH));
        Log.v("myapp"," calendar.get(Calendar.MONTH); " + calendar.get(Calendar.MONTH));
        Log.v("myapp"," calendar.get(Calendar.YEAR); " + calendar.get(Calendar.YEAR));

        Date date_1 = calendar.getTime();

        // change or remove
        Log.v("Mainactivity", "calendar.getTimeInMillis() using initial TZ: " + calendar.getTimeInMillis() );
        //


//        //educational
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        // Todo not currently used
//        if (hour == 0) {
//            AM_PM = "AM";
//        } else if (hour < 12) {
//            AM_PM = "AM";
//        } else if (hour == 12) {
//            AM_PM = "PM";
//        } else {
//            AM_PM = "PM";
//        }

//        int curr_year =  calendar_1.get(Calendar.YEAR);
//        int curr_month = calendar_1.get(Calendar.MONTH);
//        int curr_day = calendar_1.get(Calendar.DAY_OF_MONTH);

        //get first day of our plan from shared pref
        SharedPreferences pref_1 = getSharedPreferences("startday", MODE_PRIVATE);
        int sharedpref_year = pref_1.getInt("year", 0);
        int sharedpref_month = pref_1.getInt("month", 0);
        int sharedpref_day = pref_1.getInt("day", 0);
        //
        Log.v("Mainactivity", "in MainActivity, values from shared pref: day,month,year is: " + sharedpref_day + " " + sharedpref_month + " " + sharedpref_year);
        //
        Calendar cal = Calendar.getInstance();
        //months is ZERO based in JAVA //create date object
        cal.set(sharedpref_year, sharedpref_month - 1, sharedpref_day);
        //new line added
        cal.setTimeZone(tz);
        //
        Date date_2 = cal.getTime();
        Log.v("myapp"," cal.get(Calendar.DAY_OF_MONTH); " + cal.get(Calendar.DAY_OF_MONTH));
        Log.v("myapp"," cal.get(Calendar.MONTH); " + cal.get(Calendar.MONTH));
        Log.v("myapp"," cal.get(Calendar.YEAR); " + cal.get(Calendar.YEAR));
        //
        //Log.v("Mainactivity", " date_1.compareTo(date_2) : " + date_1.equals(date_2) );
        Log.v("Mainactivity", " calendar.getTimeInMillis() equivalent! for date_2.getTime() is: " + date_2.getTime() );
        //
        Log.v("Mainactivity","(calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==1) is: " + (calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH)) );

        //
        if( ( (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) && calendar.get(Calendar.DAY_OF_MONTH) < cal.get(Calendar.DAY_OF_MONTH) ) || ( (calendar.get(Calendar.MONTH) < cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) && calendar.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH) ) ||  ( (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) < cal.get(Calendar.YEAR) ) && calendar.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH) )       )
        {

            //
            Toast.makeText(this, "Your plan has not started yet!", Toast.LENGTH_LONG).show();
            //

            //today is noday!
            todayIs = "plan not yet started";
            whichDay = "plan not yet started";
            //

        }else if( (calendar.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) && (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {

            //Day1
            todayIs = "day1";
            //

            //
            day1_am newFragment = new day1_am();
            day1_pm newFragment_bottom = new day1_pm();
            //

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout_top, newFragment);
            transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

            //transaction.addToBackStack(null);

            transaction.commit();

            //
            whichDay = "day1";
            //
        }
        else if( ((calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==1) && (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {

            //Day 2
            todayIs = "day2";
            //
            day1_am newFragment = new day1_am();
            day1_pm newFragment_bottom = new day1_pm();
            //

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout_top, newFragment);
            transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

            //transaction.addToBackStack(null);

            transaction.commit();
            //
            whichDay = "day2";
            //
        }else if( ((calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==2) && (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
        //
            //Day3
            todayIs = "day3";
            //

            //FORMULA
            //day number = hours/24 + 1
            //
            day1_am newFragment = new day1_am();
            day1_pm newFragment_bottom = new day1_pm();
            //

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout_top, newFragment);
            transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

            //transaction.addToBackStack(null);

            transaction.commit();
            //

            //
            whichDay = "day3";
            //
        }else if( ((calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==3) && (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {

            //Day4
            todayIs = "day4";
            //

            //FORMULA
            //day number = hours/24 + 1
            //

            //
            day1_am newFragment = new day1_am();
            day1_pm newFragment_bottom = new day1_pm();
            //

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout_top, newFragment);
            transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

            //transaction.addToBackStack(null);

            transaction.commit();
            //

            //
            whichDay = "day4";
            //
        }else if( ((calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==4) && (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day5
            todayIs = "day5";
            //

            //FORMULA
            //day number = hours/24 + 1
            //

            //
            day1_am newFragment = new day1_am();
            day1_pm newFragment_bottom = new day1_pm();
            //

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout_top, newFragment);
            transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

            //transaction.addToBackStack(null);

            transaction.commit();
            //

            //
            whichDay = "day5";
            //
        }else if( ((calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==5) && (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day6
            todayIs = "day6";
            //

            //FORMULA
            //day number = hours/24 + 1
            //

            //
            day1_am newFragment = new day1_am();
            day1_pm newFragment_bottom = new day1_pm();
            //

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout_top, newFragment);
            transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

            //transaction.addToBackStack(null);

            transaction.commit();
            //

            //
            whichDay = "day6";
            //
        }else if( ((calendar.get(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH))==6) && (calendar.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && ( calendar.get(Calendar.YEAR) == cal.get(Calendar.YEAR) ) )
        {
            //Day7
            todayIs = "day7";
            //
            day1_am newFragment = new day1_am();
            day1_pm newFragment_bottom = new day1_pm();
            //

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.framelayout_top, newFragment);
            transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

            //transaction.addToBackStack(null);

            transaction.commit();
            //
            whichDay = "day7";
            //
        }
        else
        {
            //
            Toast.makeText(this, "Your plan has finished", Toast.LENGTH_LONG).show();
            //

            //
            todayIs = "plan not yet started";
            whichDay = "plan not yet started";
        }
        //
        middle_textview.setText(whichDay);
        //

        //end of which day to laod

        //
        //Note: WHICHDAY changes with navigation but TODAYIS does not
        //

        //
        NextDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GO TO NEXT DAY
                if (whichDay.equals("day1")) {

                    //go to day2

                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);

                    transaction.commit();
                    //
                    whichDay = "day2";
                    //

                } else if (whichDay.equals("day2")) {
                    //
                    //go to day3
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);

                    transaction.commit();
                    //

                    //
                    whichDay = "day3";
                    //

                } else if (whichDay.equals("day3")) {
                    //
                    //go to day4
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day4";
                    //

                } else if (whichDay.equals("day4")) {
                    //
                    //go to day5
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day5";
                    //

                } else if (whichDay.equals("day5")) {
                    //
                    //go to day6
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day6";
                    //

                } else if (whichDay.equals("day6")) {
                    //
                    //go to day7
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day7";
                    //

                } else if (whichDay.equals("day7")) {
                    //
                    //go to day1
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day1";
                    //

                }

                //
                middle_textview.setText(whichDay);
                //

            }
        });
        //

        //
        PreviousDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GO TO PREVIOUS DAY

                if (whichDay.equals("day1")) {
                    //
                    //go to day7
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //
                    whichDay = "day7";
                    //

                } else if (whichDay.equals("day2")) {
                    //
                    //go to day1
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day1";
                    //

                } else if (whichDay.equals("day3")) {
                    //
                    //go to day2
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day2";
                    //

                } else if (whichDay.equals("day4")) {
                    //
                    //go to day3
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day3";
                    //

                } else if (whichDay.equals("day5")) {
                    //
                    //go to day4
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day4";
                    //

                } else if (whichDay.equals("day6")) {
                    //
                    //go to day5
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day5";
                    //

                } else if (whichDay.equals("day7")) {
                    //
                    //go to day6
                    //
                    day1_am newFragment = new day1_am();
                    day1_pm newFragment_bottom = new day1_pm();
                    //

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.framelayout_top, newFragment);
                    transaction.replace(R.id.framelayout_bottom, newFragment_bottom);

                    //transaction.addToBackStack(null);


                    transaction.commit();
                    //

                    //
                    whichDay = "day6";
                    //

                }

                //
                middle_textview.setText(whichDay);
                //
            }
        });

        //
        //creating background animation
        //
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(60000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = bg.getWidth();
                final float translationX = width * progress;
                bg.setTranslationX(translationX);
                bg1.setTranslationX(translationX - width);
            }
        });
        animator.start();
        //

        // SAME CODE APPLIED TO ONRESUME
        Handler mVolHandler = new Handler();
        Runnable mVolRunnable = new Runnable() {
            public void run()
            {

                //cancel notifications after osme time
                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.cancelAll();
                //
            }
        };

        //
        //mVolHandler.removeCallbacks(mVolRunnable);
        mVolHandler.postDelayed(mVolRunnable, 1 * 60000);
        //

        // double-schehdules! -FIX IT- - if it is today and it is past due do not rescehdule! ToDO
        //RUN SERVICE - is it neccessary here? kind of yes!

        //
        Intent intent = new Intent(this,RescheduleAlarms.class);
        startService(intent);
        //

        //end of onCreate
    }
    //

    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }
    //

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.save_seven_days)
        {
            //
            save_day1_am();
            //
        }

        if (id == R.id.load_seven_days)
        {
            //
            load_day1_am();
            //
        }

        if (id == R.id.restart)
        {
            //
            restart();
            //
        }

        return false;

    }
    //

    //
    public void save_day1_am(){

        //
        if(there_is_no_data_to_save()){
            Toast.makeText(getApplicationContext(),"There is no entry to save",Toast.LENGTH_SHORT).show();
        }else {

            //
            DB_day1_am db = new DB_day1_am(getApplicationContext(), null);
            db.addMeds();
            //
            DB_day1_pm db1 = new DB_day1_pm(getApplicationContext(), null);
            db1.addMeds();
            //
            DB_day2_am db2 = new DB_day2_am(getApplicationContext(), null);
            db2.addMeds();
            //
            DB_day2_pm db3 = new DB_day2_pm(getApplicationContext(), null);
            db3.addMeds();
            //
            DB_day3_am db4 = new DB_day3_am(getApplicationContext(), null);
            db4.addMeds();
            //
            DB_day3_pm db5 = new DB_day3_pm(getApplicationContext(), null);
            db5.addMeds();
            //
            DB_day4_am db6 = new DB_day4_am(getApplicationContext(), null);
            db6.addMeds();
            //
            DB_day4_pm db7 = new DB_day4_pm(getApplicationContext(), null);
            db7.addMeds();
            //
            DB_day5_am db8 = new DB_day5_am(getApplicationContext(), null);
            db8.addMeds();
            //
            DB_day5_pm db9 = new DB_day5_pm(getApplicationContext(), null);
            db9.addMeds();
            //
            DB_day6_am db10 = new DB_day6_am(getApplicationContext(), null);
            db10.addMeds();
            //
            DB_day6_pm db11 = new DB_day6_pm(getApplicationContext(), null);
            db11.addMeds();
            //
            DB_day7_am db12 = new DB_day7_am(getApplicationContext(), null);
            db12.addMeds();
            //
            DB_day7_pm db13 = new DB_day7_pm(getApplicationContext(), null);
            db13.addMeds();
            //

            //
            Toast.makeText(getApplicationContext(),"7-day plan saved!",Toast.LENGTH_SHORT).show();
            //

        }
        //

    }
    //

    //
    public void restart(){
        //

//        //
//        SharedPreferences pref_1 = getApplicationContext().getSharedPreferences("savedPlanExists", Context.MODE_PRIVATE);
//        Boolean savedPlanExists = pref_1.getBoolean("savedPlanExists", false );
//        //
//
//        //
//        if (savedPlanExists)
//        {
//
//        }
//        //

        //
        SharedPreferences.Editor pref1_am = getApplicationContext().getSharedPreferences("day1-am",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref1_pm = getApplicationContext().getSharedPreferences("day1-pm",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref2_am = getApplicationContext().getSharedPreferences("day2-am",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref2_pm = getApplicationContext().getSharedPreferences("day2-pm",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref3_am = getApplicationContext().getSharedPreferences("day3-am",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref3_pm = getApplicationContext().getSharedPreferences("day3-pm",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref4_am = getApplicationContext().getSharedPreferences("day4-am",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref4_pm = getApplicationContext().getSharedPreferences("day4-pm",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref5_am = getApplicationContext().getSharedPreferences("day5-am",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref5_pm = getApplicationContext().getSharedPreferences("day5-pm",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref6_am = getApplicationContext().getSharedPreferences("day6-am",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref6_pm = getApplicationContext().getSharedPreferences("day6-pm",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref7_am = getApplicationContext().getSharedPreferences("day7-am",MODE_PRIVATE).edit();
        SharedPreferences.Editor pref7_pm = getApplicationContext().getSharedPreferences("day7-pm",MODE_PRIVATE).edit();
        //

        //
        // SET EVERYTHING TO NULL
        //

        //1_am
        pref1_am.putString("med1-symbol",null);
        pref1_am.putString("med1",null);
        pref1_am.putInt("med1-hour",0);
        pref1_am.putInt("med1-minute",0);
        pref1_am.putBoolean("med1-alarm",false);
        //
        pref1_am.putString("med2-symbol",null);
        pref1_am.putString("med2",null);
        pref1_am.putInt("med2-hour",0);
        pref1_am.putInt("med2-minute",0);
        pref1_am.putBoolean("med2-alarm",false);
        //
        pref1_am.putString("med3-symbol",null);
        pref1_am.putString("med3",null);
        pref1_am.putInt("med3-hour",0);
        pref1_am.putInt("med3-minute",0);
        pref1_am.putBoolean("med3-alarm",false);
        //
        pref1_am.putString("med4-symbol",null);
        pref1_am.putString("med4",null);
        pref1_am.putInt("med4-hour",0);
        pref1_am.putInt("med4-minute",0);
        pref1_am.putBoolean("med4-alarm",false);
        //
        pref1_am.putString("med5-symbol",null);
        pref1_am.putString("med5",null);
        pref1_am.putInt("med5-hour",0);
        pref1_am.putInt("med5-minute",0);
        pref1_am.putBoolean("med5-alarm",false);
        //


        //1_pm
        pref1_pm.putString("med1-symbol",null);
        pref1_pm.putString("med1",null);
        pref1_pm.putInt("med1-hour",0);
        pref1_pm.putInt("med1-minute",0);
        pref1_pm.putBoolean("med1-alarm",false);
        //
        pref1_pm.putString("med2-symbol",null);
        pref1_pm.putString("med2",null);
        pref1_pm.putInt("med2-hour",0);
        pref1_pm.putInt("med2-minute",0);
        pref1_pm.putBoolean("med2-alarm",false);
        //
        pref1_pm.putString("med3-symbol",null);
        pref1_pm.putString("med3",null);
        pref1_pm.putInt("med3-hour",0);
        pref1_pm.putInt("med3-minute",0);
        pref1_pm.putBoolean("med3-alarm",false);
        //
        pref1_pm.putString("med4-symbol",null);
        pref1_pm.putString("med4",null);
        pref1_pm.putInt("med4-hour",0);
        pref1_pm.putInt("med4-minute",0);
        pref1_pm.putBoolean("med4-alarm",false);
        //
        pref1_pm.putString("med5-symbol",null);
        pref1_pm.putString("med5",null);
        pref1_pm.putInt("med5-hour",0);
        pref1_pm.putInt("med5-minute",0);
        pref1_pm.putBoolean("med5-alarm",false);
        //

        //2_am
        pref2_am.putString("med1-symbol",null);
        pref2_am.putString("med1",null);
        pref2_am.putInt("med2-hour",0);
        pref2_am.putInt("med1-minute",0);
        pref2_am.putBoolean("med1-alarm",false);
        //
        pref2_am.putString("med2-symbol",null);
        pref2_am.putString("med2",null);
        pref2_am.putInt("med2-hour",0);
        pref2_am.putInt("med2-minute",0);
        pref2_am.putBoolean("med2-alarm",false);
        //
        pref2_am.putString("med3-symbol",null);
        pref2_am.putString("med3",null);
        pref2_am.putInt("med3-hour",0);
        pref2_am.putInt("med3-minute",0);
        pref2_am.putBoolean("med3-alarm",false);
        //
        pref2_am.putString("med4-symbol",null);
        pref2_am.putString("med4",null);
        pref2_am.putInt("med4-hour",0);
        pref2_am.putInt("med4-minute",0);
        pref2_am.putBoolean("med4-alarm",false);
        //
        pref2_am.putString("med5-symbol",null);
        pref2_am.putString("med5",null);
        pref2_am.putInt("med5-hour",0);
        pref2_am.putInt("med5-minute",0);
        pref2_am.putBoolean("med5-alarm",false);
        //

        //2_pm
        pref2_pm.putString("med1-symbol",null);
        pref2_pm.putString("med1",null);
        pref2_pm.putInt("med2-hour",0);
        pref2_pm.putInt("med1-minute",0);
        pref2_pm.putBoolean("med1-alarm",false);
        //
        pref2_pm.putString("med2-symbol",null);
        pref2_pm.putString("med2",null);
        pref2_pm.putInt("med2-hour",0);
        pref2_pm.putInt("med2-minute",0);
        pref2_pm.putBoolean("med2-alarm",false);
        //
        pref2_pm.putString("med3-symbol",null);
        pref2_pm.putString("med3",null);
        pref2_pm.putInt("med3-hour",0);
        pref2_pm.putInt("med3-minute",0);
        pref2_pm.putBoolean("med3-alarm",false);
        //
        pref2_pm.putString("med4-symbol",null);
        pref2_pm.putString("med4",null);
        pref2_pm.putInt("med4-hour",0);
        pref2_pm.putInt("med4-minute",0);
        pref2_pm.putBoolean("med4-alarm",false);
        //
        pref2_pm.putString("med5-symbol",null);
        pref2_pm.putString("med5",null);
        pref2_pm.putInt("med5-hour",0);
        pref2_pm.putInt("med5-minute",0);
        pref2_pm.putBoolean("med5-alarm",false);
        //


        //3_am
        pref3_am.putString("med1-symbol",null);
        pref3_am.putString("med1",null);
        pref3_am.putInt("med2-hour",0);
        pref3_am.putInt("med1-minute",0);
        pref3_am.putBoolean("med1-alarm",false);
        //
        pref3_am.putString("med2-symbol",null);
        pref3_am.putString("med2",null);
        pref3_am.putInt("med2-hour",0);
        pref3_am.putInt("med2-minute",0);
        pref3_am.putBoolean("med2-alarm",false);
        //
        pref3_am.putString("med3-symbol",null);
        pref3_am.putString("med3",null);
        pref3_am.putInt("med3-hour",0);
        pref3_am.putInt("med3-minute",0);
        pref3_am.putBoolean("med3-alarm",false);
        //
        pref3_am.putString("med4-symbol",null);
        pref3_am.putString("med4",null);
        pref3_am.putInt("med4-hour",0);
        pref3_am.putInt("med4-minute",0);
        pref3_am.putBoolean("med4-alarm",false);
        //
        pref3_am.putString("med5-symbol",null);
        pref3_am.putString("med5",null);
        pref3_am.putInt("med5-hour",0);
        pref3_am.putInt("med5-minute",0);
        pref3_am.putBoolean("med5-alarm",false);
        //

        //3_pm
        pref3_pm.putString("med1-symbol",null);
        pref3_pm.putString("med1",null);
        pref3_pm.putInt("med2-hour",0);
        pref3_pm.putInt("med1-minute",0);
        pref3_pm.putBoolean("med1-alarm",false);
        //
        pref3_pm.putString("med2-symbol",null);
        pref3_pm.putString("med2",null);
        pref3_pm.putInt("med2-hour",0);
        pref3_pm.putInt("med2-minute",0);
        pref3_pm.putBoolean("med2-alarm",false);
        //
        pref3_pm.putString("med3-symbol",null);
        pref3_pm.putString("med3",null);
        pref3_pm.putInt("med3-hour",0);
        pref3_pm.putInt("med3-minute",0);
        pref3_pm.putBoolean("med3-alarm",false);
        //
        pref3_pm.putString("med4-symbol",null);
        pref3_pm.putString("med4",null);
        pref3_pm.putInt("med4-hour",0);
        pref3_pm.putInt("med4-minute",0);
        pref3_pm.putBoolean("med4-alarm",false);
        //
        pref3_pm.putString("med5-symbol",null);
        pref3_pm.putString("med5",null);
        pref3_pm.putInt("med5-hour",0);
        pref3_pm.putInt("med5-minute",0);
        pref3_pm.putBoolean("med5-alarm",false);
        //

        //4_am
        pref4_am.putString("med1-symbol",null);
        pref4_am.putString("med1",null);
        pref4_am.putInt("med2-hour",0);
        pref4_am.putInt("med1-minute",0);
        pref4_am.putBoolean("med1-alarm",false);
        //
        pref4_am.putString("med2-symbol",null);
        pref4_am.putString("med2",null);
        pref4_am.putInt("med2-hour",0);
        pref4_am.putInt("med2-minute",0);
        pref4_am.putBoolean("med2-alarm",false);
        //
        pref4_am.putString("med3-symbol",null);
        pref4_am.putString("med3",null);
        pref4_am.putInt("med3-hour",0);
        pref4_am.putInt("med3-minute",0);
        pref4_am.putBoolean("med3-alarm",false);
        //
        pref4_am.putString("med4-symbol",null);
        pref4_am.putString("med4",null);
        pref4_am.putInt("med4-hour",0);
        pref4_am.putInt("med4-minute",0);
        pref4_am.putBoolean("med4-alarm",false);
        //
        pref4_am.putString("med5-symbol",null);
        pref4_am.putString("med5",null);
        pref4_am.putInt("med5-hour",0);
        pref4_am.putInt("med5-minute",0);
        pref4_am.putBoolean("med5-alarm",false);
        //

        //4_pm
        pref4_pm.putString("med1-symbol",null);
        pref4_pm.putString("med1",null);
        pref4_pm.putInt("med2-hour",0);
        pref4_pm.putInt("med1-minute",0);
        pref4_pm.putBoolean("med1-alarm",false);
        //
        pref4_pm.putString("med2-symbol",null);
        pref4_pm.putString("med2",null);
        pref4_pm.putInt("med2-hour",0);
        pref4_pm.putInt("med2-minute",0);
        pref4_pm.putBoolean("med2-alarm",false);
        //
        pref4_pm.putString("med3-symbol",null);
        pref4_pm.putString("med3",null);
        pref4_pm.putInt("med3-hour",0);
        pref4_pm.putInt("med3-minute",0);
        pref4_pm.putBoolean("med3-alarm",false);
        //
        pref4_pm.putString("med4-symbol",null);
        pref4_pm.putString("med4",null);
        pref4_pm.putInt("med4-hour",0);
        pref4_pm.putInt("med4-minute",0);
        pref4_pm.putBoolean("med4-alarm",false);
        //
        pref4_pm.putString("med5-symbol",null);
        pref4_pm.putString("med5",null);
        pref4_pm.putInt("med5-hour",0);
        pref4_pm.putInt("med5-minute",0);
        pref4_pm.putBoolean("med5-alarm",false);
        //

        //5-_am
        pref5_am.putString("med1-symbol",null);
        pref5_am.putString("med1",null);
        pref5_am.putInt("med2-hour",0);
        pref5_am.putInt("med1-minute",0);
        pref5_am.putBoolean("med1-alarm",false);
        //
        pref5_am.putString("med2-symbol",null);
        pref5_am.putString("med2",null);
        pref5_am.putInt("med2-hour",0);
        pref5_am.putInt("med2-minute",0);
        pref5_am.putBoolean("med2-alarm",false);
        //
        pref5_am.putString("med3-symbol",null);
        pref5_am.putString("med3",null);
        pref5_am.putInt("med3-hour",0);
        pref5_am.putInt("med3-minute",0);
        pref5_am.putBoolean("med3-alarm",false);
        //
        pref5_am.putString("med4-symbol",null);
        pref5_am.putString("med4",null);
        pref5_am.putInt("med4-hour",0);
        pref5_am.putInt("med4-minute",0);
        pref5_am.putBoolean("med4-alarm",false);
        //
        pref5_am.putString("med5-symbol",null);
        pref5_am.putString("med5",null);
        pref5_am.putInt("med5-hour",0);
        pref5_am.putInt("med5-minute",0);
        pref5_am.putBoolean("med5-alarm",false);
        //

        //5_pm
        pref5_pm.putString("med1-symbol",null);
        pref5_pm.putString("med1",null);
        pref5_pm.putInt("med2-hour",0);
        pref5_pm.putInt("med1-minute",0);
        pref5_pm.putBoolean("med1-alarm",false);
        //
        pref5_pm.putString("med2-symbol",null);
        pref5_pm.putString("med2",null);
        pref5_pm.putInt("med2-hour",0);
        pref5_pm.putInt("med2-minute",0);
        pref5_pm.putBoolean("med2-alarm",false);
        //
        pref5_pm.putString("med3-symbol",null);
        pref5_pm.putString("med3",null);
        pref5_pm.putInt("med3-hour",0);
        pref5_pm.putInt("med3-minute",0);
        pref5_pm.putBoolean("med3-alarm",false);
        //
        pref5_pm.putString("med4-symbol",null);
        pref5_pm.putString("med4",null);
        pref5_pm.putInt("med4-hour",0);
        pref5_pm.putInt("med4-minute",0);
        pref5_pm.putBoolean("med4-alarm",false);
        //
        pref5_pm.putString("med5-symbol",null);
        pref5_pm.putString("med5",null);
        pref5_pm.putInt("med5-hour",0);
        pref5_pm.putInt("med5-minute",0);
        pref5_pm.putBoolean("med5-alarm",false);
        //

        //6_am
        pref6_am.putString("med1-symbol",null);
        pref6_am.putString("med1",null);
        pref6_am.putInt("med2-hour",0);
        pref6_am.putInt("med1-minute",0);
        pref6_am.putBoolean("med1-alarm",false);
        //
        pref6_am.putString("med2-symbol",null);
        pref6_am.putString("med2",null);
        pref6_am.putInt("med2-hour",0);
        pref6_am.putInt("med2-minute",0);
        pref6_am.putBoolean("med2-alarm",false);
        //
        pref6_am.putString("med3-symbol",null);
        pref6_am.putString("med3",null);
        pref6_am.putInt("med3-hour",0);
        pref6_am.putInt("med3-minute",0);
        pref6_am.putBoolean("med3-alarm",false);
        //
        pref6_am.putString("med4-symbol",null);
        pref6_am.putString("med4",null);
        pref6_am.putInt("med4-hour",0);
        pref6_am.putInt("med4-minute",0);
        pref6_am.putBoolean("med4-alarm",false);
        //
        pref6_am.putString("med5-symbol",null);
        pref6_am.putString("med5",null);
        pref6_am.putInt("med5-hour",0);
        pref6_am.putInt("med5-minute",0);
        pref6_am.putBoolean("med5-alarm",false);
        //

        //6_pm
        pref6_pm.putString("med1-symbol",null);
        pref6_pm.putString("med1",null);
        pref6_pm.putInt("med2-hour",0);
        pref6_pm.putInt("med1-minute",0);
        pref6_pm.putBoolean("med1-alarm",false);
        //
        pref6_pm.putString("med2-symbol",null);
        pref6_pm.putString("med2",null);
        pref6_pm.putInt("med2-hour",0);
        pref6_pm.putInt("med2-minute",0);
        pref6_pm.putBoolean("med2-alarm",false);
        //
        pref6_pm.putString("med3-symbol",null);
        pref6_pm.putString("med3",null);
        pref6_pm.putInt("med3-hour",0);
        pref6_pm.putInt("med3-minute",0);
        pref6_pm.putBoolean("med3-alarm",false);
        //
        pref6_pm.putString("med4-symbol",null);
        pref6_pm.putString("med4",null);
        pref6_pm.putInt("med4-hour",0);
        pref6_pm.putInt("med4-minute",0);
        pref6_pm.putBoolean("med4-alarm",false);
        //
        pref6_pm.putString("med5-symbol",null);
        pref6_pm.putString("med5",null);
        pref6_pm.putInt("med5-hour",0);
        pref6_pm.putInt("med5-minute",0);
        pref6_pm.putBoolean("med5-alarm",false);
        //

        //7_am
        pref7_am.putString("med1-symbol",null);
        pref7_am.putString("med1",null);
        pref7_am.putInt("med2-hour",0);
        pref7_am.putInt("med1-minute",0);
        pref7_am.putBoolean("med1-alarm",false);
        //
        pref7_am.putString("med2-symbol",null);
        pref7_am.putString("med2",null);
        pref7_am.putInt("med2-hour",0);
        pref7_am.putInt("med2-minute",0);
        pref7_am.putBoolean("med2-alarm",false);
        //
        pref7_am.putString("med3-symbol",null);
        pref7_am.putString("med3",null);
        pref7_am.putInt("med3-hour",0);
        pref7_am.putInt("med3-minute",0);
        pref7_am.putBoolean("med3-alarm",false);
        //
        pref7_am.putString("med4-symbol",null);
        pref7_am.putString("med4",null);
        pref7_am.putInt("med4-hour",0);
        pref7_am.putInt("med4-minute",0);
        pref7_am.putBoolean("med4-alarm",false);
        //
        pref7_am.putString("med5-symbol",null);
        pref7_am.putString("med5",null);
        pref7_am.putInt("med5-hour",0);
        pref7_am.putInt("med5-minute",0);
        pref7_am.putBoolean("med5-alarm",false);
        //

        //7_pm
        pref7_pm.putString("med1-symbol",null);
        pref7_pm.putString("med1",null);
        pref7_pm.putInt("med2-hour",0);
        pref7_pm.putInt("med1-minute",0);
        pref7_pm.putBoolean("med1-alarm",false);
        //
        pref7_pm.putString("med2-symbol",null);
        pref7_pm.putString("med2",null);
        pref7_pm.putInt("med2-hour",0);
        pref7_pm.putInt("med2-minute",0);
        pref7_pm.putBoolean("med2-alarm",false);
        //
        pref7_pm.putString("med3-symbol",null);
        pref7_pm.putString("med3",null);
        pref7_pm.putInt("med3-hour",0);
        pref7_pm.putInt("med3-minute",0);
        pref7_pm.putBoolean("med3-alarm",false);
        //
        pref7_pm.putString("med4-symbol",null);
        pref7_pm.putString("med4",null);
        pref7_pm.putInt("med4-hour",0);
        pref7_pm.putInt("med4-minute",0);
        pref7_pm.putBoolean("med4-alarm",false);
        //
        pref7_pm.putString("med5-symbol",null);
        pref7_pm.putString("med5",null);
        pref7_pm.putInt("med5-hour",0);
        pref7_pm.putInt("med5-minute",0);
        pref7_pm.putBoolean("med5-a larm",false);
        //

        //
        pref6_am.commit();
        pref6_pm.commit();
        pref7_am.commit();
        pref7_pm.commit();
        pref5_am.commit();
        pref5_pm.commit();
        pref4_am.commit();
        pref4_pm.commit();
        pref3_am.commit();
        pref3_pm.commit();
        pref2_am.commit();
        pref2_pm.commit();
        pref1_am.commit();
        pref1_pm.commit();
        //

        //make timezone as NOT changed - since user is restarting the plan
        SharedPreferences.Editor editor = getSharedPreferences("timezonechanged", MODE_PRIVATE).edit();
        editor.putBoolean("timezonechanged", false);
        editor.commit();
        //

        //reset alarm numbers to 0
        SharedPreferences.Editor editor1 = getSharedPreferences("alarmnumber",MODE_PRIVATE).edit();
        editor1.putInt("alarmnumber",0);
        editor1.commit();
        //

        //

        finish();

        //
        Intent i = new Intent(this,SetStartDateActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("donotmoveon","donotmoveon");
        startActivity(i);
        //
    }
    //

    //
    public void load_day1_am(){

        //
        SharedPreferences pref_1 = getApplicationContext().getSharedPreferences("savedPlanExists", Context.MODE_PRIVATE);
        Boolean savedPlanExists = pref_1.getBoolean("savedPlanExists", false );
        //

        if (savedPlanExists)
        {

            // LOAD PLAN!
            //
            DB_day1_am db = new DB_day1_am(getApplicationContext(), null);
            db.loadMeds();
            //
            DB_day1_pm db1 = new DB_day1_pm(getApplicationContext(), null);
            db1.loadMeds();
            //
            DB_day2_am db2 = new DB_day2_am(getApplicationContext(), null);
            db2.loadMeds();
            //
            DB_day2_pm db3 = new DB_day2_pm(getApplicationContext(), null);
            db3.loadMeds();
            //
            DB_day3_am db4 = new DB_day3_am(getApplicationContext(), null);
            db4.loadMeds();
            //
            DB_day3_pm db5 = new DB_day3_pm(getApplicationContext(), null);
            db5.loadMeds();
            //
            DB_day4_am db6 = new DB_day4_am(getApplicationContext(), null);
            db6.loadMeds();
            //
            DB_day4_pm db7 = new DB_day4_pm(getApplicationContext(), null);
            db7.loadMeds();
            //
            DB_day5_am db8 = new DB_day5_am(getApplicationContext(), null);
            db8.loadMeds();
            //
            DB_day5_pm db9 = new DB_day5_pm(getApplicationContext(), null);
            db9.loadMeds();
            //
            DB_day6_am db10 = new DB_day6_am(getApplicationContext(), null);
            db10.loadMeds();
            //
            DB_day6_pm db11 = new DB_day6_pm(getApplicationContext(), null);
            db11.loadMeds();
            //
            DB_day7_am db12 = new DB_day7_am(getApplicationContext(), null);
            db12.loadMeds();
            //
            DB_day7_pm db13 = new DB_day7_pm(getApplicationContext(), null);
            db13.loadMeds();
            //

            //reload MainActivity

            finish();

            Intent intent = new Intent (MainActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //

            //
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No saved plan exists to load", Toast.LENGTH_SHORT).show();
        }
        //
    }
    //
    //

    //
    public Boolean there_is_no_data_to_save()
    {
        //
        Boolean there_is_no_data_to_save = false;
        //

        //
        Boolean nothing_in_day1_am =false;
        Boolean nothing_in_day1_pm =false;
        Boolean nothing_in_day2_am =false;
        Boolean nothing_in_day2_pm =false;
        Boolean nothing_in_day3_am =false;
        Boolean nothing_in_day3_pm =false;
        Boolean nothing_in_day4_am =false;
        Boolean nothing_in_day4_pm =false;
        Boolean nothing_in_day5_am =false;
        Boolean nothing_in_day5_pm =false;
        Boolean nothing_in_day6_am =false;
        Boolean nothing_in_day6_pm =false;
        Boolean nothing_in_day7_am =false;
        Boolean nothing_in_day7_pm =false;
        //

        //
        SharedPreferences pref1_am = getApplicationContext().getSharedPreferences("day1-am",MODE_PRIVATE);
        SharedPreferences pref1_pm = getApplicationContext().getSharedPreferences("day1-pm",MODE_PRIVATE);
        SharedPreferences pref2_am = getApplicationContext().getSharedPreferences("day2-am",MODE_PRIVATE);
        SharedPreferences pref2_pm = getApplicationContext().getSharedPreferences("day2-pm",MODE_PRIVATE);
        SharedPreferences pref3_am = getApplicationContext().getSharedPreferences("day3-am",MODE_PRIVATE);
        SharedPreferences pref3_pm = getApplicationContext().getSharedPreferences("day3-pm",MODE_PRIVATE);
        SharedPreferences pref4_am = getApplicationContext().getSharedPreferences("day4-am",MODE_PRIVATE);
        SharedPreferences pref4_pm = getApplicationContext().getSharedPreferences("day4-pm",MODE_PRIVATE);
        SharedPreferences pref5_am = getApplicationContext().getSharedPreferences("day5-am",MODE_PRIVATE);
        SharedPreferences pref5_pm = getApplicationContext().getSharedPreferences("day5-pm",MODE_PRIVATE);
        SharedPreferences pref6_am = getApplicationContext().getSharedPreferences("day6-am",MODE_PRIVATE);
        SharedPreferences pref6_pm = getApplicationContext().getSharedPreferences("day6-pm",MODE_PRIVATE);
        SharedPreferences pref7_am = getApplicationContext().getSharedPreferences("day7-am",MODE_PRIVATE);
        SharedPreferences pref7_pm = getApplicationContext().getSharedPreferences("day7-pm",MODE_PRIVATE);
        //
        if(pref1_am.getString("med1",null) == null && pref1_am.getString("med2",null) == null
                && pref1_am.getString("med3",null) == null && pref1_am.getString("med4",null) == null
                && pref1_am.getString("med5",null) == null){ nothing_in_day1_am = true;}
        //
        if(pref1_pm.getString("med1",null) == null && pref1_pm.getString("med2",null) == null
                && pref1_pm.getString("med3",null) == null && pref1_pm.getString("med4",null) == null
                && pref1_pm.getString("med5",null) == null){ nothing_in_day1_pm = true;}
        //
        if(pref2_am.getString("med1",null) == null && pref2_am.getString("med2",null) == null
                && pref2_am.getString("med3",null) == null && pref2_am.getString("med4",null) == null
                && pref2_am.getString("med5",null) == null){ nothing_in_day2_am = true;}
        //
        if(pref2_pm.getString("med1",null) == null && pref2_pm.getString("med2",null) == null
                && pref2_pm.getString("med3",null) == null && pref2_pm.getString("med4",null) == null
                && pref2_pm.getString("med5",null) == null){ nothing_in_day2_pm = true;}
        //
        if(pref3_am.getString("med1",null) == null && pref3_am.getString("med2",null) == null
                && pref3_am.getString("med3",null) == null && pref3_am.getString("med4",null) == null
                && pref3_am.getString("med5",null) == null){ nothing_in_day3_am = true;}
        //
        if(pref3_pm.getString("med1",null) == null && pref3_pm.getString("med2",null) == null
                && pref3_pm.getString("med3",null) == null && pref3_pm.getString("med4",null) == null
                && pref3_pm.getString("med5",null) == null){ nothing_in_day3_pm = true;}
        //
        if(pref4_am.getString("med1",null) == null && pref4_am.getString("med2",null) == null
                && pref4_am.getString("med3",null) == null && pref4_am.getString("med4",null) == null
                && pref4_am.getString("med5",null) == null){ nothing_in_day4_am = true;}
        //
        if(pref4_pm.getString("med1",null) == null && pref4_pm.getString("med2",null) == null
                && pref4_pm.getString("med3",null) == null && pref4_pm.getString("med4",null) == null
                && pref4_pm.getString("med5",null) == null){ nothing_in_day4_pm = true;}
        //
        if(pref5_am.getString("med1",null) == null && pref5_am.getString("med2",null) == null
                && pref5_am.getString("med3",null) == null && pref5_am.getString("med4",null) == null
                && pref5_am.getString("med5",null) == null){ nothing_in_day5_am = true;}
        //
        if(pref5_pm.getString("med1",null) == null && pref5_pm.getString("med2",null) == null
                && pref5_pm.getString("med3",null) == null && pref5_pm.getString("med4",null) == null
                && pref5_pm.getString("med5",null) == null){ nothing_in_day5_pm = true;}
        //
        if(pref6_am.getString("med1",null) == null && pref6_am.getString("med2",null) == null
                && pref6_am.getString("med3",null) == null && pref6_am.getString("med4",null) == null
                && pref6_am.getString("med5",null) == null){ nothing_in_day6_am = true;}
        //
        if(pref6_pm.getString("med1",null) == null && pref6_pm.getString("med2",null) == null
                && pref6_pm.getString("med3",null) == null && pref6_pm.getString("med4",null) == null
                && pref6_pm.getString("med5",null) == null){ nothing_in_day6_pm = true;}
        //
        if(pref7_am.getString("med1",null) == null && pref7_am.getString("med2",null) == null
                && pref7_am.getString("med3",null) == null && pref7_am.getString("med4",null) == null
                && pref7_am.getString("med5",null) == null){ nothing_in_day7_am = true;}
        //
        if(pref7_pm.getString("med1",null) == null && pref7_pm.getString("med2",null) == null
                && pref7_pm.getString("med3",null) == null && pref7_pm.getString("med4",null) == null
                && pref7_pm.getString("med5",null) == null){ nothing_in_day7_pm = true;}
        //
        if(nothing_in_day1_am &&
                nothing_in_day1_pm &&
                nothing_in_day2_am &&
                nothing_in_day2_pm &&
                nothing_in_day3_am &&
                nothing_in_day3_pm &&
                nothing_in_day4_am &&
                nothing_in_day4_pm &&
                nothing_in_day5_am &&
                nothing_in_day5_pm &&
                nothing_in_day6_am &&
                nothing_in_day6_pm &&
                nothing_in_day7_am &&
                nothing_in_day7_pm)
        {
            there_is_no_data_to_save = true;
        }
        //

        //
        return there_is_no_data_to_save;
        //

    }

    //
    //
    @Override
    public void onResume() {
        super.onResume();

        //first thing first - exact copy lives in onResume
        //
        SharedPreferences pref_2 = getSharedPreferences("timezonechanged", MODE_PRIVATE);
        timeZoneChanged = pref_2.getBoolean("timezonechanged", false);
        //

        //
        SharedPreferences pref_3 = getSharedPreferences("show_warning", MODE_PRIVATE);
        show_warning = pref_3.getBoolean("show_warning", false );
        //

        if(timeZoneChanged && show_warning)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            //set title
            alertDialogBuilder.setTitle("Time zone change detected");
            //set dialog message
            alertDialogBuilder
                    .setMessage
                            (
                                    "Although your Time Zone has changed, PillME sticks to the old Time Zone which current plan was created in, " +
                                            " your current medicine taking schedule may need revision based on the new Time Zone, you can save your plan, press restart button and then reload the saved plan with a new start date, if you want your medication taking schedule to adapt to the new Time Zone. Manual adding/removal of medications may be necessary."
                            )
                    .setCancelable(false)
                    .setPositiveButton("I Understand", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                            //
                            SharedPreferences.Editor editor = getSharedPreferences("show_warning", MODE_PRIVATE).edit();
                            editor.putBoolean("show_warning",false);
                            editor.commit();
                            //

                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            //
        }
        //

        // SAME CODE APPLIED TO ONRESUME
        Handler mVolHandler = new Handler();
        Runnable mVolRunnable = new Runnable() {
            public void run() {

                //cancel notifications after osme time
                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.cancelAll();
                //

            }
        };

        //mVolHandler.removeCallbacks(mVolRunnable);
        mVolHandler.postDelayed(mVolRunnable, 1 * 60000);
        //
        //

        if(timeZoneChanged)
        {

        //
        final SharedPreferences pref_0 = getSharedPreferences("initialtimezone", MODE_PRIVATE);
        TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone", "notimezone"));
        Calendar calendar = Calendar.getInstance(tz);
        //
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int currentMinute = calendar.get(Calendar.MINUTE);
            //
            if (currentMinute<10)
            {
                middle_textview.setText("Time in original Time Zone:\n" + currentHour + ":" + "0"+currentMinute);

            }else
            {

                middle_textview.setText("Time in original Time Zone:\n" + currentHour + ":" + currentMinute);

            }
        //
        final Handler mVolHandler1 = new Handler();


// OLD WAY - useless! read more books man you are highly misinformed when it comes to threads
//        Runnable mVolRunnable1 = new Runnable() {
//            public void run()
//            {
//                TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));
//                Calendar calendar = Calendar.getInstance(tz);
//
//                int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
//                int currentMinute = calendar.get(Calendar.MINUTE);
//
//                Log.v("MainActivity", "handler ran to set clock in onResume" );
//
//                middle_textview.setText(currentHour+":"+currentMinute);
//
//            }
//        };
//
//        //mVolHandler.removeCallbacks(mVolRunnable);
//        mVolHandler1.postDelayed(mVolRunnable1, 1 * 10000);
//        //


        // repeats time setting every 10 secs
        mVolHandler1.postDelayed(new Runnable() {

            public void run() {

                //
                TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone", "notimezone"));
                Calendar calendar = Calendar.getInstance(tz);

                int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
                int currentMinute = calendar.get(Calendar.MINUTE);

                Log.v("MainActivity", "handler ran to set clock in onResume");

                if (currentMinute<10)
                {
                    middle_textview.setText("Time in original time zone:\n" + currentHour + ":" + "0"+currentMinute);

                }else
                {

                    middle_textview.setText("Time in original time zone:\n" + currentHour + ":" + currentMinute);

                }

                //calling postdelayed again
                mVolHandler1.postDelayed(this, 10000);
                //
            }
        }, 10000);
        //
    }
        //
    }

    //end of MainActivity

}
