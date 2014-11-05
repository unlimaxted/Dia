package com.maxxindev.studia.Controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maxxindev.studia.Controller.Professor.ProfessorFragment;
import com.maxxindev.studia.Controller.Tests.TestFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    // Declare the number of ViewPager pages
    final int PAGE_COUNT = 2;
    private String titles[] = new String[] { "Test", "Professor" };

    Long idProfessorRecieved = null;
    Long idTestRecieved = null;

    private Long idSubjectReceived = null;


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setArguments (Long id)
    {
        idSubjectReceived = id;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                TestFragment testFragment = new TestFragment().newInstance(idSubjectReceived);
                return testFragment;

            case 1:
                ProfessorFragment professorFragment = new ProfessorFragment().newInstance(idSubjectReceived);
                return professorFragment;
        }
        return null;
    }


    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }



}
