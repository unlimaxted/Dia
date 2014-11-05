package com.maxxindev.studia.Controller.Subjects;

import java.lang.reflect.Field;

import com.maxxindev.studia.Controller.ViewPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxxindev.studia.R;

/**
 * Subject Information fragment
 */
public class SubjectInfoFragment extends Fragment  {

    public Long idSubject = null;
    private String tag = null;

    private ViewPagerAdapter viewPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager, container, false);
        // Locate the ViewPager in viewpager_main.xml
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        idSubject = getArguments().getLong("id");

        //Log.i(tag, "A LA SUBJECTINFO FRAGMENT LLEGO ESTE ID " + idSubject);

        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.setArguments(idSubject);
        mViewPager.setAdapter(viewPagerAdapter);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
