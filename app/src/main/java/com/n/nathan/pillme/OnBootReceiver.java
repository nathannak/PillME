package com.n.nathan.pillme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent_1 = new Intent( context , RescheduleAlarms.class );
        context.startService(intent_1);

        Log.v("myapp","ran service automatically after reboot");
        Toast.makeText(context , "ran service automatically after reboot" , Toast.LENGTH_LONG).show();

    }
}
