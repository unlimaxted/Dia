package com.maxxindev.studia.Controller.Professor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.maxxindev.studia.DataBaseController.ProfessorDB.ProfessorDataBaseManager;
import com.maxxindev.studia.DataBaseController.SubjectsDB.SubjectDataBaseManager;
import com.maxxindev.studia.R;

import java.util.ArrayList;
import java.util.List;

public class AddProfessorActivity extends Activity {

    private final static String KEY_ID = "id";

    private ProfessorDataBaseManager manager;
    private SubjectDataBaseManager subjectManager;

    private Cursor cursor;
    private Spinner spinner;
    private Bundle bundle;

    private String tag;
    private String inPosition;

    final int CODE_REQUEST = 1;

    private String name = null;
    private String email = null;
    private String office = null;
    private String number = null;

    private Integer idSubject = null;
    private Long idSubjectLong = null;

    private ScrollView scrollView;
    private Button addSchedule;
    private EditText eTName;
    private EditText eTEmail;
    private EditText eTOffice;
    private EditText eTNumber;

    //Boolean

    private Boolean mondayIsClickedBoolean = false;
    private Boolean tuesdayIsClickedBoolean = false;
    private Boolean wednesdayIsClickedBoolean = false;
    private Boolean thursdayIsClickedBoolean = false;
    private Boolean fridayIsClickedBoolean = false;
    private Boolean saturdayIsClickedBoolean = false;
    private Boolean sundayIsClickedBoolean = false;

    private String mondayIsClicked = null;
    private String tuesdayIsClicked = null;
    private String wednesdayIsClicked = null;
    private String thursdayIsClicked = null;
    private String fridayIsClicked = null;
    private String saturdayIsClicked = null;
    private String sundayIsClicked = null;

    private String eTStartTimeMonday = "0";
    private String eTEndTimeMonday = "0";
    private String eTStartTimeTuesday = "0";
    private String eTEndTimeTuesday = "0";
    private String eTStartTimeWednesday = "0";
    private String eTEndTimeWednesday = "0";
    private String eTStartTimeThursday = "0";
    private String eTEndTimeThursday = "0";
    private String eTStartTimeFriday = "0";
    private String eTEndTimeFriday = "0";
    private String eTStartTimeSaturday = "0";
    private String eTEndTimeSaturday = "0";
    private String eTStartTimeSunday = "0";
    private String eTEndTimeSunday = "0";

    private String timeMonday = "0";
    private String timeTuesday = "0";
    private String timeWednesday = "0";
    private String timeThursday = "0";
    private String timeFriday = "0";
    private String timeSaturday = "0";
    private String timeSunday = "0";

    private void initComponents()
    {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        addSchedule = (Button) findViewById(R.id.btScheduleProfessorInfo);
        addSchedule.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                launchScheduleProfessorInfo();
            }
        });

    }

    /**
     * Void to get the information and add a professor to data base
     */
    public void addProfessorToDataBase ()
    {

        eTName = (EditText) findViewById(R.id.eTProfessor);
        eTEmail = (EditText) findViewById(R.id.eTEmail);
        eTOffice = (EditText) findViewById(R.id.eTOffice);
        eTNumber = (EditText) findViewById(R.id.eTNumber);

        name = eTName.getText().toString();
        email = eTEmail.getText().toString();
        office = eTOffice.getText().toString();
        number = eTNumber.getText().toString();

        manager = new ProfessorDataBaseManager(this);

        manager.insert(name, email, number, office ,mondayIsClickedBoolean,tuesdayIsClickedBoolean,wednesdayIsClickedBoolean,thursdayIsClickedBoolean,
                fridayIsClickedBoolean,saturdayIsClickedBoolean,sundayIsClickedBoolean, timeMonday,timeTuesday,timeWednesday,timeThursday,
                timeFriday,timeSaturday,timeSunday, idSubject);
    }

    /*private void addSubjectsInSpinner ()
    {
        List<String> subjectTitles = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.sprSubjects);
        subjectManager = new SubjectDataBaseManager(this);
        subjectManager.open();

        cursor = subjectManager.getAllRows();

        if (cursor.moveToFirst()) {
            do {
                subjectTitles.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, subjectTitles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                inPosition = adapterView.getSelectedItem().toString();

                Cursor cursor = subjectManager.searchSubject(inPosition);
                if (cursor.moveToFirst()) {
                    int idDB = cursor.getInt(SubjectDataBaseManager.COL_ROWID);
                    String name = cursor.getString(SubjectDataBaseManager.COL_NAME);

                    String message = "ID: " + idDB + "\n"
                            + "Name: " + name + "\n";

                    idSubject = idDB;
                    Log.i(tag, "Subject to save with professor " + message);
                }
                else
                    Log.i(tag, "Subject NOT FOUND");
                cursor.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                inPosition = adapterView.getItemAtPosition(0).toString();

                //aqui
                Cursor cursor = subjectManager.searchSubject(inPosition);
                if (cursor.moveToFirst()) {
                    int idDB = cursor.getInt(SubjectDataBaseManager.COL_ROWID);
                    String name = cursor.getString(SubjectDataBaseManager.COL_NAME);

                    String message = "ID: " + idDB + "\n"
                            + "Name: " + name + "\n";

                    idSubject = idDB;
                    Log.i(tag, "Subject to save with professor " + message);
                }
                else
                    Log.i(tag, "Subject NOT FOUND");
                cursor.close();
            }
        });

    }*/

    public void launchScheduleProfessorInfo()
    {
        Intent intent = new Intent(this, ScheduleProfessorActivity.class);
        startActivityForResult(intent, CODE_REQUEST);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_professor);

        initComponents();
        //addSubjectsInSpinner();
        bundle = getIntent().getExtras();

        if (bundle != null)
        {
            idSubjectLong = bundle.getLong(KEY_ID);
            idSubject = idSubjectLong.intValue();
            Log.i(tag," A ADD PROFESSOR llego la ID "+ idSubject);
        }

        if( savedInstanceState != null)
        {
            idSubject = savedInstanceState.getInt("idSubject");
            name = savedInstanceState.getString("names");
            email = savedInstanceState.getString("email");
            office = savedInstanceState.getString("office");
            number = savedInstanceState.getString("number");

            eTName.setText(String.valueOf(name));
            eTEmail.setText(String.valueOf(email));
            eTOffice.setText(String.valueOf(office));
            eTNumber.setText(String.valueOf(number));
            spinner.setSelection(idSubject);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_professor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_settings:
                // do s.th.
                return true;

            case R.id.professor_add:
                addProfessorToDataBase();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            data.getBundleExtra("bundle");

            if (resultCode == Activity.RESULT_OK) {

                mondayIsClicked = data.getStringExtra("mondayIsClicked");
                tuesdayIsClicked = data.getStringExtra("tuesdayIsClicked");
                wednesdayIsClicked = data.getStringExtra("wednesdayIsClicked");
                thursdayIsClicked = data.getStringExtra("thursdayIsClicked");
                fridayIsClicked = data.getStringExtra("fridayIsClicked");
                saturdayIsClicked = data.getStringExtra("saturdayIsClicked");
                sundayIsClicked = data.getStringExtra("sundayIsClicked");


                if (mondayIsClicked.equals("true")) {
                    eTStartTimeMonday = data.getStringExtra("eTStartTimeMonday");
                    eTEndTimeMonday = data.getStringExtra("eTEndTimeMonday");

                    mondayIsClickedBoolean = true;

                    timeMonday = eTStartTimeMonday + " - " + eTEndTimeMonday;
                } else
                    timeMonday = "00";

                if (tuesdayIsClicked.equals("true")) {
                    eTStartTimeTuesday = data.getStringExtra("eTStartTimeTuesday");
                    eTEndTimeTuesday = data.getStringExtra("eTEndTimeTuesday");

                    tuesdayIsClickedBoolean = true;

                    timeTuesday = eTStartTimeTuesday + " - " + eTEndTimeTuesday;
                } else
                    timeTuesday = "00";

                if (wednesdayIsClicked.equals("true")) {
                    eTStartTimeWednesday = data.getStringExtra("eTStartTimeWednesday");
                    eTEndTimeWednesday = data.getStringExtra("eTEndTimeWednesday");

                    wednesdayIsClickedBoolean = true;

                    timeWednesday = eTStartTimeWednesday + " - " + eTEndTimeWednesday;
                } else
                    timeWednesday = "00";

                if (thursdayIsClicked.equals("true")) {
                    eTStartTimeThursday = data.getStringExtra("eTStartTimeThursday");
                    eTEndTimeThursday = data.getStringExtra("eTEndTimeThursday");

                    thursdayIsClickedBoolean = true;

                    timeThursday = eTStartTimeThursday + " - " + eTEndTimeThursday;
                } else
                    timeThursday = "00";

                if (fridayIsClicked.equals("true")) {
                    eTStartTimeFriday = data.getStringExtra("eTStartTimeFriday");
                    eTEndTimeFriday = data.getStringExtra("eTEndTimeFriday");

                    fridayIsClickedBoolean = true;

                    timeFriday = eTStartTimeFriday + " - " + eTEndTimeFriday;
                } else
                    timeFriday = "00";

                if (saturdayIsClicked.equals("true")) {
                    eTStartTimeSaturday = data.getStringExtra("eTStartTimeSaturday");
                    eTEndTimeSaturday = data.getStringExtra("eTEndTimeSaturday");

                    saturdayIsClickedBoolean = true;

                    timeSaturday = eTStartTimeSaturday + " - " + eTEndTimeSaturday;
                } else
                    timeSaturday = "00";

                if (sundayIsClicked.equals("true")) {
                    eTStartTimeSunday = data.getStringExtra("eTStartTimeSunday");
                    eTEndTimeSunday = data.getStringExtra("eTEndTimeSunday");

                    sundayIsClickedBoolean = true;

                    timeSunday = eTStartTimeSunday + " - " + eTEndTimeSunday;
                } else
                    timeSunday = "00";
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){

        eTName = (EditText) findViewById(R.id.eTProfessor);
        eTEmail = (EditText) findViewById(R.id.eTEmail);
        eTOffice = (EditText) findViewById(R.id.eTOffice);
        eTNumber = (EditText) findViewById(R.id.eTNumber);

        outState.putString("name", eTName.getText().toString());
        outState.putString("email", eTEmail.getText().toString());
        outState.putString("office", eTOffice.getText().toString());
        outState.putString("number", eTNumber.getText().toString());
        outState.putInt("idSubject", idSubject);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){

        super.onRestoreInstanceState(savedInstanceState);

        eTName = (EditText) findViewById(R.id.eTProfessor);
        eTEmail = (EditText) findViewById(R.id.eTEmail);
        eTOffice = (EditText) findViewById(R.id.eTOffice);
        eTNumber = (EditText) findViewById(R.id.eTNumber);

        idSubject = savedInstanceState.getInt("idSubject");
        name = savedInstanceState.getString("names");
        email = savedInstanceState.getString("email");
        office = savedInstanceState.getString("office");
        number = savedInstanceState.getString("number");

        eTName.setText(String.valueOf(name));
        eTEmail.setText(String.valueOf(email));
        eTOffice.setText(String.valueOf(office));
        eTNumber.setText(String.valueOf(number));
        spinner.setSelection(idSubject);

    }
}