package com.maxxindev.studia.Controller.Professor;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maxxindev.studia.DataBaseController.ProfessorDB.ProfessorDataBaseManager;
import com.maxxindev.studia.DataBaseController.SubjectsDB.SubjectDataBaseManager;
import com.maxxindev.studia.Model.Professor;
import com.maxxindev.studia.R;

/**
 * Fragment of Proffesor
 */
public class ProfessorFragment extends Fragment {

    ProfessorDataBaseManager professorManager;
    private Cursor cursor = null;
    private String tag;

    //Components

    private TextView tvProfessorName;
    private TextView tvProfessorNamed;
    private TextView tvProfessorEmail;
    private TextView tvProfessorEmailed;
    private TextView tvProfessorOffice;
    private TextView tvProfessorOfficed;
    private TextView tvProfessorNumber;
    private TextView tvProfessorNumberd;

    private TextView tvProfessorSchedule;
    private TextView tvMonday;
    private TextView tvMondayHour;
    private TextView tvTuesday;
    private TextView tvTuesdayHour;
    private TextView tvWednesday;
    private TextView tvWednesdayHour;
    private TextView tvThursday;
    private TextView tvThursdayHour;
    private TextView tvFriday;
    private TextView tvFridayHour;
    private TextView tvSaturday;
    private TextView tvSaturdayHour;
    private TextView tvSunday;
    private TextView tvSundayHour;

    private LinearLayout lLNoProfessor;
    private LinearLayout lLInfo;
    private LinearLayout lLSchedule;
    private LinearLayout lLMonday;
    private LinearLayout lLTuesday;
    private LinearLayout lLWednesday;
    private LinearLayout lLThursday;
    private LinearLayout lLFriday;
    private LinearLayout lLSaturday;
    private LinearLayout lLSunday;


    private Long idProfessor;
    private Long idSubject = null;

    private String timeMonday = null;
    private String timeTuesday = null;
    private String timeWednesday = null;
    private String timeThursday = null;
    private String timeFriday = null;
    private String timeSaturday = null;
    private String timeSunday = null;

    //Booleans
    private Boolean mondayIsClicked = false;
    private Boolean tuesdayIsClicked = false;
    private Boolean wednesdayIsClicked = false;
    private Boolean thursdayIsClicked = false;
    private Boolean fridayIsClicked = false;
    private Boolean saturdayIsClicked = false;
    private Boolean sundayIsClicked = false;

    public ProfessorFragment() {
        // Required empty public constructor
    }

    private final static String KEY_ID = "id";

    public static ProfessorFragment newInstance(Long id) {

        ProfessorFragment frag = new ProfessorFragment();

        Bundle args = frag.getArguments();
        if(args == null)
            args = new Bundle();

        args.putLong(KEY_ID, id);
        frag.setArguments(args);

        return frag;
    }

    private void initComponets(View v)
    {
        tvProfessorName =  (TextView) v.findViewById(R.id.tvProfessorName);
        tvProfessorNamed =  (TextView) v.findViewById(R.id.tvProfessorNamed);
        tvProfessorEmail =  (TextView) v.findViewById(R.id.tvProfessorEmail);
        tvProfessorEmailed =  (TextView) v.findViewById(R.id.tvProfessorEmailed);
        tvProfessorOffice =  (TextView) v.findViewById(R.id.tvProfessorOffice);
        tvProfessorOfficed =  (TextView) v.findViewById(R.id.tvProfessorOfficed);
        tvProfessorNumber =  (TextView) v.findViewById(R.id.tvProfessorNumber);
        tvProfessorNumberd =  (TextView) v.findViewById(R.id.tvProfessorNumbered);

        tvProfessorSchedule =  (TextView) v.findViewById(R.id.tvProfessorSchedule);
        tvMonday =  (TextView) v.findViewById(R.id.tvMonday);
        tvMondayHour =  (TextView) v.findViewById(R.id.tvMondayHour);
        tvTuesday =  (TextView) v.findViewById(R.id.tvTuesday);
        tvTuesdayHour =  (TextView) v.findViewById(R.id.tvTuesdayHour);
        tvWednesday =  (TextView) v.findViewById(R.id.tvWednesday);
        tvWednesdayHour =  (TextView) v.findViewById(R.id.tvWednesdayHour);
        tvThursday =  (TextView) v.findViewById(R.id.tvThursday);
        tvThursdayHour =  (TextView) v.findViewById(R.id.tvThursdayHour);
        tvFriday =  (TextView) v.findViewById(R.id.tvFriday);
        tvFridayHour =  (TextView) v.findViewById(R.id.tvFridayHour);
        tvSaturday =  (TextView) v.findViewById(R.id.tvSaturday);
        tvSaturdayHour =  (TextView) v.findViewById(R.id.tvSaturdayHour);
        tvSunday =  (TextView) v.findViewById(R.id.tvSunday);
        tvSundayHour =  (TextView) v.findViewById(R.id.tvSundayHour);

        lLNoProfessor =  (LinearLayout) v.findViewById(R.id.lLNoProfessor);
        lLInfo = (LinearLayout) v.findViewById(R.id.lLInfo);
        lLSchedule =  (LinearLayout) v.findViewById(R.id.lLSchedule);

        lLMonday =  (LinearLayout) v.findViewById(R.id.lLMonday);
        lLTuesday =  (LinearLayout) v.findViewById(R.id.lLTuesday);
        lLWednesday =  (LinearLayout) v.findViewById(R.id.lLWednesday);
        lLThursday =  (LinearLayout) v.findViewById(R.id.lLThursday);
        lLFriday =  (LinearLayout) v.findViewById(R.id.lLFriday);
        lLSaturday =  (LinearLayout) v.findViewById(R.id.lLSaturday);
        lLSunday =  (LinearLayout) v.findViewById(R.id.lLSunday);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_professor, container, false);

        initComponets(view);

        idSubject = getArguments().getLong(KEY_ID);

        Log.i(tag,"ID que llego a Professor " + idSubject);

        if (professorExist() == true) {
            populateView(view, idSubject);
        }
        else {
            lLNoProfessor.setVisibility(View.VISIBLE);
            lLInfo.setVisibility(View.GONE);
        }


        setHasOptionsMenu(true);
        return view;
    }

    private void openDBProfessor() {
        professorManager = new ProfessorDataBaseManager(getActivity());
        professorManager.open();
    }

    private void closeDBProfessor() {
        professorManager.close();
    }

    private Boolean professorExist()
    {
        openDBProfessor();
        //Find the professor of the selected subject
        Cursor professorCursor =  null;
        professorCursor = professorManager.getRowBySubject(idSubject);

        if (professorCursor != null && professorCursor.moveToFirst())
        {
            closeDBProfessor();
            professorCursor.close();
            return true;
        }

        professorCursor.close();
        closeDBProfessor();
        return false;
    }

    private void populateView (View view, Long idSubject)
    {
        openDBProfessor();
        //Find the professor of the selected subject
        Cursor professorCursor = professorManager.getRowBySubject(idSubject);

        //If professor exist
        if (professorCursor.moveToFirst()) {
            idProfessor = professorCursor.getLong(SubjectDataBaseManager.COL_ROWID);

            Log.i(tag, "ID professor " + idProfessor);

            Professor professor = null;

            professor = getProfessorbyId(idProfessor);

            if (professor != null) {

                lLSchedule.setVisibility(View.VISIBLE);

                tvProfessorNamed.setText(professor.getName());
                tvProfessorEmailed.setText(professor.getEmail());
                tvProfessorOfficed.setText(professor.getOffice());
                tvProfessorNumberd.setText(professor.getNumber());

                mondayIsClicked = professor.getMonday();
                tuesdayIsClicked = professor.getTuesday();
                wednesdayIsClicked = professor.getWednesday();
                thursdayIsClicked = professor.getThursday();
                fridayIsClicked = professor.getFriday();
                saturdayIsClicked = professor.getSaturday();
                sundayIsClicked = professor.getSunday();

                if (mondayIsClicked == true) {
                    lLMonday.setVisibility(View.VISIBLE);
                    tvMondayHour.setText(professor.getMondayHours());
                }
                if (tuesdayIsClicked == true) {
                    lLTuesday.setVisibility(View.VISIBLE);
                    tvTuesdayHour.setText(professor.getTuesdayHours());
                }
                if (wednesdayIsClicked == true) {
                    lLWednesday.setVisibility(View.VISIBLE);
                    tvWednesdayHour.setText(professor.getWednesdayHours());
                }
                if (thursdayIsClicked == true) {
                    lLThursday.setVisibility(View.VISIBLE);
                    tvThursdayHour.setText(professor.getThursdayHours());
                }
                if (fridayIsClicked == true) {
                    lLFriday.setVisibility(View.VISIBLE);
                    tvFridayHour.setText(professor.getFridayHours());
                }
                if (saturdayIsClicked == true) {
                    lLSaturday.setVisibility(View.VISIBLE);
                    tvSaturdayHour.setText(professor.getSaturdayHours());
                }
                if (sundayIsClicked == true) {
                    lLSunday.setVisibility(View.VISIBLE);
                    tvSundayHour.setText(professor.getSundayHours());
                }
            }
            professorCursor.close();

        }
        else
            Log.i(tag, "Professor NOT FOUND");

        closeDBProfessor();
    }


    // Getting single professor
    private Professor getProfessorbyId(Long id) {

        Professor professor = null;

        professorManager = new ProfessorDataBaseManager(getActivity());
        professorManager.open();

        cursor = professorManager.getRow(id);

        if (cursor != null && cursor.moveToFirst()) {

            professor = new Professor(cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4), (cursor.getInt(5) > 0), (cursor.getInt(6) > 0), (cursor.getInt(7) > 0),
                    (cursor.getInt(8) > 0), (cursor.getInt(9) > 0), (cursor.getInt(10) > 0), (cursor.getInt(11) > 0), cursor.getString(12), cursor.getString(13),
                    cursor.getString(14), cursor.getString(15), cursor.getString(16), cursor.getString(17), cursor.getString(18));
        }
        professorManager.close();
        return professor;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_professor, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // handle item selection
        switch (item.getItemId()) {
            case R.id.professor_add:
                Bundle bundle = new Bundle();
                bundle.putLong("id", idSubject);

                intent = new Intent(getActivity(), AddProfessorActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
