package com.maxxindev.studia.Controller.Professor;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.maxxindev.studia.R;

import java.util.Calendar;

public class ScheduleProfessorActivity extends Activity {

    String tag = null;

    String startTimeMonday = "0";
    String endTimeMonday = "0";
    String startTimeTuesday = "0";
    String endTimeTuesday = "0";
    String startTimeWednesday = "0";
    String endTimeWednesday = "0";
    String startTimeThursday = "0";
    String endTimeThursday = "0";
    String startTimeFriday = "0";
    String endTimeFriday = "0";
    String startTimeSaturday = "0";
    String endTimeSaturday = "0";
    String startTimeSunday = "0";
    String endTimeSunday = "0";

    String timePicked = "0";

    //Boolean

    String mondayIsClicked = "false";
    String tuesdayIsClicked = "false";
    String wednesdayIsClicked = "false";
    String thursdayIsClicked = "false";
    String fridayIsClicked = "false";
    String saturdayIsClicked = "false";
    String sundayIsClicked = "false";

    //Components

    private Button btMonday;
    private Button btTuesday;
    private Button btWednesday;
    private Button btThursday;
    private Button btFriday;
    private Button btSaturday;
    private Button btSunday;

    private LinearLayout lLMondayHour;
    private LinearLayout lLTuesdayHour;
    private LinearLayout lLWednesdayHour;
    private LinearLayout lLThursdayHour;
    private LinearLayout lLFridayHour;
    private LinearLayout lLSaturdayHour;
    private LinearLayout lLSundayHour;

    private EditText eTStartTimeMonday;
    private EditText eTEndTimeMonday;
    private EditText eTStartTimeTuesday;
    private EditText eTEndTimeTuesday;
    private EditText eTStartTimeWednesday;
    private EditText eTEndTimeWednesday;
    private EditText eTStartTimeThursday;
    private EditText eTEndTimeThursday;
    private EditText eTStartTimeFriday;
    private EditText eTEndTimeFriday;
    private EditText eTStartTimeSaturday;
    private EditText eTEndTimeSaturday;
    private EditText eTStartTimeSunday;
    private EditText eTEndTimeSunday;

    private void initComponents()
    {
        //Days button
        btMonday = (Button) findViewById(R.id.btMonday);
        lLMondayHour = (LinearLayout) findViewById(R.id.lLMondayHour);
        btMonday.setOnClickListener(onClickMonday());

        btTuesday = (Button) findViewById(R.id.btTuesday);
        lLTuesdayHour = (LinearLayout) findViewById(R.id.lLTuesdayHour);
        btTuesday.setOnClickListener(onClickTuesday());

        btWednesday = (Button) findViewById(R.id.btWednesday);
        lLWednesdayHour = (LinearLayout) findViewById(R.id.lLWednesdayHour);
        btWednesday.setOnClickListener(onClickWednesday());

        btThursday = (Button) findViewById(R.id.btThursday);
        lLThursdayHour = (LinearLayout) findViewById(R.id.lLThursdayHour);
        btThursday.setOnClickListener(onClickThursday());

        btFriday = (Button) findViewById(R.id.btFriday);
        lLFridayHour = (LinearLayout) findViewById(R.id.lLFridayHour);
        btFriday.setOnClickListener(onClickFriday());

        btSaturday = (Button) findViewById(R.id.btSaturday);
        lLSaturdayHour = (LinearLayout) findViewById(R.id.lLSaturdayHour);
        btSaturday.setOnClickListener(onClickSaturday());

        btSunday = (Button) findViewById(R.id.btSunday);
        lLSundayHour = (LinearLayout) findViewById(R.id.lLSundayHour);
        btSunday.setOnClickListener(onClickTSunday());

        //Edit text

        eTStartTimeMonday = (EditText) findViewById(R.id.eTStarTimeMonday);
        eTEndTimeMonday = (EditText) findViewById(R.id.eTEndTimeMonday);

        eTStartTimeTuesday = (EditText) findViewById(R.id.eTStartTimeTuesday);
        eTEndTimeTuesday = (EditText) findViewById(R.id.eTEndTimeTuesday);

        eTStartTimeWednesday = (EditText) findViewById(R.id.eTStartTimeWednesday);
        eTEndTimeWednesday = (EditText) findViewById(R.id.eTEndTimeWednesday);

        eTStartTimeThursday = (EditText) findViewById(R.id.eTStartTimeThursday);
        eTEndTimeThursday = (EditText) findViewById(R.id.eTEndTimeThursday);

        eTStartTimeFriday = (EditText) findViewById(R.id.eTStartTimeFriday);
        eTEndTimeFriday = (EditText) findViewById(R.id.eTEndTimeFriday);

        eTStartTimeSaturday = (EditText) findViewById(R.id.eTStarTimeSaturday);
        eTEndTimeSaturday = (EditText) findViewById(R.id.eTEndTimeSaturday);

        eTStartTimeSunday = (EditText) findViewById(R.id.eTStartTimeSunday);
        eTEndTimeSunday = (EditText) findViewById(R.id.eTEndTimeSunday);

    }

    private void sendParameters ()
    {
        startTimeMonday = eTStartTimeMonday.getText().toString();
        endTimeMonday = eTEndTimeMonday.getText().toString();
        startTimeTuesday = eTStartTimeTuesday.getText().toString();
        endTimeTuesday = eTEndTimeTuesday.getText().toString();
        startTimeWednesday = eTStartTimeWednesday.getText().toString();
        endTimeWednesday = eTEndTimeWednesday.getText().toString();
        startTimeThursday = eTStartTimeThursday.getText().toString();
        endTimeThursday = eTEndTimeThursday.getText().toString();
        startTimeFriday = eTStartTimeFriday.getText().toString();
        endTimeFriday = eTEndTimeFriday.getText().toString();
        startTimeSaturday = eTStartTimeSaturday.getText().toString();
        endTimeSaturday = eTEndTimeSaturday.getText().toString();
        startTimeSunday = eTStartTimeSunday.getText().toString();
        endTimeSunday = eTEndTimeSunday.getText().toString();


        Intent i = new Intent(this, AddProfessorActivity.class);

        //Boolean
        i.putExtra("mondayIsClicked", mondayIsClicked);
        i.putExtra("tuesdayIsClicked", tuesdayIsClicked);
        i.putExtra("wednesdayIsClicked", wednesdayIsClicked);
        i.putExtra("thursdayIsClicked", thursdayIsClicked);
        i.putExtra("fridayIsClicked", fridayIsClicked);
        i.putExtra("saturdayIsClicked", saturdayIsClicked);
        i.putExtra("sundayIsClicked", sundayIsClicked);

        //Edit text

        if (mondayIsClicked == "true") {
            i.putExtra("eTStartTimeMonday", startTimeMonday);
            i.putExtra("eTEndTimeMonday", endTimeMonday);
        }

        if (tuesdayIsClicked == "true") {
            i.putExtra("eTStartTimeTuesday", startTimeTuesday);
            i.putExtra("eTEndTimeTuesday", endTimeTuesday);
        }

        if (wednesdayIsClicked == "true") {
            i.putExtra("eTStartTimeWednesday", startTimeWednesday);
            i.putExtra("eTEndTimeWednesday", endTimeWednesday);
        }

        if (thursdayIsClicked == "true") {
            i.putExtra("eTStartTimeThursday", startTimeThursday);
            i.putExtra("eTEndTimeThursday", endTimeThursday);
        }

        if (fridayIsClicked == "true") {
            i.putExtra("eTStartTimeFriday", startTimeFriday);
            i.putExtra("eTEndTimeFriday", endTimeFriday);
        }

        if (saturdayIsClicked == "true") {
            i.putExtra("eTStartTimeSaturday", startTimeSaturday);
            i.putExtra("eTEndTimeSaturday", endTimeSaturday);
        }

        if (sundayIsClicked == "true") {
            i.putExtra("eTStartTimeSunday", startTimeSunday);
            i.putExtra("eTEndTimeSunday", endTimeSunday);
        }

        setResult(Activity.RESULT_OK, i);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_professor);

        initComponents();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule_professor, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_settings:
                // do s.th.
                return true;

            case R.id.scheduleProfessor_add:
                sendParameters();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    private View.OnClickListener onStartMondayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTStartTimeMonday.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onEndMondayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTEndTimeMonday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onStartTuesdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTStartTimeTuesday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onEndTuesdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTEndTimeTuesday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onStartWednesdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTStartTimeWednesday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onEndWednesdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTEndTimeWednesday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onStartThursdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTStartTimeThursday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onEndThursdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTEndTimeThursday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onStartFridayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTStartTimeFriday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onEndFridayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTEndTimeFriday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onStartSaturdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTStartTimeSaturday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onEndSaturdayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTEndTimeSaturday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onStartSundayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTStartTimeSunday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }

    private View.OnClickListener onEndSundayClickTimePicker() {
        return new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timePicked = updateTime(selectedHour, selectedMinute);
                        eTEndTimeSunday.setText(timePicked);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        };
    }*/

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


    private View.OnClickListener onClickMonday() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mondayIsClicked == "false") {
                    lLMondayHour.setVisibility(View.VISIBLE);
                    mondayIsClicked = "true";
                    eTStartTimeMonday.setClickable(true);
                    eTStartTimeMonday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTStartTimeMonday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                    eTEndTimeMonday.setClickable(true);
                    eTEndTimeMonday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTEndTimeMonday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                }
                else {
                    lLMondayHour.setVisibility(View.GONE);
                    mondayIsClicked = "false";
                }
            }
        };
    }

    private View.OnClickListener onClickTuesday() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (tuesdayIsClicked == "false") {
                    lLTuesdayHour.setVisibility(View.VISIBLE);
                    tuesdayIsClicked = "true";
                    eTStartTimeTuesday.setClickable(true);
                    eTStartTimeTuesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTStartTimeTuesday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                    eTEndTimeTuesday.setClickable(true);
                    eTEndTimeTuesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTEndTimeTuesday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                }
                else {
                    lLTuesdayHour.setVisibility(View.GONE);
                    tuesdayIsClicked = "false";
                }
            }
        };
    }

    private View.OnClickListener onClickWednesday() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (wednesdayIsClicked == "false") {
                    lLWednesdayHour.setVisibility(View.VISIBLE);
                    wednesdayIsClicked = "true";
                    eTStartTimeWednesday.setClickable(true);
                    eTStartTimeWednesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTStartTimeWednesday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                    eTEndTimeWednesday.setClickable(true);
                    eTEndTimeWednesday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTEndTimeWednesday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                }
                else {
                    lLWednesdayHour.setVisibility(View.GONE);
                    wednesdayIsClicked = "false";
                }
            }
        };
    }

    private View.OnClickListener onClickThursday() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (thursdayIsClicked == "false") {
                    lLThursdayHour.setVisibility(View.VISIBLE);
                    thursdayIsClicked = "true";
                    eTStartTimeThursday.setClickable(true);
                    eTStartTimeThursday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTStartTimeThursday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                    eTEndTimeThursday.setClickable(true);
                    eTEndTimeThursday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTEndTimeThursday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                }
                else {
                    lLThursdayHour.setVisibility(View.GONE);
                    thursdayIsClicked = "false";
                }
            }
        };
    }

    private View.OnClickListener onClickFriday() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (fridayIsClicked == "false") {
                    lLFridayHour.setVisibility(View.VISIBLE);
                    fridayIsClicked = "true";
                    eTStartTimeFriday.setClickable(true);
                    eTStartTimeFriday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTStartTimeFriday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                    eTEndTimeFriday.setClickable(true);
                    eTEndTimeFriday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTEndTimeFriday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                }
                else {
                    lLFridayHour.setVisibility(View.GONE);
                    fridayIsClicked = "false";
                }
            }
        };
    }

    private View.OnClickListener onClickSaturday() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (saturdayIsClicked == "false") {
                    lLSaturdayHour.setVisibility(View.VISIBLE);
                    saturdayIsClicked = "true";
                    eTStartTimeSaturday.setClickable(true);
                    eTStartTimeSaturday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTStartTimeSaturday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                    eTEndTimeSaturday.setClickable(true);
                    eTEndTimeSaturday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTEndTimeSaturday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                }
                else {
                    lLSaturdayHour.setVisibility(View.GONE);
                    saturdayIsClicked = "false";
                }
            }
        };
    }

    private View.OnClickListener onClickTSunday() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (sundayIsClicked == "false") {
                    lLSundayHour.setVisibility(View.VISIBLE);
                    sundayIsClicked = "true";
                    eTStartTimeSunday.setClickable(true);
                    eTStartTimeSunday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTStartTimeSunday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                    eTEndTimeSunday.setClickable(true);
                    eTEndTimeSunday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(hasFocus)
                            {
                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);
                                TimePickerDialog mTimePicker;
                                mTimePicker = new TimePickerDialog(ScheduleProfessorActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                        timePicked = updateTime(selectedHour, selectedMinute);
                                        eTEndTimeSunday.setText(timePicked);
                                    }
                                }, hour, minute, false);
                                mTimePicker.setTitle("Select Time");
                                mTimePicker.show();
                            }
                        }
                    });
                }
                else {
                    lLSundayHour.setVisibility(View.GONE);
                    sundayIsClicked = "false";
                }
            }
        };
    }

}