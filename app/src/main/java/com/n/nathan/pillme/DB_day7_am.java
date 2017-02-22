package com.n.nathan.pillme;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB_day7_am extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "med_7_amDB.db";
    private static final String TABLE_MED_7_AM = "med_7_am";

    //
    public static final String COLUMN_SYMBOL = "_symbol";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_HOUR = "_hour";
    public static final String COLUMN_MINUTE = "_minute";
    public static final String COLUMN_HASALARMIMAGE = "_hasalarmimage";
    public static final String COLUMN_INSTRUCTIONS = "_instructions";
    //

    Context ctx;

    public DB_day7_am(Context context, SQLiteDatabase.CursorFactory factory)
    {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        ctx = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_MED_7_AM = "CREATE TABLE IF NOT EXISTS " +
                TABLE_MED_7_AM + "("
                + COLUMN_SYMBOL +
                " TEXT , "
                + COLUMN_NAME +
                " TEXT , " +
                COLUMN_HOUR +
                " TEXT , " +
                COLUMN_MINUTE +
                " TEXT , " +
                COLUMN_HASALARMIMAGE +
                " TEXT , " +
                COLUMN_INSTRUCTIONS
                + " TEXT " +
                ")";

        db.execSQL(CREATE_TABLE_MED_7_AM);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MED_7_AM);
        onCreate(db);
    }



    public void addMeds() {

        //
        Log.v("DB_day7_am","numberOfRecords before function: " + number_of_records() );

        deleteAllRec();

        Log.v("DB_day7_am","numberOfRecords after function: " + number_of_records() );
        //

        //
        SharedPreferences pref  = ctx.getSharedPreferences("day7-am",Context.MODE_PRIVATE);
        //

        //adding med1 - med5 INFO
        for( int i=1 ; i<6 ; i++ ) {

            //
            ContentValues values = new ContentValues();
            //

            //adding values
            values.put(COLUMN_SYMBOL, pref.getString("med"+i+"-symbol", null));
            Log.v("DB_day7_am", "saved med" + i + " symbol " + pref.getString("med"+i+"-symbol", null) );

            values.put(COLUMN_NAME, pref.getString("med"+i, null));

            values.put(COLUMN_HOUR, Integer.toString(pref.getInt("med"+i+"-hour", 0) ));
            values.put(COLUMN_MINUTE, Integer.toString(pref.getInt("med"+i+"-minute", 0) ));
            Log.v("DB_day7_am", " minute value for med " + i + " is: " + Integer.toString(pref.getInt("med"+i+"-minute", 0) ));

            values.put(COLUMN_HASALARMIMAGE, Boolean.toString(pref.getBoolean("med"+i+"-alarm",false)) );
            Log.v("DB_day7_am", "saved alarm boolean value:" + Boolean.toString(pref.getBoolean("med"+i+"-alarm",false))  , null );

            values.put(COLUMN_INSTRUCTIONS,"");
            //

            SQLiteDatabase db = this.getWritableDatabase();

            //added later
            //db.beginTransaction();
            //

            db.insert(TABLE_MED_7_AM, null, values);

            //added later
            //db.setTransactionSuccessful();
            //db.endTransaction();
            //

            //
            db.close();
            //

            //
            Log.v("DB_day7_am", number_of_records() + " is number of records in DB_day7_am after save");
            //

        }

        //saved to shared pref that we have a saved plan
        SharedPreferences.Editor pref_1 = ctx.getSharedPreferences("savedPlanExists",Context.MODE_PRIVATE).edit();
        pref_1.putBoolean("savedPlanExists",true);
        pref_1.commit();
        //

    }

    //

    //

    //Load MEDS
    public void loadMeds()
    {

        //
        //String query = "Select * FROM " + TABLE_MED_7_AM + " WHERE " + COLUMN_ID + " =  \"" + id + "\" ORDER BY " + COLUMN_ORDER + " DESC";
        String query = "SELECT * FROM " + TABLE_MED_7_AM;
        //

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //
        int cnt = cursor.getCount();
        Log.v("DB_day7_am", " number of records in day7-am DB: " + cnt );
        //

        SharedPreferences.Editor pref = ctx.getSharedPreferences("day7-am",Context.MODE_PRIVATE).edit();

        for(int i=1 ; i<6; i++)
        {

            pref.putString("med"+i+"-symbol",null);
            pref.putString("med"+i,null);
            pref.putInt("med"+i+"-hour",0);
            pref.putInt("med"+i+"-minute",0);
            pref.putBoolean("med"+i+"-alarm",false);

        }

        //
        pref.commit();
        //

        //
        cursor.moveToFirst();   //med1
        Log.v("DB_day7_am","cursor.getString(0) after cursor.moveToFirst is: " + cursor.getString(0)) ;
        //

        //
        pref.putString("med1-symbol",cursor.getString(0));
        Log.v("DB_day7_am", " loaded med1-symbol " + cursor.getString(0) );
        //
        pref.putString("med1",cursor.getString(1));
        pref.putInt("med1-hour", Integer.parseInt(cursor.getString(2) ));
        pref.putInt("med1-minute", Integer.parseInt(cursor.getString(3) ));
        Log.v("DB_day7_am", "minute value for med1 is: " + Integer.parseInt(cursor.getString(3)));
        //
        pref.putBoolean("med1-alarm",Boolean.parseBoolean(cursor.getString(4) ));
        Log.v("DB_day7_am", " Boolean value for loaded med1-alarm " + cursor.getString(4) );
        //

        for(int i=2; i<6; i++){

            if (cursor.moveToNext()){

                //
                pref.putString("med"+i+"-symbol",cursor.getString(0));
                Log.v("DB_day7_am", " loaded med"+i+"-symbol is: " + cursor.getString(0) );
                //
                pref.putString("med"+i,cursor.getString(1));
                //
                pref.putInt("med"+i+"-hour", Integer.parseInt(cursor.getString(2) ));
                pref.putInt("med"+i+"-minute", Integer.parseInt(cursor.getString(3) ));
                Log.v("DB_day7_am", "minute value for med" + i + " is: " + Integer.parseInt(cursor.getString(3)));
                //
                pref.putBoolean("med"+i+"-alarm",Boolean.parseBoolean(cursor.getString(4) ));
                Log.v("DB_day7_am", " loaded med"+i+"-alarm is: " + cursor.getString(4) );
                //

            }

        }

        //

//        //
//        try {
//
//            while (cursor.moveToNext())
//            {
//
//            }
//        } finally {
//            cursor.close();
//        }

        //
        pref.commit();
        cursor.close();
        db.close();
        //

    }


//

    // delete records by query
    public void deleteAllRec()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " +  TABLE_MED_7_AM); // + " WHERE " + COLUMN_NAME + " IN (SELECT " +  COLUMN_NAME + " FROM " +  TABLE_MED_7_AM + " ORDER BY " +  COLUMN_NAME + " ASC LIMIT 5)");
        db.close();

//     //   db.execSQL("DELETE FROM " +  TABLE_MED_7_AM + " WHERE " + COLUMN_NAME + " IN (SELECT " +  COLUMN_NAME + " FROM " +  TABLE_MED_7_AM + " ORDER BY " +  COLUMN_NAME + " ASC LIMIT 5)");

//        String query =  "DELETE FROM " + TABLE_MED_7_AM +  " WHERE " + COLUMN_SYMBOL + " IN (SELECT " +  COLUMN_SYMBOL + " FROM " +  TABLE_MED_7_AM + " LIMIT 5)";

//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.close();
//        db.close();

    }
    //

    //
    public int number_of_records(){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_MED_7_AM;
        Cursor cursor = db.rawQuery(query, null);

        int cnt = cursor.getCount();

        cursor.close();
        db.close();

        return cnt;
    }
//

//
}