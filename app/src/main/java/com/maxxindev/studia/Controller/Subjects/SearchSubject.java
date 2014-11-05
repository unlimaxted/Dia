package com.maxxindev.studia.Controller.Subjects;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.maxxindev.studia.DataBaseController.SubjectsDB.SubjectDataBaseManager;
import com.maxxindev.studia.R;

public class SearchSubject extends Activity implements View.OnClickListener
{

    //Data Base components
    private SubjectDataBaseManager manager;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;


    //Components
    private ListView list;
    private TextView tv;
    private ImageButton bt;


    private void initComponents()
    {
        list = (ListView) findViewById(R.id.listView);
        tv = (TextView) findViewById(R.id.edTxtSearch);
        bt = (ImageButton) findViewById(R.id.imageButton);
        bt.setOnClickListener(this);

        manager = new SubjectDataBaseManager(this);

    }

    public void showSubjects(SubjectDataBaseManager manager, Cursor cursor, SimpleCursorAdapter adapter, ListView list, TextView tv, ImageButton bt)
    {

        String[] from = new String[]{manager.CN_NAME, manager.CN_CREDIT};
        int[] to = new int[] {android.R.id.text1, android.R.id.text2};

        cursor = manager.loadSubjectsCursor();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to,0);
        list.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_subject);

        initComponents();

        // showSubjects(manager, cursor, adapter, list_subject, tv, bt); no funciona aun..

        //manager.insert("Ejmplo ahorite","ejemplo","1");
        //manager.insert("Relleno","Mierda pura","2");

        String[] from = new String[]{manager.CN_NAME,manager.CN_BIBLIOGRAPHY};
        int[] to = new int[] {android.R.id.text1, android.R.id.text2};

        cursor = manager.loadSubjectsCursor();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to,0);
        list.setAdapter(adapter);
    }

    @Override
    public void onClick (View view)
    {
        if (view.getId() == R.id.imageButton)
        {
            new FindTask().execute();

        }
    }
    private class FindTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            //Toast.makeText(getApplicationContext(), "Searching...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            cursor = manager.searchSubject(tv.getText().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.changeCursor(cursor);
            Toast.makeText(getApplicationContext(),"Finishing...", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}