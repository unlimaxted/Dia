package com.maxxindev.studia.Controller.Tests;



import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.maxxindev.studia.DataBaseController.TestDB.TestDataBaseManager;
import com.maxxindev.studia.Model.Test;
import com.maxxindev.studia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class TestFragment extends Fragment {

    private final static String KEY_ID = "id";

    private ListView testList;

    TestDataBaseManager testManager;

    private Long idTest = null;
    private Long idSubject = null;

    private String tag = null;


    public static TestFragment newInstance(Long id) {
        String tag = null;
        TestFragment frag = new TestFragment();

        Bundle args = frag.getArguments();
        if(args == null) {
            args = new Bundle();
        }

        args.putLong(KEY_ID, id);

        frag.setArguments(args);

        return frag;
    }

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);
        initComponents(view);

        idSubject = getArguments().getLong(KEY_ID);
        Log.i(tag,"ID que llego a Test " + idSubject);

        new PopulateListTask().execute();
        setHasOptionsMenu(true);

        return view;
    }

    private void initComponents(View v)
    {
        testList = (ListView) v.findViewById(R.id.lvTest);
    }


    private void populateListView (Long idSubject)
    {

        openDBTest();
        Cursor testCursor = testManager.getRowBySubject(idSubject);

        final List<Test> allTest = new ArrayList<Test>();
        Test test = null;

        if (testCursor != null)
        {
            testCursor.moveToFirst();

            while (testCursor.isAfterLast() == false)
            {
                idTest = testCursor.getLong(TestDataBaseManager.COL_ROWID);
                test = getTestbyId(idTest);

                allTest.add(test);
                testCursor.moveToNext();
                Log.i(tag,"TEST "+ test.getDate());
            }

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    testList.setAdapter(new TestAdapter(getActivity(), allTest));
                }
            });

        }
        closeDBTest();
        testCursor.close();

    }

    private Test getTestbyId(Long id) {

        Cursor testCursor = null;
        Test test = null;

        testManager = new TestDataBaseManager(getActivity());
        testManager.open();

        testCursor = testManager.getRow(id);

        if (testCursor != null && testCursor.moveToFirst()) {

            test = new Test(testCursor.getString(1),
                    testCursor.getString(2), testCursor.getString(3), testCursor.getString(4), testCursor.getString(5),
                    testCursor.getString(6), testCursor.getInt(7), testCursor.getInt(8));
        }
        testManager.close();
        testCursor.close();
        return test;
    }


    private void openDBTest() {
        testManager = new TestDataBaseManager(getActivity());
        testManager.open();
    }

    private void closeDBTest() {
        testManager.close();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_test, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.test_add:
                Bundle bundle = new Bundle();
                bundle.putLong("id", idSubject);

                Intent intent = new Intent(getActivity(), AddTestActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class PopulateListTask extends AsyncTask<Void, Void, Void>
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
            populateListView(idSubject);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }

    }

}