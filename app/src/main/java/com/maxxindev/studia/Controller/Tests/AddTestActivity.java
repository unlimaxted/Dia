package com.maxxindev.studia.Controller.Tests;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.maxxindev.studia.DataBaseController.TestDB.TestDataBaseManager;
import com.maxxindev.studia.R;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTestActivity extends Activity {

    private final static String KEY_ID = "id";

    private TestDataBaseManager manager;

    private DateFormat format = DateFormat.getDateInstance();
    private Calendar mcurrentDate = Calendar.getInstance();

    private Cursor cursor;
    private Bundle bundle;

    private String tag;
    private String inPosition;

    private String name = "";
    private String date = "";
    private String dayOfWeek = "";
    private String hour = "";
    private String salon = "";
    private String description = "";
    private Integer qualification = 0;
    private Integer percent= 0;

    private String timePicked = "0";

    private Long idSubject = null;

    private Integer idSubjectInt = null;

    private EditText eTName;
    private EditText eTDate;
    private EditText eTHour;
    private EditText eTSalon;
    private EditText eTDescription;
    private EditText eTPercent;

    private Long idTest = null;

    private void openDB() {
        manager = new TestDataBaseManager(this);
        manager.open();
    }
    private void closeDB() {
        manager.close();
    }

    /**
     * Void to get the information and add a professor to data base
     */
    public void addTestToDataBase ()
    {

        openDB();

        eTName = (EditText) findViewById(R.id.eTTest);
        eTDate = (EditText) findViewById(R.id.eTDate);
        eTHour = (EditText) findViewById(R.id.eTHour);
        eTSalon = (EditText) findViewById(R.id.eTSalon);
        eTDescription = (EditText) findViewById(R.id.eTDescription);
        eTPercent = (EditText) findViewById(R.id.eTPercent);

        name = eTName.getText().toString();
        hour = eTHour.getText().toString();
        salon = eTSalon.getText().toString();
        description = eTDescription.getText().toString();
        percent = Integer.parseInt(eTPercent.getText().toString());

        manager = new TestDataBaseManager(this);

        manager.insert(name, date, dayOfWeek, timePicked, salon, description, qualification, percent, idSubjectInt);

    }

    /**
     * Void to initialize components
     */
    private void initComponents()
    {
        eTDate = (EditText) findViewById(R.id.eTDate);
        eTHour = (EditText) findViewById(R.id.eTHour);

        eTHour.setClickable(true);
        eTHour.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(AddTestActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            timePicked = updateTime(selectedHour, selectedMinute);
                            eTHour.setText(timePicked);
                        }
                    }, hour, minute, false);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            }

        });

        eTDate.setClickable(true);
        eTDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {

                    DatePickerDialog.OnDateSetListener datePick = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                            mcurrentDate.set(Calendar.YEAR, year);
                            mcurrentDate.set(Calendar.MONTH, monthOfYear);
                            mcurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            eTDate.setText(format.format(mcurrentDate.getTime()));
                            date = dateUpdate(mcurrentDate.getTime());

                            int dweek = mcurrentDate.get(Calendar.DAY_OF_WEEK);

                            switch (dweek) {
                                case Calendar.MONDAY:
                                    dayOfWeek = "MON";
                                    break;

                                case Calendar.TUESDAY:
                                    dayOfWeek = "TUE";;
                                    break;

                                case Calendar.WEDNESDAY:
                                    dayOfWeek = "WED";
                                    break;

                                case Calendar.THURSDAY:
                                    dayOfWeek = "THU";
                                    break;

                                case Calendar.FRIDAY:
                                    dayOfWeek = "FRI";
                                    break;

                                case Calendar.SATURDAY:
                                    dayOfWeek = "SAT";
                                    break;

                                case Calendar.SUNDAY:
                                    dayOfWeek = "SUN";
                                    break;

                            }
                        }

                    };

                    new DatePickerDialog(AddTestActivity.this, datePick, mcurrentDate
                            .get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                            mcurrentDate.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
    }

    /**
     * Void to uptade the date to the editText
     * @param date
     * @return
     */
    private String dateUpdate(Date date)
    {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat format = new SimpleDateFormat(myFormat, Locale.getDefault());

        String dateUpdated = format.format(date);

        Log.i(tag, "Update "+ dateUpdated);

        return dateUpdated;

    }

    /**
     * Void to uptade Time to format
     * @param hours
     * @param mins
     * @return
     */
    private String updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";


        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return aTime;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        initComponents();

        bundle = getIntent().getExtras();

        if (bundle != null)
        {
            idSubject = bundle.getLong(KEY_ID);
            idSubjectInt = idSubject.intValue();
            Log.i(tag," A ADD TEST ACTIVITY llego la ID "+ idSubject);
        }

        if( savedInstanceState != null)
        {
            idSubjectInt = savedInstanceState.getInt("idSubject");
            name = savedInstanceState.getString("names");
            date = savedInstanceState.getString("date");
            hour = savedInstanceState.getString("hour");
            salon = savedInstanceState.getString("salon");
            description = savedInstanceState.getString("description");
            percent = savedInstanceState.getInt("percent");

            eTName.setText(String.valueOf(name));
            eTDate.setText(String.valueOf(date));
            eTHour.setText(String.valueOf(hour));
            eTSalon.setText(String.valueOf(salon));
            eTDescription.setText(String.valueOf(description));
            eTPercent.setText(String.valueOf(percent));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test_add:
                addTestToDataBase();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

        eTName = (EditText) findViewById(R.id.eTTest);
        eTDate = (EditText) findViewById(R.id.eTDate);
        eTHour = (EditText) findViewById(R.id.eTHour);
        eTSalon = (EditText) findViewById(R.id.eTSalon);
        eTDescription = (EditText) findViewById(R.id.eTDescription);
        eTPercent = (EditText) findViewById(R.id.eTPercent);

        outState.putString("name", eTName.getText().toString());
        outState.putString("date", eTDate.getText().toString());
        outState.putString("hour", eTHour.getText().toString());
        outState.putString("salon", eTSalon.getText().toString());
        outState.putString("description", eTDescription.getText().toString());
        outState.putString("percent", eTPercent.getText().toString());
        outState.putInt("idSubject", idSubjectInt);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);

        eTName = (EditText) findViewById(R.id.eTTest);
        eTDate = (EditText) findViewById(R.id.eTDate);
        eTHour = (EditText) findViewById(R.id.eTHour);
        eTSalon = (EditText) findViewById(R.id.eTSalon);
        eTDescription = (EditText) findViewById(R.id.eTDescription);
        eTPercent = (EditText) findViewById(R.id.eTPercent);

        idSubjectInt = savedInstanceState.getInt("idSubject");
        name = savedInstanceState.getString("names");
        date = savedInstanceState.getString("date");
        hour = savedInstanceState.getString("hour");
        salon = savedInstanceState.getString("salon");
        description = savedInstanceState.getString("description");
        percent = savedInstanceState.getInt("percent");

        eTName.setText(String.valueOf(name));
        eTDate.setText(String.valueOf(date));
        eTHour.setText(String.valueOf(hour));
        eTSalon.setText(String.valueOf(salon));
        eTDescription.setText(String.valueOf(description));
        eTPercent.setText(String.valueOf(percent));

    }

}
