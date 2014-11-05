package com.maxxindev.studia.Controller.Subjects;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.maxxindev.studia.DataBaseController.ProfessorDB.ProfessorDataBaseManager;
import com.maxxindev.studia.DataBaseController.SubjectsDB.SubjectDataBaseManager;
import com.maxxindev.studia.DataBaseController.TestDB.TestDataBaseManager;
import com.maxxindev.studia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject Fragment
 */
public class SubjectsFragment extends Fragment implements AdapterView.OnItemClickListener{

    private OnFragmentInteractionListener mListener;
    SubjectDataBaseManager subjectManager;
    ProfessorDataBaseManager professorManager;
    TestDataBaseManager testManager;
    Cursor cursor;

    private String subjectSelected;
    private String tag;
    private Integer initLong = 0;


    public SubjectsFragment() {
        // Required empty public constructor
    }

    /**
     * Void to send the id of Subject to SubjectInfo Activity
     * @param idDB
     */
    private void showSubjectInfoFragment (Long idDB)
    {

        Bundle bundle = new Bundle();

        bundle.putLong("id", idDB);

        Intent intent = new Intent(getActivity(), SubjectInfoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_subjects, container, false);


        openDB();
        populateListViewFromDB(v);
        registerListClickCallback(v);

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position,
                            long ID) {

    }

    /**
     * Void to select the subject and send IDSubject to open the Subject Activity info
     * @param view
     */
    private void registerListClickCallback(View view) {
        View v = view;
        List<String> subjectTitles = new ArrayList<String>();
        ListView myList = (ListView) v.findViewById(R.id.lvSubjectsF);

        openDB();

        cursor = null;

        cursor = subjectManager.getAllRows();

        if (cursor.moveToFirst()) {
            do {
                subjectTitles.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {

                TextView text = (TextView) viewClicked.findViewById(R.id.tvSubject);
                subjectSelected = text.getText().toString();

                Log.i(tag, "Subject selected" + subjectSelected);

                Cursor cursorSubject = subjectManager.searchSubject(subjectSelected);

                //Find subject selected
                if (cursorSubject.moveToFirst()) {
                    long idDBSubject = cursorSubject.getLong(SubjectDataBaseManager.COL_ROWID);
                    Log.i(tag, "ID Subject " + idDBSubject);

                    //Put idProfessor and idTest in bundle to send to SubjectInfoFragment
                    Bundle bundle = new Bundle();
                    bundle.putLong("id", idDBSubject);

                    //Call the void to send the info to SubjectInfoFragment
                    showSubjectInfoFragment(idDBSubject);
                }
                else
                    Log.i(tag, "Subject NOT FOUND");
                cursorSubject.close();

            }
        });
    }


    /**
     * Void to populate ListView of Subjects
     * @param view
     */
    private void populateListViewFromDB(View view) {
        View v = view;
        Cursor cursor = subjectManager.getAllRows();

        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED! Runs on the UI thread, OK for small/short queries.
        getActivity().startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:
        String[] fromFieldNames = new String[]
                {SubjectDataBaseManager.CN_NAME, SubjectDataBaseManager.CN_BIBLIOGRAPHY, SubjectDataBaseManager.CN_COLORMARK, SubjectDataBaseManager.CN_CREDIT};
        int[] toViewIDs = new int[]
                {R.id.tvSubject, R.id.tvProfessor, R.id.ivColorRectangle, R.id.tvUcNumber};

        // Create adapter to may columns of the DB onto elemesnt in the UI.


        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        getActivity(),		// Context
                        R.layout.list_subject,	// Row layout template
                        cursor,					// cursor (set of DB records to map)
                        fromFieldNames,			// DB Column names
                        toViewIDs				// View IDs to put information in
                );

        // Set the adapter for the list view
        ListView myList = (ListView) v.findViewById(R.id.lvSubjectsF);
        myList.setAdapter(myCursorAdapter);
    }


    private void updateItemForId(long idInDB, View v) {
        Cursor cursor = subjectManager.getRow(idInDB);
        if (cursor.moveToFirst()) {
            long idDB = cursor.getLong(SubjectDataBaseManager.COL_ROWID);
            String name = cursor.getString(SubjectDataBaseManager.COL_NAME);
            String bibliography = cursor.getString(SubjectDataBaseManager.COL_BIBLIOGRAPHY);
            String credits = cursor.getString(SubjectDataBaseManager.COL_CREDIT);
            Integer color = cursor.getInt(SubjectDataBaseManager.COL_COLORMARK);


            subjectManager.updateRow(idInDB, name, bibliography, color, credits);
        }
        cursor.close();
        populateListViewFromDB(v);
    }

    private void displayToastForId(long idInDB) {
        Cursor cursor = subjectManager.getRow(idInDB);
        if (cursor.moveToFirst()) {
            long idDB = cursor.getLong(SubjectDataBaseManager.COL_ROWID);
            String name = cursor.getString(SubjectDataBaseManager.COL_NAME);
            String bibliography = cursor.getString(SubjectDataBaseManager.COL_BIBLIOGRAPHY);
            String credits = cursor.getString(SubjectDataBaseManager.COL_CREDIT);

            String message = "ID: " + idDB + "\n"
                    + "Name: " + name + "\n"
                    + "Bibliography: " + bibliography + "\n"
                    + "credits: " + credits;
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }


    /**
     * Void to open Subject database
     */
    private void openDB() {
        subjectManager = new SubjectDataBaseManager(getActivity());
        subjectManager.open();
    }

    /**
     * Subject to close Subject Database
     */
    private void closeDB() {
        subjectManager.close();
    }

    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, android.view.MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_subject, menu);
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        Intent intent;
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                // do s.th.
                return true;
            case R.id.menu_add:
                intent = new Intent(getActivity(), AddSubjectsActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}