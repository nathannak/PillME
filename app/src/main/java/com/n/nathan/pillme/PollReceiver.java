package com.n.nathan.pillme;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class PollReceiver extends WakefulBroadcastReceiver {

    private static final int PERIOD = 60000;

    @Override
    public void onReceive(Context ctx, Intent i) {

        //if (i.getAction() == null) {
        //  WakefulIntentService.sendWakefulWork(ctx, LocationChangeService_Location.class);
        // }
        //else {

        //
        Log.v("myapp","in onReceive");
        Toast.makeText(ctx,"in onReceive - executing alarm -",Toast.LENGTH_LONG).show();
        //

        //
        Intent notificationIntent = new Intent(ctx, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(ctx,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager nm = (NotificationManager) ctx
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = ctx.getResources();
        Notification.Builder builder = new Notification.Builder(ctx);

//        //
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        //

        //Uri uri = Uri.parse("R.raw.alarm1");
        Uri uri = Uri.parse("android.resource://com.n.nathan.pillme/" + R.raw.alarm1);

        builder.setContentIntent(contentIntent)

                .setSmallIcon(R.mipmap.bellonly)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setVisibility(VISIBILITY_PUBLIC)
                .setOnlyAlertOnce(true)
                .setLights( Color.RED,1,1 )
                //.setLights( ctx.getResources().getColor(R.color.gray) ,1,1)

                //Vibration
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })

                //Tone
                //.setSound(Uri.parse("uri://sadfasdfasdf.mp3"))
                .setSound(uri)

                //.setContentTitle(res.getString(R.string.your_notif_title))
                .setContentTitle("PillMe-check your medication taking schedule");

        Notification n = builder.build();

        nm.notify(0, n);
        //

        //
        Intent intent = new Intent (ctx,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
        //

        //
        //

        //add runtime permission for Android MArshmallow
//        String phoneNumber = "6504552047";
//        String smsBody = "Message from the API";
//
//        // Get the default instance of SmsManager
//        SmsManager smsManager = SmsManager.getDefault();
//
//        // Send a text based SMS
//        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);

        //
        //

//        //
//        String phoneNumber = "6504552047";
//        String smsBody = "This is an SMS!";
//
//        // Add the phone number in the data
//        Uri uri = Uri.parse("smsto:" + phoneNumber);
//
//        // Create intent with the action and data
//        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
//        smsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        // smsIntent.setData(uri); // We just set the data in the constructor above
//
//        // Set the message
//        smsIntent.putExtra("sms_body", smsBody);
//
//        ctx.startActivity(smsIntent);
//        //

        //

        //if you comment this out alarm happens once, if you un-comment alarm happens periodically
        //scheduleAlarms(ctx,0);

    }

//
//    //for yelp and foursquare
//    static void scheduleAlarms(Context ctx) {
//        AlarmManager mgr =
//                (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
//        Intent i = new Intent(ctx, LocationChangeService_4s.class);
//        PendingIntent pi = PendingIntent.getService(ctx, 0, i, 0);
//        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime() + PERIOD, 1*PERIOD, pi);
//    }
//
//
//    //gor GP
//    static void scheduleAlarmsGP(Context ctx) {
//        AlarmManager mgr =
//                (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
//        Intent i = new Intent(ctx, LocationChangeService_gp.class);
//        PendingIntent pi = PendingIntent.getService(ctx, 0, i, 0);
//        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime() + PERIOD, 2*PERIOD, pi);
//    }


    //location change listener - for automatic search
    static void scheduleAlarms(Context ctx,int timePeriod) {

        // to be used as alarm id -  each alarm needs a seperate id or previous alarms will be overridden.
        SharedPreferences pref = ctx.getSharedPreferences("alarmnumber",MODE_PRIVATE);
        int alarmnumber = pref.getInt("alarmnumber",0);
        //

        Log.v("PollReceiver","alarmnumber before scheduling alarm: " + alarmnumber);

        //
        AlarmManager mgr =
                (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        //

//
//        Intent i = new Intent(ctx, NotificationIntentService.class);
//        PendingIntent pi = PendingIntent.getService(ctx, 0, i, 0);
//

        //
//        SharedPreferences pref = ctx.getSharedPreferences("initialtimezone", MODE_PRIVATE);
//        TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));
//        Calendar c = Calendar.getInstance(tz);
        //

        //mgr.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 60000,pi);

        //mgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,cal.getTimeInMillis() + 60000,pi);

        //mgr.setRepeating(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime()+PERIOD,PERIOD,pi);



        //
        Intent i1 = new Intent(ctx, PollReceiver.class);
        //PendingIntent pi1 = PendingIntent.getBroadcast(ctx, 0, i1, 0);
        PendingIntent pi1 = PendingIntent.getBroadcast(ctx, alarmnumber , i1, 0);
        //

        //THIS IS NOT THAT IMPORTANT - FOR CLICKING ON NOTIF AND SHIT ... !
        Intent i2 = new Intent(ctx, MainActivity.class);
        //PendingIntent pi2 = PendingIntent.getActivity(ctx, 0, i2, 0);
        PendingIntent pi2 = PendingIntent.getActivity(ctx, alarmnumber , i2, 0);
        //

        //
        AlarmManager.AlarmClockInfo ac=
                new AlarmManager.AlarmClockInfo(System.currentTimeMillis()+ timePeriod,
                        pi2);
        //

        //
        mgr.setAlarmClock(ac,pi1);
        //

        //
        alarmnumber = alarmnumber + 1;
        SharedPreferences.Editor editor = ctx.getSharedPreferences("alarmnumber",MODE_PRIVATE).edit();
        editor.putInt("alarmnumber",alarmnumber);
        editor.commit();
        Log.v("PollReceiver","alarmnumber after scheduling alarm: " + alarmnumber);
        //

        //
        Toast.makeText(ctx,"Alarm scheduled" ,Toast.LENGTH_LONG).show();
        Log.v("myapp","(scheduleAlarms) Alarm scheduled for " + (timePeriod/60000) + " minutes form now" );
        //

    }

}