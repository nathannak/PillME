package com.n.nathan.pillme;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class RescheduleAlarms extends IntentService {

    public static final String ACTION_FOO = "com.n.nathan.pillme.action.FOO";
    public static final String ACTION_BAZ = "com.n.nathan.pillme.action.BAZ";

    public static final String EXTRA_PARAM1 = "com.n.nathan.pillme.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.n.nathan.pillme.extra.PARAM2";

    //
    String todayIs= "noDay";
    //


    public RescheduleAlarms()
    {
        super("RescheduleAlarms");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
        //

        //
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        //
        Log.v("RescheduleAlarms","CURRENT FREE memory: " + Long.toString(freeMemory));
        //
        if(freeMemory < 2000000)
        {
            runtime.gc();

            freeMemory = runtime.freeMemory();
            Log.v("RescheduleAlarms","NEW FREE memory: " + Long.toString(freeMemory));

        }
        //

        //rescheduleanyways ???
        Boolean rescheduleanyways = false;
        if(intent != null)
        {
            rescheduleanyways = intent.getBooleanExtra("rescheduleanyways",false);
        }
        //

        //DO WE HAVE ACTIVE ALARMS?
        Intent intent1 = new Intent(getApplicationContext(), PollReceiver.class);
        boolean alarmExists = (PendingIntent.getBroadcast(getApplicationContext(), 0 , intent1 , PendingIntent.FLAG_NO_CREATE) != null);
        //
            Log.v("RescheduleAlarms","alarmExists value: "+ alarmExists );
        //

        //ON-HANDLE INTENT STARTS HERE!

        //if alarm does not exists RE-REGISTER alarms
        //** problem with this approach is that it reschedules past alarms so when tha app opens
        //** it makes a noise and sends notiifcation withput any reason
        //

        if(!alarmExists || rescheduleanyways)

        {
            //

            //the case that USER runs app after FORCE-STOP
            //get current time (HOUR and MINUTE)

            // no need for this BS
            //Date date = new Date();

            SharedPreferences pref = getSharedPreferences("initialtimezone", MODE_PRIVATE);
            TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone", "notimezone"));
            Calendar calendar = Calendar.getInstance(tz);

            //no need for this BS
            //calendar.setTime(date);

            //
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
            Log.v("RescheduleAlarms", "current Hour is: " + currentHour);
            //
            int currentMinute = calendar.get(Calendar.MINUTE);
            Log.v("RescheduleAlarms", "current Minute is: " + currentMinute);
            //

            //START OF MOTHER FOR LOOP
            //for ( int h = 1; h < 8; h++) {
            //run for loop from today onwards not from day1

            //neccessary information to know what is today so we dont schedule past alarms
            todayIs_definer();
            //

            if(!todayIs.equals("planfinished") && !todayIs.equals("plannotstarted") ){
            int h = Integer.parseInt(todayIs.substring(3));
            //

            //AFTER FORCE-STOP, Main Activity ran and we have acces to its static variable
            if (MainActivity.todayIs != null && MainActivity.todayIs.equals("day" + h)) {
                //

                //get alarm time for day1 to day7 to get related shared pref

                // if h=2 , then today is day2, right?
                // then there is no need to check for day 1

                //for (int i = 1 ; i < 8; i++)
                for (int i = h; i < 8; i++)

                {

                    Log.v("RescheduleAlarms", "checking alarms for day" + i);

                    //AM FIRST

                    //for med1-med5 AM time
                    for (int j = 1; j < 6; j++) {

                        //day i
                        SharedPreferences pref_1 = getApplicationContext().getSharedPreferences("day" + i + "-am", MODE_PRIVATE);

                        //med j
                        if (pref_1.getBoolean("med" + j + "-alarm", false)) {

                            // alarm time from shared pref
                            int pref_hour = pref_1.getInt("med" + j + "-hour", 0);
                            int pref_minute = pref_1.getInt("med" + j + "-minute", 0);

                            //
                            Log.v("RescheduleAlarms", "pref_hour: " + pref_hour);
                            Log.v("RescheduleAlarms", "pref_minute:  " + pref_minute);
                            //

                            //
                            Log.v("RescheduleAlarms", " got alarm info for med number " + j);
                            //

                            //
                            Log.v("RescheduleAlarms", "current Hour is: " + currentHour);
                            Log.v("RescheduleAlarms", "current Minute is: " + currentMinute);
                            //

                            Log.v("RescheduleAlarms", " checking/setting up alarms for day" + i);

                            //SAME DAY LOGIC IS DIFFERENT!

                            if (i == h) {

                                //
                                Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h);
                                //

                                if (pref_hour < currentHour || (pref_hour == currentHour && (pref_minute < currentMinute))) {

                                    //DO NOTHING
                                    Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h + ", NO ALARM TO SET!");
                                    //

                                } else if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_minutes = pref_minute - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , value of remaining minutes is: " + remaining_minutes);

                                    //
                                    PollReceiver.scheduleAlarms(this, (remaining_minutes) * 60000);
                                    //

                                    //
                                } else {

                                    //
                                    Log.v("RescheduleAlarms", "checking alarms staus for day" + h);
                                    //

                                    //
                                    int remainingMinutes = 60 - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingMinutes" + remainingMinutes);
                                    //

                                    //
                                    int remainingHoursMinutes = ((pref_hour) - (currentHour + 1)) * 60;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingHours" + remainingHoursMinutes);
                                    //

                                    // this lagter to be fed to  repeat_alarm function
                                    int alarm_set_for = remainingHoursMinutes + remainingMinutes + pref_minute;
                                    //
                                    Log.v("RescheduleAlarms", "alarm set for " + alarm_set_for + " minutes from now.");
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, (alarm_set_for) * 60000);
                                    //

                                    //
                                }

                                //
                            } else if (i > h) {

                                //
                                Log.v("RescheduleAlarms", "day is day number: " + h);
                                Log.v("RescheduleAlarms", "checking alarms for day numeber: " + i);

                                //

                                //ALARM SET ON OTHER DAYS THAN THE SAME DAY!
                                if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_hours = ((i - h) * 24);
                                    int remaining_minutes = (pref_minute - currentMinute);
                                    //

                                    // i-h days
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);

                                    //
                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , alarmSetFor value: " + alarm_set_for);
                                    //

                                } else if (pref_hour == currentHour && (pref_minute < currentMinute)) {

                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = (60 - currentMinute) + pref_minute;
                                    //

                                    int alarm_set_for = remaining_minutes * 60000 + remaining_hours * 60 * 60000;

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", "  pref_hour == currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute < currentMinute)) {
                                    //
//
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = 60 - currentMinute + pref_minute;
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;


                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute > currentMinute)) {
                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - currentHour);
                                    int remaining_minutes = pref_minute - currentMinute;

                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute > currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                }

                                //

                            }

                            //

                            //end of checking that alarm exists
                        }


                        // med1-med5 for loop
                    }
                    //END OF AM

                    //DO THE SAME WE DID FOR PM TIME

                    //

                    //for med1-med5 PM time
                    for (int j = 1; j < 6; j++) {

                        //day i
                        SharedPreferences pref_1 = getApplicationContext().getSharedPreferences("day" + i + "-pm", MODE_PRIVATE);

                        //med j
                        if (pref_1.getBoolean("med" + j + "-alarm", false)) {

                            // alarm time from shared pref
                            int pref_hour = pref_1.getInt("med" + j + "-hour", 0);
                            int pref_minute = pref_1.getInt("med" + j + "-minute", 0);

                            //
                            Log.v("RescheduleAlarms", "pref_hour: " + pref_hour);
                            Log.v("RescheduleAlarms", "pref_minute:  " + pref_minute);
                            //

                            //
                            Log.v("RescheduleAlarms", " got alarm info for med number " + j);
                            //

                            //
                            Log.v("RescheduleAlarms", "current Hour is: " + currentHour);
                            Log.v("RescheduleAlarms", "current Minute is: " + currentMinute);
                            //

                            Log.v("RescheduleAlarms", " checking/setting up alarms for day" + i);

                            //SAME DAY LOGIC IS DIFFERENT!

                            if (i == h) {

                                //
                                Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h);
                                //

                                if (pref_hour < currentHour || (pref_hour == currentHour && (pref_minute < currentMinute))) {

                                    //DO NOTHING
                                    Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h + ", NO ALARM TO SET!");
                                    //

                                } else if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_minutes = pref_minute - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , value of remaining minutes is: " + remaining_minutes);

                                    //
                                    PollReceiver.scheduleAlarms(this, (remaining_minutes) * 60000);
                                    //

                                    //
                                } else {

                                    //
                                    Log.v("RescheduleAlarms", "checking alarms staus for day" + h);
                                    //

                                    //
                                    int remainingMinutes = 60 - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingMinutes" + remainingMinutes);
                                    //

                                    //
                                    int remainingHoursMinutes = ((pref_hour) - (currentHour + 1)) * 60;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingHours" + remainingHoursMinutes);
                                    //

                                    // this lagter to be fed to  repeat_alarm function
                                    int alarm_set_for = remainingHoursMinutes + remainingMinutes + pref_minute;
                                    //
                                    Log.v("RescheduleAlarms", "alarm set for " + alarm_set_for + " minutes from now.");
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, (alarm_set_for) * 60000);
                                    //

                                    //
                                }

                                //
                            } else if (i > h) {

                                //
                                Log.v("RescheduleAlarms", "day is day number: " + h);
                                Log.v("RescheduleAlarms", "checking alarms for day numeber: " + i);

                                //

                                //ALARM SET ON OTHER DAYS THAN THE SAME DAY!
                                if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_hours = ((i - h) * 24);
                                    int remaining_minutes = (pref_minute - currentMinute);
                                    //

                                    // i-h days
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);

                                    //
                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , alarmSetFor value: " + alarm_set_for);
                                    //

                                } else if (pref_hour == currentHour && (pref_minute < currentMinute)) {

                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = (60 - currentMinute) + pref_minute;
                                    //

                                    int alarm_set_for = remaining_minutes * 60000 + remaining_hours * 60 * 60000;

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", "  pref_hour == currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute < currentMinute)) {
                                    //
//
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = 60 - currentMinute + pref_minute;
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;


                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute > currentMinute)) {
                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - currentHour);
                                    int remaining_minutes = pref_minute - currentMinute;

                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute > currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                }

                                //

                            }

                            //

                            //end of checking that alarm exists
                        }


                        // med1-med5 for loop
                    }
                    //

                    //
                    //end of PM time programming for FORCE-STOP case
                    //

                    // day1 - day7 for loop
                }

                //end of checking for todayIS exists in Main activity, next is the case fo reboot!
            } else {

                // Ran service after REBOOT!

                //get alarm time for day1 to day7 to get related shared pref

                //run for loop from today onwards not from day1
                //for (int i = 1; i < 8; i++)

                for (int i = h; i < 8; i++) {

                    Log.v("RescheduleAlarms", "checking alarms for day" + i);

                    //AM FIRST

                    //for med1-med5 AM time
                    for (int j = 1; j < 6; j++) {

                        //day i
                        SharedPreferences pref_1 = getApplicationContext().getSharedPreferences("day" + i + "-am", MODE_PRIVATE);

                        //med j
                        if (pref_1.getBoolean("med" + j + "-alarm", false)) {

                            // alarm time from shared pref
                            int pref_hour = pref_1.getInt("med" + j + "-hour", 0);
                            int pref_minute = pref_1.getInt("med" + j + "-minute", 0);

                            //
                            Log.v("RescheduleAlarms", "pref_hour: " + pref_hour);
                            Log.v("RescheduleAlarms", "pref_minute:  " + pref_minute);
                            //

                            //
                            Log.v("RescheduleAlarms", " got alarm info for med number " + j);
                            //

                            //
                            Log.v("RescheduleAlarms", "current Hour is: " + currentHour);
                            Log.v("RescheduleAlarms", "current Minute is: " + currentMinute);
                            //

                            Log.v("RescheduleAlarms", " checking/setting up alarms for day" + i);

                            //SAME DAY LOGIC IS DIFFERENT!
                            if (i == h) {

                                //
                                Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h);
                                //

                                if (pref_hour < currentHour || (pref_hour == currentHour && (pref_minute < currentMinute))) {

                                    //DO NOTHING
                                    Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h + ", NO ALARM TO SET!");
                                    //

                                } else if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_minutes = pref_minute - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , value of remaining minutes is: " + remaining_minutes);

                                    //
                                    PollReceiver.scheduleAlarms(this, (remaining_minutes) * 60000);
                                    //

                                    //
                                } else {

                                    //
                                    Log.v("RescheduleAlarms", "checking alarms staus for day" + h);
                                    //

                                    //
                                    int remainingMinutes = 60 - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingMinutes" + remainingMinutes);
                                    //

                                    //
                                    int remainingHoursMinutes = ((pref_hour) - (currentHour + 1)) * 60;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingHours" + remainingHoursMinutes);
                                    //

                                    // this lagter to be fed to  repeat_alarm function
                                    int alarm_set_for = remainingHoursMinutes + remainingMinutes + pref_minute;
                                    //
                                    Log.v("RescheduleAlarms", "alarm set for " + alarm_set_for + " minutes from now.");
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, (alarm_set_for) * 60000);
                                    //

                                    //
                                }

                                //
                            }

                            //scheduling alarms for other days after reboot
                            else if (i > h) {

                                //
                                Log.v("RescheduleAlarms", "day is day number: " + h);
                                Log.v("RescheduleAlarms", "checking alarms for day numeber: " + i);
                                //

                                //ALARM SET ON OTHER DAYS THAN THE SAME DAY!
                                if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_hours = ((i - h) * 24);
                                    int remaining_minutes = (pref_minute - currentMinute);
                                    //

                                    // i-h days
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);

                                    //
                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , alarmSetFor value: " + alarm_set_for);
                                    //

                                } else if (pref_hour == currentHour && (pref_minute < currentMinute)) {

                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = (60 - currentMinute) + pref_minute;
                                    //

                                    int alarm_set_for = remaining_minutes * 60000 + remaining_hours * 60 * 60000;

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", "  pref_hour == currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute < currentMinute)) {
                                    //
//
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = 60 - currentMinute + pref_minute;
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;


                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute > currentMinute)) {
                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - currentHour);
                                    int remaining_minutes = pref_minute - currentMinute;

                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute > currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                }

                                //

                            }

                            //

                            //end of checking that alarm exists
                        }

                        // med1-med5 for loop
                    }

                }
                //END OF AM

                //

                //
                //DO THE SAME WE DID FOR PM TIME
                //

                //
                for (int i = h; i < 8; i++) {
                    //

                    Log.v("RescheduleAlarms", "checking alarms for day" + i);

                    //AM FIRST

                    //for med1-med5 AM time
                    for (int j = 1; j < 6; j++) {

                        //day i
                        SharedPreferences pref_1 = getApplicationContext().getSharedPreferences("day" + i + "-pm", MODE_PRIVATE);

                        //med j
                        if (pref_1.getBoolean("med" + j + "-alarm", false)) {

                            // alarm time from shared pref
                            int pref_hour = pref_1.getInt("med" + j + "-hour", 0);
                            int pref_minute = pref_1.getInt("med" + j + "-minute", 0);

                            //
                            Log.v("RescheduleAlarms", "pref_hour: " + pref_hour);
                            Log.v("RescheduleAlarms", "pref_minute:  " + pref_minute);
                            //

                            //
                            Log.v("RescheduleAlarms", " got alarm info for med number " + j);
                            //

                            //
                            Log.v("RescheduleAlarms", "current Hour is: " + currentHour);
                            Log.v("RescheduleAlarms", "current Minute is: " + currentMinute);
                            //

                            Log.v("RescheduleAlarms", " checking/setting up alarms for day" + i);

                            //SAME DAY LOGIC IS DIFFERENT!
                            if (i == h) {

                                //
                                Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h);
                                //

                                if (pref_hour < currentHour || (pref_hour == currentHour && (pref_minute < currentMinute))) {

                                    //DO NOTHING
                                    Log.v("RescheduleAlarms", " checking/setting up alarms for day" + h + ", NO ALARM TO SET!");
                                    //

                                } else if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_minutes = pref_minute - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , value of remaining minutes is: " + remaining_minutes);

                                    //
                                    PollReceiver.scheduleAlarms(this, (remaining_minutes) * 60000);
                                    //

                                    //
                                } else {

                                    //
                                    Log.v("RescheduleAlarms", "checking alarms staus for day" + h);
                                    //

                                    //
                                    int remainingMinutes = 60 - currentMinute;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingMinutes" + remainingMinutes);
                                    //

                                    //
                                    int remainingHoursMinutes = ((pref_hour) - (currentHour + 1)) * 60;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", "remainingHours" + remainingHoursMinutes);
                                    //

                                    // this lagter to be fed to  repeat_alarm function
                                    int alarm_set_for = remainingHoursMinutes + remainingMinutes + pref_minute;
                                    //
                                    Log.v("RescheduleAlarms", "alarm set for " + alarm_set_for + " minutes from now.");
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, (alarm_set_for) * 60000);
                                    //

                                    //
                                }

                                //
                            }

                            //scheduling alarms for other days after reboot
                            else if (i > h) {

                                //
                                Log.v("RescheduleAlarms", "day is day number: " + h);
                                Log.v("RescheduleAlarms", "checking alarms for day numeber: " + i);
                                //

                                //ALARM SET ON OTHER DAYS THAN THE SAME DAY!
                                if (pref_hour == currentHour && (pref_minute > currentMinute)) {

                                    //
                                    int remaining_hours = ((i - h) * 24);
                                    int remaining_minutes = (pref_minute - currentMinute);
                                    //

                                    // i-h days
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);

                                    //
                                    Log.v("RescheduleAlarms", " pref_hour == currentHour && (pref_minute > currentMinute) , alarmSetFor value: " + alarm_set_for);
                                    //

                                } else if (pref_hour == currentHour && (pref_minute < currentMinute)) {

                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = (60 - currentMinute) + pref_minute;
                                    //

                                    int alarm_set_for = remaining_minutes * 60000 + remaining_hours * 60 * 60000;

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", "  pref_hour == currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute < currentMinute)) {
                                    //
//
                                    int remaining_hours = (i - h) * 24 + (pref_hour - (currentHour + 1));
                                    int remaining_minutes = 60 - currentMinute + pref_minute;
                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;


                                    //
                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //

                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute < currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                } else if (pref_hour > currentHour && (pref_minute > currentMinute)) {
                                    //
                                    int remaining_hours = (i - h) * 24 + (pref_hour - currentHour);
                                    int remaining_minutes = pref_minute - currentMinute;

                                    int alarm_set_for = remaining_hours * 60 * 60000 + remaining_minutes * 60000;

                                    PollReceiver.scheduleAlarms(this, alarm_set_for);
                                    //
                                    Log.v("RescheduleAlarms", " remaining_hours " + remaining_hours);
                                    Log.v("RescheduleAlarms", " remaining_minutes " + remaining_minutes);
                                    //
                                    Log.v("RescheduleAlarms", " pref_hour > currentHour && ( pref_minute > currentMinute ) , alarm_set_for: " + alarm_set_for);
                                    //

                                }

                                //

                            }

                            //

                            //end of checking that alarm exists
                        }

                        // med1-med5 for loop
                    }

                    //
                    //end of PM time
                    //

                    // day1 - day7 for loop
                }

                //

                // end of else- the case that service ran after Reboot
            }


            //end of mother for loop
            //}

        //end of checking for h to be legit value
        }

            //end of checking that there is no registered pending intent
        }


        //end of onHandle Intent
    }

    //

    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public void todayIs_definer ()
    {
        //

        //Get CURRENT day, month, and year - curren time


        SharedPreferences pref_0 = getSharedPreferences("initialtimezone", MODE_PRIVATE);
        TimeZone tz = TimeZone.getTimeZone(pref_0.getString("initialtimezone","notimezone"));
        Calendar calendar_1 = Calendar.getInstance(tz);
        Date date_1 = calendar_1.getTime();


        //get first day of our plan from shared pref
        SharedPreferences pref = getSharedPreferences("startday", MODE_PRIVATE);
        int sharedpref_year = pref.getInt("year", 0);
        int sharedpref_month = pref.getInt("month", 0);
        int sharedpref_day = pref.getInt("day", 0);
        //
        Log.v("Mainactivity", "in MainActivity, values from shared pref: day,month,year is: " + sharedpref_day + " " + sharedpref_month + " " + sharedpref_year);
        //

        // do NOT egt the sticky timezone here, we are trying to create a new object to create adate
        Calendar cal = Calendar.getInstance();
        //

        //months is ZERO based in JAVA
        //create date object
        cal.set(sharedpref_year, sharedpref_month - 1, sharedpref_day);

        //new line added
        cal.setTimeZone(tz);

        //
        Date date_2 = cal.getTime();
        //

        //retired due to inaccuracy concerns
        //long hoursPassed = (calendar_1.getTimeInMillis() - date_2.getTime()) / (60000 * 60);
        long hoursPassed = (date_1.getTime() - date_2.getTime()) / (60000 * 60);

        //
        Log.v("RescheduleAlarms", "hours passed since start day: " + ((calendar_1.getTimeInMillis() - date_2.getTime()) / (60000 * 60)));
        //
        if (hoursPassed < 0) {

            todayIs = "plannotstarted";

        } else if (hoursPassed == 0)
        {

            //Day1
            todayIs = "day1";
            //

        } else if ((hoursPassed > 0) && (hoursPassed <= 25))
        {

            //Day 2
            todayIs = "day2";
            //

        } else if (hoursPassed > 25 && hoursPassed <= 49)
        {

            //Day3
            todayIs = "day3";
            //

        } else if (hoursPassed > 49 && hoursPassed <= 73) {

            //
            todayIs = "day4";
            //

        } else if (hoursPassed > 73 && hoursPassed <= 97) {

            //
            todayIs = "day5";
            //

        } else if (hoursPassed > 97 && hoursPassed <= 121) {

            //
            todayIs = "day6";
            //


        } else if (hoursPassed > 121 && hoursPassed <= 145) {

            //
            todayIs = "day7";
            //

        }
        else
        {

            todayIs = "planfinished";

        }
        //
    }

    //end of Intent class
}
