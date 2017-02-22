package com.n.nathan.pillme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

public class AddPillActivity extends AppCompatActivity {
    //
    List<String> med_list;
    //
    TextView med_text_view_1;
    TextView med_text_view_2;
    TextView med_text_view_3;
    TextView med_text_view_4;
    TextView med_text_view_5;
    //
    EditText editText;
    //
    String symbol = "regularRoundPill";
    //
    ImageButton pill_add_Button;
    //
    private static final int PERIOD = 60000;
    //
//    ImageView pill1_imgView;
//    ImageView pill2_imgView;
//    ImageView pill3_imgView;
//    ImageView pill4_imgView;
//    ImageView pill5_imgView;
//    ImageView pill6_imgView;
//    ImageView pill7_imgView;
//    ImageView pill8_imgView;
//    ImageView pill9_imgView;
//    ImageView pill10_imgView;
//    ImageView pill11_imgView;
//    ImageView pill12_imgView;
    //

    //

    //
    NumberPicker numberPicker;
    NumberPicker numberPicker_minute;
    //

    //
    Switch alarm_switch;
    TextView set_alarm_for_how_many_hours;
    //
    Boolean hasAlarmSetup = false;
    //

    //
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    RadioButton radioButton7;
    //
    String day_time_final = null;
    //

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pill);
        //

        //
        Bundle extras = getIntent().getExtras();

        // can be simplified
        final String day_time = extras.getString("day_time", null);
        day_time_final = day_time;
        //

        //
        Log.v("AddPillActivity", day_time);
        //

        //
        med_list = new ArrayList<>();
        //
        Resources res = getResources();
        final String[] medic_array = res.getStringArray(R.array.medication_name);
        //

        //
        med_text_view_1 = (TextView) findViewById(R.id.med_text_1);
        med_text_view_2 = (TextView) findViewById(R.id.med_text_2);
        med_text_view_3 = (TextView) findViewById(R.id.med_text_3);
        med_text_view_4 = (TextView) findViewById(R.id.med_text_4);
        med_text_view_5 = (TextView) findViewById(R.id.med_text_5);
        //

        //
        pill_add_Button = (ImageButton) findViewById(R.id.pill_add_activity);
        //

//        //
//        pill1_imgView = (ImageView) findViewById(R.id.pill_symbol_1);
//        pill2_imgView = (ImageView) findViewById(R.id.pill_symbol_2);
//        pill3_imgView = (ImageView) findViewById(R.id.pill_symbol_3);
//        pill4_imgView = (ImageView) findViewById(R.id.pill_symbol_4);
//        pill5_imgView = (ImageView) findViewById(R.id.pill_symbol_5);
//        pill6_imgView = (ImageView) findViewById(R.id.pill_symbol_6);
//        pill7_imgView = (ImageView) findViewById(R.id.pill_symbol_7);
//        pill8_imgView = (ImageView) findViewById(R.id.pill_symbol_8);
//        pill9_imgView = (ImageView) findViewById(R.id.pill_symbol_9);
//        pill10_imgView = (ImageView) findViewById(R.id.pill_symbol_10);
//        pill11_imgView = (ImageView) findViewById(R.id.pill_symbol_11);
//        pill12_imgView = (ImageView) findViewById(R.id.pill_symbol_12);
//        //

        //
        radioButton1 = (RadioButton) findViewById(R.id.Day1_AM_radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.Day2_AM_radioButton);
        radioButton3 = (RadioButton) findViewById(R.id.Day3_AM_radioButton);
        radioButton4 = (RadioButton) findViewById(R.id.Day4_AM_radioButton);
        radioButton5 = (RadioButton) findViewById(R.id.Day5_AM_radioButton);
        radioButton6 = (RadioButton) findViewById(R.id.Day6_AM_radioButton);
        radioButton7 = (RadioButton) findViewById(R.id.Day7_AM_radioButton);
        //

        //hide day1 anyways ???
        radioButton1.setVisibility(View.GONE);
        //

        //hide day2 if we are on Day2
        if (day_time.substring(0, 4).equals("day2")) {
            //
            radioButton2.setVisibility(View.GONE);
            //
        }
        //

        //hide day2,3 if we are on Day3
        if (day_time.substring(0, 4).equals("day3")) {
            //
            radioButton2.setVisibility(View.GONE);
            //

            //
            radioButton3.setVisibility(View.GONE);
            //
        }
        //

        //hide day2,3,4 if we are on Day4
        if (day_time.substring(0, 4).equals("day4")) {
            //
            radioButton2.setVisibility(View.GONE);
            //

            //
            radioButton3.setVisibility(View.GONE);
            //

            //
            radioButton4.setVisibility(View.GONE);
            //
        }
        //

        //hide day2,3,4,5 if we are on Day5
        if (day_time.substring(0, 4).equals("day5")) {
            //
            radioButton2.setVisibility(View.GONE);
            //

            //
            radioButton3.setVisibility(View.GONE);
            //

            //
            radioButton4.setVisibility(View.GONE);
            //

            //
            radioButton5.setVisibility(View.GONE);
            //
        }
        //

        //hide day2,3,4,5,6 if we are on Day6
        if (day_time.substring(0, 4).equals("day6")) {
            //
            radioButton2.setVisibility(View.GONE);
            //

            //
            radioButton3.setVisibility(View.GONE);
            //

            //
            radioButton4.setVisibility(View.GONE);
            //

            //
            radioButton5.setVisibility(View.GONE);
            //

            //
            radioButton6.setVisibility(View.GONE);
            //
        }
        //

        //hide day2,3,4,5,6 if we are on Day6
        if (day_time.substring(0, 4).equals("day7")) {
            //
            radioButton2.setVisibility(View.GONE);
            //

            //
            radioButton3.setVisibility(View.GONE);
            //

            //
            radioButton4.setVisibility(View.GONE);
            //

            //
            radioButton5.setVisibility(View.GONE);
            //

            //
            radioButton6.setVisibility(View.GONE);
            //

            //
            radioButton7.setVisibility(View.GONE);
            //
        }
        //

        //
        //hide irrelavant radioButtons

        //

        //

        // SETTING UP alarm HOURS FROM NOW
        numberPicker = (NumberPicker) findViewById(R.id.hour_number_picker);
        numberPicker.setMaxValue(23);
        numberPicker.setMinValue(0);
        numberPicker.setValue(0);
        //

        // SETTING UP alarm HOURS FROM NOW
        numberPicker_minute = (NumberPicker) findViewById(R.id.minute_number_picker);
        numberPicker_minute.setMaxValue(59);
        numberPicker_minute.setMinValue(0);
        numberPicker_minute.setValue(0);
        //

        //
        //setting up SWITCH
        //
        alarm_switch = (Switch) findViewById(R.id.alarm_switch);
        //Hide number picker and complimentary text
        set_alarm_for_how_many_hours = (TextView) findViewById(R.id.set_alarm_for_how_many_hours);
        set_alarm_for_how_many_hours.setVisibility(View.GONE);
        numberPicker.setVisibility(View.GONE);
        numberPicker_minute.setVisibility(View.GONE);
        //

        //
        alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //

                    //set has alarm to true
                    hasAlarmSetup = true;
                    //

                    //
                    set_alarm_for_how_many_hours.setVisibility(View.VISIBLE);

                    numberPicker.setVisibility(View.VISIBLE);
                    numberPicker_minute.setVisibility(View.VISIBLE);
                    //
                } else if (!isChecked) {
                    //

                    //set has alarm to false
                    hasAlarmSetup = false;
                    //

                    set_alarm_for_how_many_hours.setVisibility(View.GONE);

                    numberPicker.setVisibility(View.GONE);
                    numberPicker_minute.setVisibility(View.GONE);
                    //
                }
            }
        });
        //

        //
        editText = (EditText) findViewById(R.id.med_add_edit_text);
        //
        //
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        //
        //
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
                med_text_view_1.setText(" ");
                med_text_view_2.setText(" ");
                med_text_view_3.setText(" ");
                med_text_view_4.setText(" ");
                med_text_view_5.setText(" ");
                //

                // get the first lette rmake it capitol

                String search_term = editText.getText().toString();
                //
                if (search_term.length() > 1) {
                    //
                    char first_letter = search_term.charAt(0);
                    String first_letter_string = Character.toString(first_letter).toUpperCase();
                    //
                    String refind_search_term = search_term.substring(1);
                    search_term = first_letter_string + refind_search_term;
                    //
                } else if (search_term.length() == 1) {
                    //
                    char first_letter = search_term.charAt(0);
                    String first_letter_string = Character.toString(first_letter).toUpperCase();
                    //
                    search_term = first_letter_string;
                    //
                }
                //

                for (int i = 0; i < medic_array.length; i++) {
                    String med_string = medic_array[i];
                    //
                    if (med_string.startsWith(search_term)) {
                        //
                        //Toast.makeText(getApplicationContext(), "found", Toast.LENGTH_SHORT).show();
                        //
                        med_list.add(med_string);
                        //
                    }
                }
                //

                //
                int list_size = med_list.size();
                Log.v("AddPillActivity", " " + list_size);
                //

                switch (list_size) {

                    case 1:
                        med_text_view_1.setText(med_list.get(0));
                        med_text_view_2.setText(" ");
                        med_text_view_3.setText(" ");
                        med_text_view_4.setText(" ");
                        med_text_view_5.setText(" ");
                        break;
                    case 2:
                        med_text_view_1.setText(med_list.get(0));
                        med_text_view_2.setText(med_list.get(1));
                        med_text_view_3.setText(" ");
                        med_text_view_4.setText(" ");
                        med_text_view_5.setText(" ");
                        break;
                    case 3:
                        med_text_view_1.setText(med_list.get(0));
                        med_text_view_2.setText(med_list.get(1));
                        med_text_view_3.setText(med_list.get(2));
                        med_text_view_4.setText(" ");
                        med_text_view_5.setText(" ");
                        break;
                    case 4:
                        med_text_view_1.setText(med_list.get(0));
                        med_text_view_2.setText(med_list.get(1));
                        med_text_view_3.setText(med_list.get(2));
                        med_text_view_4.setText(med_list.get(3));
                        med_text_view_5.setText(" ");
                        break;
                    case 5:
                        med_text_view_1.setText(med_list.get(0));
                        med_text_view_2.setText(med_list.get(1));
                        med_text_view_3.setText(med_list.get(2));
                        med_text_view_4.setText(med_list.get(3));
                        med_text_view_5.setText(med_list.get(4));
                        break;

                    default:
                        if (list_size > 5) {
                            med_text_view_1.setText(med_list.get(0));
                            med_text_view_2.setText(med_list.get(1));
                            med_text_view_3.setText(med_list.get(2));
                            med_text_view_4.setText(med_list.get(3));
                            med_text_view_5.setText(med_list.get(4));
                        }
                        break;

                }

                //

                //clear med_list here
                med_list.clear();
                //

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //

        //
        //setup onLongcliclk listeners to add the names to the relevant shared pref (day - time)

        //med textView1 onClickListener - adding from first suggestion
        med_text_view_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //

                //
                return false;
                //
            }
        });
        //

        //adding from secong suggestion
        med_text_view_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //

                //
                String pillName = med_text_view_2.getText().toString().trim();
                add_pill_function(day_time_final, pillName , hasAlarmSetup , symbol , false );
                //

                //
                return false;
                //
            }
        });
        //

        //adding from third suggestion
        med_text_view_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                String pillName = med_text_view_3.getText().toString().trim();
                add_pill_function(day_time_final, pillName , hasAlarmSetup , symbol , false  );
                //

                //
                return false;
                //

            }
        });
        //

        //adding from fourth suggesrtion
        med_text_view_4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                String pillName = med_text_view_4.getText().toString().trim();
                add_pill_function(day_time_final, pillName , hasAlarmSetup , symbol  , false );
                //

//                if (day_time != null) {
//                    //see if drug already exists
//                    List<String> drugs_already_exist = new ArrayList<>();
//                    SharedPreferences pref = getSharedPreferences(day_time, MODE_PRIVATE);
//                    String med1 = pref.getString("med1", null);
//                    if (med1 != null) {
//                        drugs_already_exist.add(med1);
//                    }
//                    String med2 = pref.getString("med2", null);
//                    if (med2 != null) {
//                        drugs_already_exist.add(med2);
//                    }
//                    String med3 = pref.getString("med3", null);
//                    if (med3 != null) {
//                        drugs_already_exist.add(med3);
//                    }
//                    String med4 = pref.getString("med4", null);
//                    if (med4 != null) {
//                        drugs_already_exist.add(med4);
//                    }
//                    String med5 = pref.getString("med5", null);
//                    if (med5 != null) {
//                        drugs_already_exist.add(med5);
//                    }
//                    //
//
//                    //if drug does not exist already, add it to shared pref
//                    if (!(drugs_already_exist.contains(med_text_view_4.getText().toString().trim()))) {
//
//                        if (med1 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med1", med_text_view_4.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med1-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med1-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med1 added: " + med_text_view_4.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_4.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//
//                            //if medication has been added finsih activity
//                            finish();
//                            //
//
//                        } else if (med2 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med2", med_text_view_4.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med2-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med2-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med2 added: " + med_text_view_4.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_4.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//
//                            //if medication has been added finsih activity
//                            finish();
//                            //
//
//                        } else if (med3 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med3", med_text_view_4.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med3-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med3-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med3 added: " + med_text_view_4.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_4.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//
//                            //if medication has been added finsih activity
//                            finish();
//                            //
//
//                        } else if (med4 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med4", med_text_view_4.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med4-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med4-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med4 added: " + med_text_view_4.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_4.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//
//                            //if medication has been added finsih activity
//                            finish();
//                            //
//
//                        } else if (med5 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med5", med_text_view_5.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med5-symbol", symbol);
//                            }
//                            //
//
//                            //
//                            editor.putBoolean("med5-alarm", hasAlarmSetup);
//                            //
//
//                            //
//                            Log.v("AddPillActivity", "med5 added: " + med_text_view_5.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_5.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//
//                            //if medication has been added finsih activity
//                            finish();
//                            //
//
//                        }
//                        //
////                      SharedPreferences pref = getSharedPreferences(day_time, MODE_PRIVATE);
////                      set_1 = pref.getStringSet("key", null);
//                        //
//                        else {
//                            //
//                            Toast.makeText(v.getContext(), "No spots left to add meds or entry name is empty/too short", Toast.LENGTH_SHORT).show();
//                            //
//                        }
//                        //
//
//                        //end of checking if drug already exists
//                    } else {
//                        Toast.makeText(v.getContext(), "Medication already exists  or entry too short", Toast.LENGTH_SHORT).show();
//                        //end of checking if drug already exists
//                    }
////
//                    //end of checking for day_time ot to be null
//                }
//

                //
                return false;
                //

            }
        });
        //

        //adding from fifth suggesrtion
        med_text_view_5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //
                String pillName = med_text_view_5.getText().toString().trim();
                add_pill_function(day_time_final, pillName , hasAlarmSetup , symbol , false  );
                //

                //
                return false;
                //

            }
        });
        //

        //

        // PILL ADD ADD  - PILL BUTTON
        pill_add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //
                String pillName = editText.getText().toString().trim();
                //
                add_pill_function( day_time_final, pillName , hasAlarmSetup , symbol , false  );
                //
            }
        });
        // PILL ADD ADD - PILL BUTTON

        //

//        // pill image ONE click listener
//        pill1_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "capsole";
//                //
//
//                //
//                pill1_imgView.setImageResource(R.mipmap.capsole_ticked);
//                //
//
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//
//                //
//                //un-tick - un-select others!
//                //
//
//                //
//
//            }
//        });
//        //
//
//        //
//        pill2_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "regularRoundPill";
//                //
//
//                //
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//            }
//        });
//        //
//
//        //
//        pill3_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "granolacapsole";
//                //
//
//                //
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill4_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "calciumpill";
//                //
//
//                //
//                pill4_imgView.setImageResource(R.mipmap.calciumpill_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill5_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "rectangularpill";
//                //
//
//                //
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill6_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "ointment";
//                //
//
//                //
//                pill6_imgView.setImageResource(R.mipmap.ointment_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill7_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "dropper";
//                //
//
//                //
//                pill7_imgView.setImageResource(R.mipmap.dropper_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill8_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "inhaler";
//                //
//
//                //
//                pill8_imgView.setImageResource(R.mipmap.inhaler_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill9_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "syringe";
//                //
//
//                //
//                pill9_imgView.setImageResource(R.mipmap.syringe_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill10_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "syrup";
//                //
//
//                //
//                pill10_imgView.setImageResource(R.mipmap.syrup_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill11_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "spray";
//                //
//
//                //
//                pill11_imgView.setImageResource(R.mipmap.spray_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar);
//                //
//
//            }
//        });
//        //
//
//        //
//        pill12_imgView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //
//                symbol = "bloodsugar";
//                //
//
//                //
//                pill12_imgView.setImageResource(R.mipmap.bloodsugar_ticked);
//                //
//
//                //and also unTICKED for others, unselect others
//                pill1_imgView.setImageResource(R.mipmap.capsole);
//                pill2_imgView.setImageResource(R.mipmap.regularroundpill);
//                pill3_imgView.setImageResource(R.mipmap.granolacapsole);
//                pill4_imgView.setImageResource(R.mipmap.calciumpill);
//                pill5_imgView.setImageResource(R.mipmap.rectangularpill);
//                pill6_imgView.setImageResource(R.mipmap.ointment);
//                pill7_imgView.setImageResource(R.mipmap.dropper);
//                pill8_imgView.setImageResource(R.mipmap.inhaler);
//                pill9_imgView.setImageResource(R.mipmap.syringe);
//                pill10_imgView.setImageResource(R.mipmap.syrup);
//                pill11_imgView.setImageResource(R.mipmap.spray);
//                //
//
//            }
//        });
//        //

        //
        load_images load_images_instance = new load_images();
        load_images_instance.execute();
        //

        //
        //end of onCreate
        //

    }
    //

    // ADD ALARM ALARM ADD
    public Boolean add_alarm(String day_time, String medNumber, String pillName, String symbol)
    {

        //EDUCATIONAL
        //if(new Integer(Calendar.HOUR_OF_DAY) + new Integer(numberPicker.getValue()) <= new Integer(24)){

        //

        //get current time (hour and minute)
        SharedPreferences pref = getSharedPreferences("initialtimezone", MODE_PRIVATE);
        TimeZone tz = TimeZone.getTimeZone(pref.getString("initialtimezone","notimezone"));
        final Calendar calendar = Calendar.getInstance(tz);

//        Date date = new Date();
//        calendar.setTime(date);

        //
        int timeNowHour = calendar.get(Calendar.HOUR_OF_DAY);
        int timeNowMinute = calendar.get(Calendar.MINUTE);
        Log.v("MainActivity", "timeNowHour " + timeNowHour);
        Log.v("MainActivity", "timeNowMinute " + timeNowMinute);
        //

        if ( (MainActivity.todayIs.equals(MainActivity.whichDay)) && (numberPicker.getValue() < timeNowHour || (numberPicker.getValue() == timeNowHour && (numberPicker_minute.getValue() < timeNowMinute)))) {

            Toast.makeText(this, "please set alarm time in FUTURE", Toast.LENGTH_SHORT).show();

            return false;

        }
        //same hour different tminutes
        else if (numberPicker.getValue() == timeNowHour && (numberPicker_minute.getValue() > timeNowMinute)) {
            //WRITE TO SHARED PREF

            //save set alarm to shared pref so when alarms are cancelled i can ge them back
            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();

            // example: med1-hour
            editor.putInt(medNumber + "-hour", numberPicker.getValue());

            // med1-minute
            editor.putInt(medNumber + "-minute", numberPicker_minute.getValue());
            editor.commit();
            //
            Log.v("MainActivity", "number Picker value: (hour) " + numberPicker.getValue());
            Log.v("MainActivity", "number Picker value: (Minute) " + numberPicker_minute.getValue());
            //

            //
            int remainingMinutes = numberPicker_minute.getValue() - timeNowMinute;
            //

            //
            Log.v("myapp", "remainingMinutes" + remainingMinutes);
            //

            //
            Log.v("myapp", "alarm set for " + remainingMinutes);
            //

            //PollReceiver.scheduleAlarms(this,remainingHoursMinutes+remainingMinutes+numberPicker_minute.getValue());
            PollReceiver.scheduleAlarms(this, (remainingMinutes) * 60000);
            //

            //taking care of adding repeat MED
            String am_pm = day_time.substring(5);
            //
            ArrayList<Integer> repeatDays = new ArrayList<Integer>();

            if (radioButton2.isChecked()) {

                //
                repeatDays.add(2);
                //
                add_pill_function("day2-" + am_pm , pillName , hasAlarmSetup , symbol , true );
                //
                Log.v("myapp","day2- + am_pm is: " + "day2-" + am_pm );
                //
            }
            if (radioButton3.isChecked()) {
                repeatDays.add(3);
                //add pill to day3
                add_pill_function("day3-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton4.isChecked()) {
                repeatDays.add(4);
                //add pill to day4
                add_pill_function("day4-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton5.isChecked()) {
                repeatDays.add(5);
                //add pill to day5
                add_pill_function("day5-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton6.isChecked()) {
                repeatDays.add(6);
                //add pill to day6
                add_pill_function("day6-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton7.isChecked()) {
                repeatDays.add(7);
                //add pill to day7
                add_pill_function("day7-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            //

            // add alarm for all selected repeat days
            repeat_alarm(day_time, repeatDays, remainingMinutes);
            //

            //
            return true;
            //

        } else {

            //write ot shared pref

            //save set alarm to shared pref so when alarms are cancelled i can ge them back
            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
            editor.putInt(medNumber + "-hour", numberPicker.getValue());
            editor.putInt(medNumber + "-minute", numberPicker_minute.getValue());
            editor.commit();
            //
            Log.v("MainActivity", "number Picker value: (hour) " + numberPicker.getValue());
            // example: med1-hour
            Log.v("MainActivity", "number Picker value: (Minute) " + numberPicker_minute.getValue());
            // med1-minute


            //
            int remainingMinutes = 60 - timeNowMinute;
            Log.v("myapp", "remainingMinutes" + remainingMinutes);
            //

            int remainingHoursMinutes = ((numberPicker.getValue()) - (timeNowHour + 1)) * 60;
            Log.v("myapp", "remainingHours" + remainingHoursMinutes);

            // this lagter to be fed to  repeat_alarm function
            int alarm_set_for = remainingHoursMinutes + remainingMinutes + numberPicker_minute.getValue();
            //
            Log.v("myapp", "alarm set for " + alarm_set_for + " minutes from now.");
            //

            //System.currentTimeMillis()+(remainingHoursMinutes+remainingMinutes+numberPicker_minute.getValue())*60000,

            //PollReceiver.scheduleAlarms(this,remainingHoursMinutes+remainingMinutes+numberPicker_minute.getValue());
            PollReceiver.scheduleAlarms(this, (remainingHoursMinutes + remainingMinutes + numberPicker_minute.getValue()) * 60000);

            //

            //
            //take care of reapiting here
            //

            //taking care of adding repeat MED
            String am_pm = day_time.substring(5);
            //
            ArrayList<Integer> repeatDays = new ArrayList<>();

            if (radioButton2.isChecked()) {

                //
                repeatDays.add(2);
                //
                add_pill_function("day2-" + am_pm , pillName , hasAlarmSetup , symbol , true );
                //
            }
            if (radioButton3.isChecked()) {
                repeatDays.add(3);
                //add pill to day3
                add_pill_function("day3-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton4.isChecked()) {
                repeatDays.add(4);
                //add pill to day4
                add_pill_function("day4-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton5.isChecked()) {
                repeatDays.add(5);
                //add pill to day5
                add_pill_function("day5-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton6.isChecked()) {
                repeatDays.add(6);
                //add pill to day6
                add_pill_function("day6-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            if (radioButton7.isChecked()) {
                repeatDays.add(7);
                //add pill to day7
                add_pill_function("day7-" + am_pm , pillName , hasAlarmSetup , symbol , true  );
                //
            }
            //

            //
            repeat_alarm(day_time, repeatDays, alarm_set_for);
            //

            //
            return true;
            //

        }
        //

        //
    }
    //

    //

    public void repeat_alarm(String day_time, ArrayList<Integer> repeatDays, int remainingMinutes) {

        //get day number
        String day_number_string = day_time.substring(3, 4);
        int day_number = Integer.parseInt(day_number_string);
        Log.v("myapp", "day number is:" + day_number);
        //

        //

        //get day difference and schedule alarm
        for (int i = 0; i < repeatDays.size(); i++) {
            //
            Log.v("myapp", "day from repeatDays arraylist is: " + repeatDays.get(i));

            int day_difference = repeatDays.get(i) - day_number;

            Log.v("myapp", "day_difference is:" + day_difference);
            //

            PollReceiver.scheduleAlarms(this, day_difference * 24 * 60 * 60000 + remainingMinutes * 60000);
            int day_difrence_and_remaining_minutes = day_difference * 24 * 60 * 60000 + remainingMinutes * 60000;

            //
            Log.v("myapp", "alarm scheduled for " + day_difrence_and_remaining_minutes + " (milliseconds) form now");
            //

        }


        //end of repeat_alarm
    }
    //

    //



    // ADD_PILL_FUNCTION add pill function add pill
    public void add_pill_function( String day_time, String pillName, Boolean hasAlarmSetup, String symbol, Boolean justAddTimeandMinute ) {
        //

        //THIS CAN BE A SEPERATYE FUNCTION

        //add pill
        if (day_time != null) {

            //see if drug already exists
            List<String> drugs_already_exist = new ArrayList<>();

            SharedPreferences pref = getSharedPreferences(day_time, MODE_PRIVATE);
            String med1 = pref.getString("med1", null);

            if (med1 != null) {
                drugs_already_exist.add(med1);
            }
            String med2 = pref.getString("med2", null);
            if (med2 != null) {
                drugs_already_exist.add(med2);
            }
            String med3 = pref.getString("med3", null);
            if (med3 != null) {
                drugs_already_exist.add(med3);
            }
            String med4 = pref.getString("med4", null);
            if (med4 != null) {
                drugs_already_exist.add(med4);
            }
            String med5 = pref.getString("med5", null);
            if (med5 != null) {
                drugs_already_exist.add(med5);
            }
            //
            for (int i = 0; i < drugs_already_exist.size(); i++) {
                Log.v("AddPillActivity", drugs_already_exist.get(i));
            }
            //


            //if drug does not exist already, add it to shared pref
            //if (!(drugs_already_exist.contains(editText.getText().toString().trim())) && editText.getText().toString().trim().length() > 2) {
            if (!(drugs_already_exist.contains(pillName) && pillName.length() > 2)) {


                if (med1 == null) {

                    if(!justAddTimeandMinute)
                    {
                        //
                        Boolean continue_process = true;

                        //
                        if (hasAlarmSetup) {
                            //add alarm first
                            continue_process = add_alarm(day_time,"med1",pillName,symbol);
                            //
                        }

                        //
                        if (continue_process) {

                            //setup shared pref
                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                            editor.putString("med1", pillName);

                            //ading symbol -  should be RegularRoundPill by default
                            if (symbol != null) {
                                editor.putString("med1-symbol", symbol);
                            }

                            //
                            editor.putBoolean("med1-alarm", hasAlarmSetup);
                            //


                            Log.v("AddPillActivity", "med1 added: " + pillName);
                            Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                            editor.commit();
                            //

                            //finish and fuckOFF
                            finish();
                            //
                        }

                    }else{
                        //

                        //setup shared pref
                        SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                        editor.putString("med1", pillName);

                        //ading symbol -  should be RegularRoundPill by default
                        if (symbol != null) {
                            editor.putString("med1-symbol", symbol);
                        }

                        //
                        editor.putBoolean("med1-alarm", hasAlarmSetup);
                        //


                        Log.v("AddPillActivity", "med1 added: " + pillName);
                        Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                        editor.commit();
                        //

                        //finish and fuckOFF
                        finish();
                        //
                        //
                        just_add_hour_minute(day_time, "med1");
                        //
                    }
                    //
                } else if (med2 == null) {

                    if(!justAddTimeandMinute)
                    {

                        //
                        Boolean continue_process = true;

                        //
                        if (hasAlarmSetup) {
                            //add alarm first
                            continue_process = add_alarm(day_time,"med2",pillName,symbol);
                            //
                        }

                        //
                        if (continue_process) {

                            //setup shared pref
                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                            editor.putString("med2", pillName);

                            //ading symbol -  should be RegularRoundPill by default
                            if (symbol != null) {
                                editor.putString("med2-symbol", symbol);
                            }

                            //
                            editor.putBoolean("med2-alarm", hasAlarmSetup);
                            //


                            Log.v("AddPillActivity", "med2 added: " + pillName);
                            Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                            editor.commit();
                            //


                            //finish and fuckOFF
                            finish();
                            //

                        }

                    }
                    else{

                        //

                        //setup shared pref
                        SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                        editor.putString("med2", pillName);

                        //ading symbol -  should be RegularRoundPill by default
                        if (symbol != null) {
                            editor.putString("med2-symbol", symbol);
                        }

                        //
                        editor.putBoolean("med2-alarm", hasAlarmSetup);
                        //


                        Log.v("AddPillActivity", "med2 added: " + pillName);
                        Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                        editor.commit();
                        //


                        //finish and fuckOFF
                        finish();
                        //

                        //

                        //
                        just_add_hour_minute(day_time, "med2");
                        //
                    }

                } else if (med3 == null) {
                    //
                    if(!justAddTimeandMinute)
                    {
                        //
                        Boolean continue_process = true;

                        //
                        if (hasAlarmSetup) {
                            //add alarm first
                            continue_process = add_alarm(day_time,"med3",pillName,symbol);
                            //
                        }
                        //

                        //
                        if (continue_process) {
                            //

                            //setup shared pref
                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                            editor.putString("med3", pillName);

                            //ading symbol -  should be RegularRoundPill by default
                            if (symbol != null) {
                                editor.putString("med3-symbol", symbol);
                            }

                            //
                            editor.putBoolean("med3-alarm", hasAlarmSetup);
                            //


                            Log.v("AddPillActivity", "med3 added: " + pillName);
                            Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                            editor.commit();
                            //


                            //finish and fuckOFF
                            finish();
                            //

                        }
                        //
                    }
                    else{
                        //

                        //

                        //setup shared pref
                        SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                        editor.putString("med3", pillName);

                        //ading symbol -  should be RegularRoundPill by default
                        if (symbol != null) {
                            editor.putString("med3-symbol", symbol);
                        }

                        //
                        editor.putBoolean("med3-alarm", hasAlarmSetup);
                        //


                        Log.v("AddPillActivity", "med3 added: " + pillName);
                        Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                        editor.commit();
                        //


                        //finish and fuckOFF
                        finish();
                        //

                        //

                        //
                        just_add_hour_minute(day_time, "med3");
                        //
                    }

                } else if (med4 == null) {

                    //
                    if(!justAddTimeandMinute) {
                        //
                        Boolean continue_process = true;

                        //
                        if (hasAlarmSetup) {
                            //add alarm first
                            continue_process = add_alarm(day_time,"med4", pillName, symbol);
                            //
                        }

                        //
                        if (continue_process) {

                            //setup shared pref
                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                            editor.putString("med4", pillName);

                            //ading symbol -  should be RegularRoundPill by default
                            if (symbol != null) {
                                editor.putString("med4-symbol", symbol);
                            }

                            //
                            editor.putBoolean("med4-alarm", hasAlarmSetup);
                            //


                            Log.v("AddPillActivity", "med4 added: " + pillName);
                            Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                            editor.commit();
                            //


                            //finish and fuckOFF
                            finish();
                            //

                        }

                    } else
                    {
                        //

                        //

                        //setup shared pref
                        SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                        editor.putString("med4", pillName);

                        //ading symbol -  should be RegularRoundPill by default
                        if (symbol != null) {
                            editor.putString("med4-symbol", symbol);
                        }

                        //
                        editor.putBoolean("med4-alarm", hasAlarmSetup);
                        //


                        Log.v("AddPillActivity", "med4 added: " + pillName);
                        Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                        editor.commit();
                        //


                        //finish and fuckOFF
                        finish();
                        //

                        //
                        //
                        just_add_hour_minute(day_time, "med4");
                        //
                    }

                } else if (med5 == null) {

                    //
                    if(!justAddTimeandMinute)
                    {
                        //
                        Boolean continue_process = true;
                        //

                        //
                        if (hasAlarmSetup) {
                            //add alarm first
                            continue_process = add_alarm(day_time,"med5",pillName,symbol);
                            //
                        }

                        //
                        if (continue_process) {

                            //setup shared pref
                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                            editor.putString("med5", pillName);

                            //ading symbol -  should be RegularRoundPill by default
                            if (symbol != null) {
                                editor.putString("med5-symbol", symbol);
                            }

                            //
                            editor.putBoolean("med5-alarm", hasAlarmSetup);
                            //


                            Log.v("AddPillActivity", "med5 added: " + pillName);
                            Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                            editor.commit();
                            //


                            //finish and fuckOFF
                            finish();
                            //
                        }

                    } else{
                        //

                        //

                        //setup shared pref
                        SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
                        editor.putString("med5", pillName);

                        //ading symbol -  should be RegularRoundPill by default
                        if (symbol != null) {
                            editor.putString("med5-symbol", symbol);
                        }

                        //
                        editor.putBoolean("med5-alarm", hasAlarmSetup);
                        //


                        Log.v("AddPillActivity", "med5 added: " + pillName);
                        Toast.makeText(getApplicationContext(), pillName + " added", Toast.LENGTH_SHORT).show();
                        editor.commit();
                        //


                        //finish and fuckOFF
                        finish();

                        //


                        just_add_hour_minute(day_time, "med5");
                        //
                    }

                    //

                }

                else {
                    //
                    Toast.makeText(getApplicationContext(), "No spots left to add meds or entry name is empty/too short", Toast.LENGTH_SHORT).show();
                    //
                }

                //

                //end of checking if drug already exists
            } else {

                //
                Toast.makeText(getApplicationContext(), "Medication already exists or entry too short", Toast.LENGTH_SHORT).show();
                //end of checking if drug already exists

            }


            //end of checking for day_time ot to be null
        }
        //

        //
        //end of add_pill_function
        //
    }
    //

    //

    //add alarm no repeat
    public void just_add_hour_minute(String day_time, String medNumber)
    {

        //EDUCATIONAL
        //if(new Integer(Calendar.HOUR_OF_DAY) + new Integer(numberPicker.getValue()) <= new Integer(24)){

        Log.v("myapp","just_add_hour_minute was called with med number: " + medNumber + " and day_time: " + day_time );


        //save set alarm to shared pref so when alarms are cancelled i can ge them back
        SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();

        // example: med1-hour
        editor.putInt(medNumber + "-hour", numberPicker.getValue());

        // med1-minute
        editor.putInt(medNumber + "-minute", numberPicker_minute.getValue());
        editor.commit();
        //

    }
    //

    //

    // moved findviewbyid for images to AsyncTask to reduce onCreate phase costs, move others later.
    private class load_images extends AsyncTask<Void, Void, Void>
    {
        //
        ImageView pill1_imgView;
        ImageView pill2_imgView;
        ImageView pill3_imgView;
        ImageView pill4_imgView;
        ImageView pill5_imgView;
        ImageView pill6_imgView;
        ImageView pill7_imgView;
        ImageView pill8_imgView;
        ImageView pill9_imgView;
        ImageView pill10_imgView;
        ImageView pill11_imgView;
        ImageView pill12_imgView;
        //

        //
        @Override
        protected void onPreExecute()
        {
            //

            //
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            //

            //
            pill1_imgView = (ImageView) findViewById(R.id.pill_symbol_1);
            pill2_imgView = (ImageView) findViewById(R.id.pill_symbol_2);
            pill3_imgView = (ImageView) findViewById(R.id.pill_symbol_3);
            pill4_imgView = (ImageView) findViewById(R.id.pill_symbol_4);
            pill5_imgView = (ImageView) findViewById(R.id.pill_symbol_5);
            pill6_imgView = (ImageView) findViewById(R.id.pill_symbol_6);
            pill7_imgView = (ImageView) findViewById(R.id.pill_symbol_7);
            pill8_imgView = (ImageView) findViewById(R.id.pill_symbol_8);
            pill9_imgView = (ImageView) findViewById(R.id.pill_symbol_9);
            pill10_imgView = (ImageView) findViewById(R.id.pill_symbol_10);
            pill11_imgView = (ImageView) findViewById(R.id.pill_symbol_11);
            pill12_imgView = (ImageView) findViewById(R.id.pill_symbol_12);
            //

            //
            return null;
            //
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
        //

            // pill image ONE click listener
            pill1_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "capsole";
                    //

                    //
                    pill1_imgView.setImageResource(R.mipmap.capsole_ticked);
                    //

                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);

                    //
                    //un-tick - un-select others!
                    //

                    //

                }
            });
            //

            //
            pill2_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "regularRoundPill";
                    //

                    //
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //
                }
            });
            //

            //
            pill3_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "granolacapsole";
                    //

                    //
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill4_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "calciumpill";
                    //

                    //
                    pill4_imgView.setImageResource(R.mipmap.calciumpill_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill5_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "rectangularpill";
                    //

                    //
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill6_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "ointment";
                    //

                    //
                    pill6_imgView.setImageResource(R.mipmap.ointment_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill7_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "dropper";
                    //

                    //
                    pill7_imgView.setImageResource(R.mipmap.dropper_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill8_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "inhaler";
                    //

                    //
                    pill8_imgView.setImageResource(R.mipmap.inhaler_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill9_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "syringe";
                    //

                    //
                    pill9_imgView.setImageResource(R.mipmap.syringe_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill10_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "syrup";
                    //

                    //
                    pill10_imgView.setImageResource(R.mipmap.syrup_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill11_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "spray";
                    //

                    //
                    pill11_imgView.setImageResource(R.mipmap.spray_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar);
                    //

                }
            });
            //

            //
            pill12_imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //
                    symbol = "bloodsugar";
                    //

                    //
                    pill12_imgView.setImageResource(R.mipmap.bloodsugar_ticked);
                    //

                    //and also unTICKED for others, unselect others
                    pill1_imgView.setImageResource(R.mipmap.capsole);
                    pill2_imgView.setImageResource(R.mipmap.regularroundpill);
                    pill3_imgView.setImageResource(R.mipmap.granolacapsole);
                    pill4_imgView.setImageResource(R.mipmap.calciumpill);
                    pill5_imgView.setImageResource(R.mipmap.rectangularpill);
                    pill6_imgView.setImageResource(R.mipmap.ointment);
                    pill7_imgView.setImageResource(R.mipmap.dropper);
                    pill8_imgView.setImageResource(R.mipmap.inhaler);
                    pill9_imgView.setImageResource(R.mipmap.syringe);
                    pill10_imgView.setImageResource(R.mipmap.syrup);
                    pill11_imgView.setImageResource(R.mipmap.spray);
                    //

                }
            });
            //

        //
        }
        //
    }
    //

    //

//END OF ACTIVITY
}
//

//

//LEGACY CODE
//                if (day_time != null) {
//                    //see if drug already exists
//                    List<String> drugs_already_exist = new ArrayList<>();
//                    SharedPreferences pref = getSharedPreferences(day_time, MODE_PRIVATE);
//                    String med1 = pref.getString("med1", null);
//                    if (med1 != null) {
//                        drugs_already_exist.add(med1);
//                    }
//                    String med2 = pref.getString("med2", null);
//                    if (med2 != null) {
//                        drugs_already_exist.add(med2);
//                    }
//                    String med3 = pref.getString("med3", null);
//                    if (med3 != null) {
//                        drugs_already_exist.add(med3);
//                    }
//                    String med4 = pref.getString("med4", null);
//                    if (med4 != null) {
//                        drugs_already_exist.add(med4);
//                    }
//                    String med5 = pref.getString("med5", null);
//                    if (med5 != null) {
//                        drugs_already_exist.add(med5);
//                    }
//                    //
//
//                    //if drug does not exist already, add it to shared pref
//                    if (!(drugs_already_exist.contains(med_text_view_5.getText().toString().trim()))) {
//
//                        if (med1 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med1", med_text_view_5.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med1-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med1-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med1 added: " + med_text_view_5.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_5.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med2 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med2", med_text_view_5.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med2-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med2-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med2 added: " + med_text_view_5.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_5.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med3 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med3", med_text_view_5.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med3-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med3-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med3 added: " + med_text_view_5.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_5.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med4 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med4", med_text_view_5.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med4-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med4-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med4 added: " + med_text_view_5.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_5.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med5 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med5", med_text_view_5.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med5-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med5-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med5 added: " + med_text_view_5.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_5.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        }
//
//                        //
//                        else {
//                            //
//                            Toast.makeText(v.getContext(), "No spots left to add meds or entry name is empty/too short", Toast.LENGTH_SHORT).show();
//                            //
//                        }
//                        //
//
//                        //
////                      SharedPreferences pref = getSharedPreferences(day_time, MODE_PRIVATE);
////                      set_1 = pref.getStringSet("key", null);
//                        //
//
//                        //end of checking if drug already exists
//                    } else {
//                        Toast.makeText(v.getContext(), "Medication already exists or entry too short", Toast.LENGTH_SHORT).show();
//                        //end of checking if drug already exists
//                    }
////
//                    //end of checking for day_time ot to be null
//
// }




//LEGACY CODE
//

//                if (day_time != null) {
//                    //see if drug already exists
//                    List<String> drugs_already_exist = new ArrayList<>();
//                    SharedPreferences pref = getSharedPreferences(day_time, MODE_PRIVATE);
//                    String med1 = pref.getString("med1", null);
//                    if (med1 != null) {
//                        drugs_already_exist.add(med1);
//                    }
//                    String med2 = pref.getString("med2", null);
//                    if (med2 != null) {
//                        drugs_already_exist.add(med2);
//                    }
//                    String med3 = pref.getString("med3", null);
//                    if (med3 != null) {
//                        drugs_already_exist.add(med3);
//                    }
//                    String med4 = pref.getString("med4", null);
//                    if (med4 != null) {
//                        drugs_already_exist.add(med4);
//                    }
//                    String med5 = pref.getString("med5", null);
//                    if (med5 != null) {
//                        drugs_already_exist.add(med5);
//                    }
//                    //
//
//                    //if drug does not exist already, add it to shared pref
//                    if (!(drugs_already_exist.contains(med_text_view_2.getText().toString().trim()))) {
//
//                        if (med1 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med1", med_text_view_2.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med1-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med1-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med1 added: " + med_text_view_2.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_2.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med2 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med2", med_text_view_2.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med2-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med2-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med2 added: " + med_text_view_2.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_2.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med3 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med3", med_text_view_2.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med3-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med3-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med3 added: " + med_text_view_2.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_2.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med4 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med4", med_text_view_2.getText().toString().trim());
//                            //
//
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med4-symbol", symbol);
//                            }
//                            //
//
//                            //
//                            editor.putBoolean("med4-alarm", hasAlarmSetup);
//                            //
//
//                            //
//                            Log.v("AddPillActivity", "med4 added: " + med_text_view_2.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_2.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        } else if (med5 == null) {
//
//                            //setup shared pref
//                            SharedPreferences.Editor editor = getSharedPreferences(day_time, MODE_PRIVATE).edit();
//                            editor.putString("med5", med_text_view_2.getText().toString().trim());
//                            //
//                            //ading symbol -  should be RegularRoundPill by default
//                            if (symbol != null) {
//                                editor.putString("med5-symbol", symbol);
//                            }
//                            //
//                            //
//                            editor.putBoolean("med5-alarm", hasAlarmSetup);
//                            //
//                            //
//                            Log.v("AddPillActivity", "med5 added: " + med_text_view_2.getText().toString().trim());
//                            Toast.makeText(getApplicationContext(), med_text_view_2.getText().toString().trim() + " added", Toast.LENGTH_SHORT).show();
//                            editor.commit();
//                            //
//
//                            //if medication has been added finsih activity
//                            finish();
//
//                        }
//                        //
////                      SharedPreferences pref = getSharedPreferences(day_time, MODE_PRIVATE);
////                      set_1 = pref.getStringSet("key", null);
//                        //
//                        else {
//                            //
//                            Toast.makeText(v.getContext(), "No spots left to add meds or entry name is empty/too short", Toast.LENGTH_SHORT).show();
//                            //
//                        }
//
//                        //end of checking if drug already exists
//                    } else {
//                        Toast.makeText(v.getContext(), "Medication already exists  or entry too short", Toast.LENGTH_SHORT).show();
//                        //end of checking if drug already exists
//                    }
////
//                    //end of checking for day_time ot to be null
//                }