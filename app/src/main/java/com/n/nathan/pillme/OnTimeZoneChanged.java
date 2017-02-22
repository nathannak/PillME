package com.n.nathan.pillme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;

public class OnTimeZoneChanged extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //get new time zone
        TimeZone tz0 = TimeZone.getDefault();

        //compare with shared pref
        SharedPreferences pref = context.getSharedPreferences("initialtimezone", MODE_PRIVATE);
        String timeZone = pref.getString("initialtimezone", "notimezone");

        if (!timeZone.equals("notimezone")) {

            TimeZone tz = TimeZone.getTimeZone(timeZone);

            if (tz.getRawOffset() != tz0.getRawOffset())
            {
                //
                SharedPreferences.Editor editor = context.getSharedPreferences("timezonechanged", MODE_PRIVATE).edit();
                editor.putBoolean("timezonechanged", true);
                editor.commit();
                //

                //
                //
                SharedPreferences.Editor editor1 = context.getSharedPreferences("show_warning", MODE_PRIVATE).edit();
                editor1.putBoolean("show_warning",true);
                editor1.commit();
                //
                //

                Log.v("onTimeZoneChanged", "PillMe - Time Zone changed");
                Toast.makeText(context, "PillMe - Time Zone changed", Toast.LENGTH_LONG).show();

                //
            }
        }
    }
}
