package com.maxxindev.studia.Controller.Subjects;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maxxindev.studia.Controller.NavigationDrawer.Item_objct;
import com.maxxindev.studia.DataBaseController.SubjectsDB.SubjectDataBaseManager;
import com.maxxindev.studia.Model.Color;
import com.maxxindev.studia.R;


import java.util.ArrayList;
import java.util.List;


public class AddSubjectsActivity extends Activity {

    private SubjectDataBaseManager manager;
    private Spinner spinner;
    private TypedArray colorIcons;
    private ArrayList<Item_objct> NavItms;
    private String[] titles;

    private String subject;
    private String credits;
    private String bibliography;
    private Integer colorMark;

    int [] imagesIDs = {
            R.drawable.limegreen_icon,
            R.drawable.black_icon,
            R.drawable.blue_icon,
            R.drawable.brown_icon,
            R.drawable.fuchsia_icon,
            R.drawable.gray_icon,
            R.drawable.green_icon,
            R.drawable.lightblue_icon,
            R.drawable.lightred_icon,
            R.drawable.lightyellow_icon,
            R.drawable.orange_icon,
            R.drawable.red_icon,
            R.drawable.brightturquoise_icon,
            R.drawable.violet_icon,
            R.drawable.yellow_icon,

    };
    int nextImageIndex = 0;

    /**
     * Void to get the information and add a subject to data base
     */
    public void addSubjectToDataBase ()
    {

        EditText eTsubject = (EditText) findViewById(R.id.eTSubjects);
        EditText eTcredits = (EditText) findViewById(R.id.eTCreditUnits);
        EditText eTbibliography = (EditText) findViewById(R.id.eTBibliography);

        subject = eTsubject.getText().toString();
        credits = eTcredits.getText().toString();
        bibliography = eTbibliography.getText().toString();

        int imageId = imagesIDs[colorMark];
        nextImageIndex = (colorMark) % imagesIDs.length;

        Log.i("Subject " + subject, "!!");
        Log.i("credits "+credits,"!!");
        Log.i("bibliography "+bibliography,"!!");

        manager = new SubjectDataBaseManager(this);

        manager.insert(subject, bibliography, imageId, credits);
    }

    private void addColorInSpinner ()
    {
        List<Color> items = new ArrayList<Color>(15);
        colorIcons = getResources().obtainTypedArray(R.array.color_icons);
        titles = getResources().getStringArray(R.array.color_array);


        items.add(new Color(titles[0], colorIcons.getResourceId(0, -1)));
        items.add(new Color(titles[1], colorIcons.getResourceId(1, -1)));
        items.add(new Color(titles[2], colorIcons.getResourceId(2, -1)));
        items.add(new Color(titles[3], colorIcons.getResourceId(3, -1)));
        items.add(new Color(titles[4], colorIcons.getResourceId(4, -1)));
        items.add(new Color(titles[5], colorIcons.getResourceId(5, -1)));
        items.add(new Color(titles[6], colorIcons.getResourceId(6, -1)));
        items.add(new Color(titles[7], colorIcons.getResourceId(7, -1)));
        items.add(new Color(titles[8], colorIcons.getResourceId(8, -1)));
        items.add(new Color(titles[9], colorIcons.getResourceId(9, -1)));
        items.add(new Color(titles[10], colorIcons.getResourceId(10, -1)));
        items.add(new Color(titles[11], colorIcons.getResourceId(11, -1)));
        items.add(new Color(titles[12], colorIcons.getResourceId(12, -1)));
        items.add(new Color(titles[13], colorIcons.getResourceId(13, -1)));
        items.add(new Color(titles[14], colorIcons.getResourceId(14, -1)));

        spinner = (Spinner) findViewById(R.id.sprColors);
        spinner.setAdapter(new SpinnerColorsAdapter(this,items));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                int pos = adapterView.getSelectedItemPosition();
                switch (pos) {

                    case 0:
                        colorMark = 0;
                        break;
                    case 1:
                        colorMark = 1;
                        break;
                    case 2:
                        colorMark = 2;
                        break;
                    case 3:
                        colorMark = 3;
                        break;
                    case 4:
                        colorMark = 4;
                        break;
                    case 5:
                        colorMark = 5;
                        break;
                    case 6:
                        colorMark = 6;
                        break;
                    case 7:
                        colorMark = 7;
                        break;
                    case 8:
                        colorMark = 8;
                        break;
                    case 9:
                        colorMark = 9;
                        break;
                    case 10:
                        colorMark = 10;
                        break;
                    case 11:
                        colorMark = 11;
                        break;
                    case 12:
                        colorMark = 12;
                        break;
                    case 13:
                        colorMark = 13;
                        break;
                    case 14:
                        colorMark = 14;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                colorMark = 0;
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        addColorInSpinner();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_subject, menu);

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
            case R.id.menu_add:
                addSubjectToDataBase();
                finish();
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }



}