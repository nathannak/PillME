package com.n.nathan.pillme;

import android.util.Log;
import java.util.TimeZone;

/*
Created by Nathan on 12/18/16.
*/

public class WhatsTimeZone {

    public static String getTimezone()
    {

        // get current time zone
        TimeZone tz = TimeZone.getDefault();
        //

        /*
         GMT	Greenwich Mean Time	GMT
         UTC	Universal Coordinated Time	GMT
         ECT	European Central Time	GMT+1:00
         EET	Eastern European Time	GMT+2:00
         ART	(Arabic) Egypt Standard Time	GMT+2:00
         EAT	Eastern African Time	GMT+3:00
         MET	Middle East Time	GMT+3:30
         NET	Near East Time	GMT+4:00
         PLT	Pakistan Lahore Time	GMT+5:00
         IST	India Standard Time	GMT+5:30
         BST	Bangladesh Standard Time	GMT+6:00
         VST	Vietnam Standard Time	GMT+7:00
         CTT	China Taiwan Time	GMT+8:00
         JST	Japan Standard Time	GMT+9:00
         ACT	Australia Central Time	GMT+9:30
         AET	Australia Eastern Time	GMT+10:00
         SST	Solomon Standard Time	GMT+11:00
         NST	New Zealand Standard Time	GMT+12:00
         MIT	Midway Islands Time	GMT-11:00
         HST	Hawaii Standard Time	GMT-10:00
         AST	Alaska Standard Time	GMT-9:00
         PST	Pacific Standard Time	GMT-8:00
         PNT	Phoenix Standard Time	GMT-7:00
         MST	Mountain Standard Time	GMT-7:00
         CST	Central Standard Time	GMT-6:00
         EST	Eastern Standard Time	GMT-5:00
         IET	Indiana Eastern Standard Time	GMT-5:00
         PRT	Puerto Rico and US Virgin Islands Time	GMT-4:00
         CNT	Canada Newfoundland Time	GMT-3:30
         AGT	Argentina Standard Time	GMT-3:00
         BET	Brazil Eastern Time	GMT-3:00
         CAT	Central African Time	GMT-1:00
         */

        String timeZone = "notimezone";

        Log.v("WhatsTimeZone" , "raw offset for current timezone: "+ tz.getRawOffset());

        int rawOffset = tz.getRawOffset();
        final int threeAndHalf = (int) (3.5*60*60*1000);
        final int fiveAndHalf = (int) (5.5*60*60*1000);
        final int nineAndHalf = (int) (9.5*60*60*1000);
        final int tenAndHalf = (int) (10.5*60*60*1000);
        final int minusThreeAndHalf = (int) (-3.5*60*60*1000);

        switch (rawOffset) {

            case 0:

                //
                timeZone = "GMT";
                //
                break;

            case 1*60*60*1000:

                //
                timeZone = "GMT+01:00";
                //
                break;

            case 2*60*60*1000:

                //
                timeZone = "GMT+02:00";
                //
                break;

            case 3*60*60*1000:

                //
                timeZone = "GMT+03:00";
                //
                break;

            case threeAndHalf:

                //
                timeZone = "GMT+03:30";
                //
                break;

            case 4*60*60*1000:

                //
                timeZone = "GMT+04:00";
                //
                break;

            case 5*60*60*1000:

                //
                timeZone = "GMT+05:00";
                //
                break;

            case fiveAndHalf:

                //
                timeZone = "GMT+05:30";
                //
                break;

            case 6*60*60*1000:

                //
                timeZone = "GMT+06:00";
                //
                break;

            case 7*60*60*1000:

                //
                timeZone = "GMT+07:00";
                //
                break;


            case 8*60*60*1000:

                //
                timeZone = "GMT+08:00";
                //

                break;


            case 9*60*60*1000:

                //
                timeZone = "GMT+09:00";
                //
                break;


            case nineAndHalf:

                //
                timeZone = "GMT+09:30";
                //
                break;


            case 10*60*60*1000:

                //
                timeZone = "GMT+10:00";
                //
                break;

            case tenAndHalf:

                //
                timeZone = "GMT+10:30";
                //
                break;

            case 11*60*60*1000:

                //
                timeZone = "GMT+11:00";
                //
                break;


            case 12*60*60*1000:

                //
                timeZone = "GMT+12:00";
                //
                break;

            case 13*60*60*1000:

                //
                timeZone = "GMT+13:00";
                //
                break;

            case -11*60*60*1000:

                //
                timeZone = "GMT-11:00";
                //

                break;


            case -10*60*60*1000:

                //
                timeZone = "GMT-10:00";
                //

                break;


            case -9*60*60*1000:

                //
                timeZone = "GMT-09:00";
                //

                break;

            case -8*60*60*1000:

                //
                timeZone = "GMT-08:00";
                //

                break;


            case -7*60*60*1000:

                //
                timeZone = "GMT-07:00";
                //

                break;

            case -6*60*60*1000:

                //
                timeZone = "GMT-06:00";
                //

                break;


            case -5*60*60*1000:

                //
                timeZone = "GMT-05:00";
                //

                break;

            case -4*60*60*1000:

                //
                timeZone = "GMT-04:00";
                //
                break;

            case minusThreeAndHalf:

                //
                timeZone = "GMT-03:30";
                //
                break;

            case -3*60*60*1000:

                //
                timeZone = "GMT-03:00";
                //
                break;

            case -1*60*60*1000:

                //
                timeZone = "GMT-01:00";
                //
                break;

            default:

                break;
            //
        }

        Log.v("WhatsTimeZone" , "GMT time zone is: " + timeZone );
        return timeZone;

    }

}
