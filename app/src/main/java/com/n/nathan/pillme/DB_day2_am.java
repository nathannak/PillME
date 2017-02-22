package com.n.nathan.pillme;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB_day2_am extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "med_2_amDB.db";
    private static final String TABLE_MED_2_AM = "med_2_am";

    //
    public static final String COLUMN_SYMBOL = "_symbol";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_HOUR = "_hour";
    public static final String COLUMN_MINUTE = "_minute";
    public static final String COLUMN_HASALARMIMAGE = "_hasalarmimage";
    public static final String COLUMN_INSTRUCTIONS = "_instructions";
    //

    Context ctx;

    public DB_day2_am(Context context, SQLiteDatabase.CursorFactory factory)
    {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        ctx = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_MED_2_AM = "CREATE TABLE IF NOT EXISTS " +
                TABLE_MED_2_AM + "("
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

        db.execSQL(CREATE_TABLE_MED_2_AM);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MED_2_AM);
        onCreate(db);
    }



    public void addMeds() {

        //
        Log.v("DB_day2_am","numberOfRecords before function: " + number_of_records() );

        deleteAllRec();

        Log.v("DB_day2_am","numberOfRecords after function: " + number_of_records() );
        //

        //
        SharedPreferences pref  = ctx.getSharedPreferences("day2-am",Context.MODE_PRIVATE);
        //

        //adding med1 - med5 INFO
        for( int i=1 ; i<6 ; i++ ) {

            //
            ContentValues values = new ContentValues();
            //

            //adding values
            values.put(COLUMN_SYMBOL, pref.getString("med"+i+"-symbol", null));
            Log.v("DB_day2_am", "saved med" + i + " symbol " + pref.getString("med"+i+"-symbol", null) );

            values.put(COLUMN_NAME, pref.getString("med"+i, null));

            values.put(COLUMN_HOUR, Integer.toString(pref.getInt("med"+i+"-hour", 0) ));
            values.put(COLUMN_MINUTE, Integer.toString(pref.getInt("med"+i+"-minute", 0) ));
            Log.v("DB_day2_am", " minute value for med " + i + " is: " + Integer.toString(pref.getInt("med"+i+"-minute", 0) ));

            values.put(COLUMN_HASALARMIMAGE, Boolean.toString(pref.getBoolean("med"+i+"-alarm",false)) );
            Log.v("DB_day2_am", "saved alarm boolean value:" + Boolean.toString(pref.getBoolean("med"+i+"-alarm",false))  , null );

            values.put(COLUMN_INSTRUCTIONS,"");
            //

            SQLiteDatabase db = this.getWritableDatabase();

            //added later
            //db.beginTransaction();
            //

            db.insert(TABLE_MED_2_AM, null, values);

            //added later
            //db.setTransactionSuccessful();
            //db.endTransaction();
            //

            //
            db.close();
            //

            //
            Log.v("DB_day2_am", number_of_records() + " is number of records in DB_day2_am after save");
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
        //String query = "Select * FROM " + TABLE_MED_2_AM + " WHERE " + COLUMN_ID + " =  \"" + id + "\" ORDER BY " + COLUMN_ORDER + " DESC";
        String query = "SELECT * FROM " + TABLE_MED_2_AM;
        //

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //
        int cnt = cursor.getCount();
        Log.v("DB_day2_am", " number of records in day2-am DB: " + cnt );
        //

        SharedPreferences.Editor pref = ctx.getSharedPreferences("day2-am",Context.MODE_PRIVATE).edit();

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
        Log.v("DB_day2_am","cursor.getString(0) after cursor.moveToFirst is: " + cursor.getString(0)) ;
        //

        //
        pref.putString("med1-symbol",cursor.getString(0));
        Log.v("DB_day2_am", " loaded med1-symbol " + cursor.getString(0) );
        //
        pref.putString("med1",cursor.getString(1));
        pref.putInt("med1-hour", Integer.parseInt(cursor.getString(2) ));
        pref.putInt("med1-minute", Integer.parseInt(cursor.getString(3) ));
        Log.v("DB_day2_am", "minute value for med1 is: " + Integer.parseInt(cursor.getString(3)));
        //
        pref.putBoolean("med1-alarm",Boolean.parseBoolean(cursor.getString(4) ));
        Log.v("DB_day2_am", " Boolean value for loaded med1-alarm " + cursor.getString(4) );
        //

        for(int i=2; i<6; i++){

            if (cursor.moveToNext()){

                //
                pref.putString("med"+i+"-symbol",cursor.getString(0));
                Log.v("DB_day2_am", " loaded med"+i+"-symbol is: " + cursor.getString(0) );
                //
                pref.putString("med"+i,cursor.getString(1));
                //
                pref.putInt("med"+i+"-hour", Integer.parseInt(cursor.getString(2) ));
                pref.putInt("med"+i+"-minute", Integer.parseInt(cursor.getString(3) ));
                Log.v("DB_day2_am", "minute value for med" + i + " is: " + Integer.parseInt(cursor.getString(3)));
                //
                pref.putBoolean("med"+i+"-alarm",Boolean.parseBoolean(cursor.getString(4) ));
                Log.v("DB_day2_am", " loaded med"+i+"-alarm is: " + cursor.getString(4) );
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
        db.execSQL("DELETE FROM " +  TABLE_MED_2_AM); // + " WHERE " + COLUMN_NAME + " IN (SELECT " +  COLUMN_NAME + " FROM " +  TABLE_MED_2_AM + " ORDER BY " +  COLUMN_NAME + " ASC LIMIT 5)");
        db.close();

//     //   db.execSQL("DELETE FROM " +  TABLE_MED_2_AM + " WHERE " + COLUMN_NAME + " IN (SELECT " +  COLUMN_NAME + " FROM " +  TABLE_MED_2_AM + " ORDER BY " +  COLUMN_NAME + " ASC LIMIT 5)");

//        String query =  "DELETE FROM " + TABLE_MED_2_AM +  " WHERE " + COLUMN_SYMBOL + " IN (SELECT " +  COLUMN_SYMBOL + " FROM " +  TABLE_MED_2_AM + " LIMIT 5)";

//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.close();
//        db.close();

    }
    //

    //
    public int number_of_records(){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_MED_2_AM;
        Cursor cursor = db.rawQuery(query, null);

        int cnt = cursor.getCount();

        cursor.close();
        db.close();

        return cnt;
    }
//






//    //find business by id
//
//    public Business_4s findBusiness(String id) {
//        String query = "Select * FROM " + TABLE_BUSINESS + " WHERE " + COLUMN_ID + " =  \"" + id + "\" ORDER BY " + COLUMN_ORDER + " DESC";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        int cnt = cursor.getCount();
//        Log.v("myapp", Integer.toString(cnt) + " inside find business with id function, " + Integer.toString(cnt) + " business/businesses found.");
//
//        Business_4s business = new Business_4s();
//
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//
//            business.set_id(cursor.getString(0));
//            business.set_likes(cursor.getString(1));
//            business.set_lat(Double.parseDouble(cursor.getString(2)));
//            business.set_lng(Double.parseDouble(cursor.getString(3)));
//            business.set_name(cursor.getString(4));
//            business.set_formattedPhone(cursor.getString(5));
//            business.set_facebook(cursor.getString(6));
//            business.set_formattedAddress(cursor.getString(7));
//            business.set_mobileUrl(cursor.getString(8));
//
//            //want to know how many business are there
//
//
//
//
//            cursor.close();
//
//        }
//
//        if (cnt == 0) {
//            business = null;
//        }
//
//        cursor.close();
//        db.close();
//
//        return business;
//    }
//
//
//
//
//
//    //new function to check if business is already in the database
//    public boolean doesBusinessExist(Business_4s business)
//    {
//        //
//        boolean businessExists = false;
//        //
//
//        //i get a creepy eror for some reason
//        if(business != null) {
//
//            //
//            String query = "Select " + COLUMN_ID + " FROM " + TABLE_BUSINESS + " WHERE " + COLUMN_ID + " =  \"" + business.get_id() + "\"";
//
//            SQLiteDatabase db = this.getWritableDatabase();
//
//            Cursor cursor = db.rawQuery(query, null);
//            //
//
//            try {
//                //
//                if (cursor != null && cursor.moveToFirst()) {
//                    cursor.moveToFirst();
//
//                    //want to know how many business are there
//                    int cnt = cursor.getCount();
//                    Log.v("myapp", Integer.toString(cnt) + " (inside doesBusinessExist function) business/businesses found");
//
//                    //return true
//                    if (cnt > 0) {
//                        businessExists = true;
//                    }
//
//                } else {
//                    businessExists = false;
//                }
//                //
//            } finally {
//                cursor.close();
//                db.close();
//            }
//        }
//
//        //
//        //final return
//        return businessExists;
//        //
//
//    }
//
//
//
///* experimental function retired now
//    public Boolean findYelpEquivalent_0 ( String phone )
//    {
//        Boolean YelpBusinessExists = false;
//
//
//        // i recieved pure phone from yelp, i have to make it formatted
//
//        phone = phone.trim();
//
//        String rawphone = "(" + phone;
//
//        String rawphone0 = rawphone.substring(0,4);
//        rawphone0 = rawphone0 + ") ";
//
//        String rawphone1 = rawphone.substring(4,7);
//        rawphone1 = rawphone1 + "-";
//
//        String rawphone2 = rawphone.substring(7);
//
//        String processedphone = rawphone0 + rawphone1 + rawphone2;
//
//        Log.v("myapp","processed phone is " + processedphone);
//
//        //
//        String query = "Select * FROM " + TABLE_BUSINESS + " WHERE " + COLUMN_FORMATTEDPHONE + " =  \"" + processedphone + "\"";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//
//
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//
//            //want to know how many business are there
//            int cnt = cursor.getCount();
//            Log.v("myapp", Integer.toString(cnt) + " similar 4s business found by yelp phone number OLD function");
//
//            //return true if you find records
//            if(cnt>0){YelpBusinessExists = true;}
//            cursor.close();
//
//        } else {
//            YelpBusinessExists = false;
//        }
//
//        cursor.close();
//        db.close();
//        //final return
//        return YelpBusinessExists;
//        //
//
//    }
//*/ // end of experimental function
//
//
//    // returns true if it fonds similar YELP business based on given yelp business phone numeber
//    /*
//    public Boolean findYelpEquivalent_usa_boolean ( String displayPhone )
//    {
//        Boolean YelpBusinessExists = false;
//
//
//        // i recieved pure phone from yelp, i have to make it formatted
//        if(displayPhone != null){
//
//            //
//            String displayPhone_trimmed = displayPhone.trim();
//            Log.v("myapp", "display phone trimmed is: " + displayPhone_trimmed);
//
//            displayPhone_trimmed = displayPhone_trimmed.substring(3);
//            String displayPhone_trimmed1 = displayPhone_trimmed.substring(0,3);
//            displayPhone_trimmed1 = "(" + displayPhone_trimmed1 + ") ";
//            String displayPhone_trimmed2 = displayPhone_trimmed.substring(4);
//            displayPhone_trimmed = displayPhone_trimmed1 + displayPhone_trimmed2;
//
//
//            //search foursquare
//            String query = "Select * FROM " + TABLE_BUSINESS + " WHERE " + COLUMN_FORMATTEDPHONE + " =  \"" + displayPhone_trimmed + "\"";
//
//            SQLiteDatabase db = this.getWritableDatabase();
//            Cursor cursor = db.rawQuery(query, null);
//
//
//
//            if (cursor.moveToFirst()) {
//                cursor.moveToFirst();
//
//                //want to know how many business are there
//                int cnt = cursor.getCount();
//                Log.v("myapp", Integer.toString(cnt) + " similar 4s business found by yelp phone number NEW function");
//
//                //return true if you find records
//                if(cnt>0){YelpBusinessExists = true;}
//                cursor.close();
//
//            } else {
//                YelpBusinessExists = false;
//            }
//
//            cursor.close();
//            db.close();
//
//            //
//        }
//
//        //return boolean
//        return YelpBusinessExists;
//        //
//    }
//    //
//    */
//
//
//    //
//    // returns true if it fonds similar YELP business base don gice yelp business phone numeber
//    public String findYelpEquivalent_id ( String displayPhone )
//
//    {
//
//        //VOROODI HAMISHE YELP E!
//        String id = "notfound";
//        String displayPhone_trimmed ="";
//        //
//        //
//        // Denmark
//        if(displayPhone != null && displayPhone.substring(0,3).equals("+45")){
//
//            //
//            //weiredly for Denmark, phone numebrs are the sam efor yelp and 4s
//
//            displayPhone_trimmed = displayPhone.trim();
//
//            Log.v("myapp","danish phone number from yelp, processed to be like 4s: " + displayPhone_trimmed);
//
//        }
//
//        //Singapore
//        else if(displayPhone != null && displayPhone.substring(0,3).equals("+65")){
//
//            //
//            //weiredly for singapure, phone numebrs are the sam efor yelp and 4s
//
//
//            String displayPhone_trimmed1 = displayPhone.substring(0,8);
//            displayPhone_trimmed1 = displayPhone_trimmed1 + " ";
//
//            String displayPhone_trimmed2 =  displayPhone.substring(9);
//
//            displayPhone_trimmed = displayPhone_trimmed1 + displayPhone_trimmed2;
//            Log.v("myapp","singaporean phone number from yelp, processed to be like 4s: " + displayPhone_trimmed);
//        }
//
//
//        //Japan
//        else if(displayPhone != null && displayPhone.substring(0,3).equals("+81")){
//
//            //
//            //there are two more style of phonenumber which i did not cover here
//            //
//
//            String displayPhone_trimmed1 = displayPhone.substring(0,5);
//            displayPhone_trimmed1 = displayPhone_trimmed1 + "-";
//
//            String displayPhone_trimmed2 = displayPhone.substring(6,10);
//            displayPhone_trimmed2 = displayPhone_trimmed2 + "-";
//
//            String displayPhone_trimmed3 =  displayPhone.substring(11);
//
//            displayPhone_trimmed = displayPhone_trimmed1 + displayPhone_trimmed2 + displayPhone_trimmed3;
//            Log.v("myapp","japanese phone number from yelp , processed to be like 4s: " + displayPhone_trimmed);
//        }
//
//
//
//        //Sweden
//        else if(displayPhone != null && displayPhone.substring(0,3).equals("+46")){
//
//            String displayPhone_trimmed1 = displayPhone.substring(0,5);
//            displayPhone_trimmed1 = displayPhone_trimmed1 + " ";
//            String displayPhone_trimmed2 =  displayPhone.substring(6);
//            displayPhone_trimmed = displayPhone_trimmed1 + displayPhone_trimmed2;
//            Log.v("myapp","swedish phone number from yelp , processed to be like 4s: " + displayPhone_trimmed);
//        }
//
//        //HongKong
//        else if(displayPhone != null && displayPhone.substring(0,4).equals("+852")){
//
//            displayPhone_trimmed = displayPhone.trim();
//
//            Log.v("myapp","hongkongese phone number from yelp , processed to be like 4s: " + displayPhone_trimmed);
//
//        }
//
//        //USA
//        else if( displayPhone != null && displayPhone.substring(0,2).equals("+1")) {
//
//            // USA function
//            // I recieved pure phone from yelp, i have to make it formatted
//
//            //
//            displayPhone_trimmed = displayPhone.trim();
//
//            displayPhone_trimmed = displayPhone_trimmed.substring(3);
//            String displayPhone_trimmed1 = displayPhone_trimmed.substring(0, 3);
//            displayPhone_trimmed1 = "(" + displayPhone_trimmed1 + ") ";
//            String displayPhone_trimmed2 = displayPhone_trimmed.substring(4);
//            displayPhone_trimmed = displayPhone_trimmed1 + displayPhone_trimmed2;
//
//            Log.v("myapp","american phone number from yelp , processed to be like 4s: " + displayPhone_trimmed);
//
//            //
//        }
//
//
//
//        if(displayPhone_trimmed != null){
//            //search foursquare - it is ordered by likes so i see the business with highest common likes on the top
//            String query = "Select * FROM " + TABLE_BUSINESS + " WHERE " + COLUMN_FORMATTEDPHONE + " =  \"" + displayPhone_trimmed + "\" ORDER BY " + COLUMN_ORDER + " DESC";
//
//            SQLiteDatabase db = this.getWritableDatabase();
//            Cursor cursor = db.rawQuery(query, null);
//
//
//            if (cursor.moveToFirst()) {
//                cursor.moveToFirst();
//
//                //want to know how many business are there
//                int cnt = cursor.getCount();
//                Log.v("myapp", Integer.toString(cnt) + " similar 4s business found by yelp phone number NEW function");
//
//                //return true if you find records
//                if (cnt > 0) {
//                    id = cursor.getString(0);
//                    Log.v("myapp","found similar business in yelp and 4s with id: "+ id + " and name " + cursor.getString(4));
//                }
//                cursor.close();
//
//            } else {
//
//            }
//
//            cursor.close();
//            db.close();
//
//            //
//
//
//        }
//
//
//        //return id
//        return id;
//        //
//    }
//
//
//    //all ids array 4s
//    //all ids array 4s
//    public List<String> Ids() {
//
//        String query = "Select * FROM " + TABLE_BUSINESS + " ORDER BY " + COLUMN_ORDER + " DESC"; //+ " WHERE " + COLUMN_ID + " =  \"" + id + "\"";
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        int cnt = cursor.getCount();
//
//        List<String> allIdArray = new ArrayList<>();
//
//        try {
//
//            while (cursor.moveToNext()) {
//
//                allIdArray.add(cursor.getString(0));
//
//            }
//        } finally {
//            cursor.close();
//        }
//
//        cursor.close();
//        db.close();
//
//        return allIdArray;
//    }
//    //
//
//
//    //
//
//
//    // THESE FUNCTIONS ARE HERE TO HELP KEEPING NUMBE ROF RECORDS  BELOW 100
//
//    public void delete_first_row_0()
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "Select * FROM " + TABLE_BUSINESS;
//        Cursor cursor = db.rawQuery(query, null);
//
//        if(cursor.moveToFirst()) {
//            String rowId = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
//
//            db.delete(TABLE_BUSINESS, COLUMN_ID + "=?", new String[]{rowId});
//        }
//        db.close();
//    }
//
//
//    //
//    public void delete_first_row_1() {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "Select * FROM " + TABLE_BUSINESS;
//        Cursor cursor = db.rawQuery(query, null);
//
//        //delete first record
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            String id = cursor.getString(0);
//            //
//            db.delete(TABLE_BUSINESS, COLUMN_ID + "=?", new String[] { id });
//        }
//        //
//        cursor.close();
//        db.close();
//        //
//    }
//    //
//
//    //
//    public int number_of_records(){
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String query = "Select * FROM " + TABLE_BUSINESS; //+ " WHERE " + COLUMN_ID + " =  \"" + id + "\"";
//        Cursor cursor = db.rawQuery(query, null);
//
//        int cnt = cursor.getCount();
//
//        cursor.close();
//        db.close();
//
//
//        return cnt;
//    }
//
//    //
//
//    // delete records by query
//    public void deleteQuery(String query)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        try
//        {
//            db.delete(TABLE_BUSINESS, "_query = ?", new String[] { query });
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            db.close();
//        }
//    }
//    //

}