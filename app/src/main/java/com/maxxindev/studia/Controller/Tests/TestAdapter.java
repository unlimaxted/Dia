package com.maxxindev.studia.Controller.Tests;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maxxindev.studia.Model.Test;
import com.maxxindev.studia.R;

import java.util.List;
import java.util.StringTokenizer;

/**
 * ListView adapter of Test
 */
public class TestAdapter extends ArrayAdapter<Test> {

    private Context context;

    List<Test> datos = null;

    public TestAdapter(Context context, List<Test> datos)
    {
        super(context, R.layout.list_test, datos);
        this.context = context;
        this.datos = datos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_test,null);
        }

        ((TextView) convertView.findViewById(R.id.tvTestName)).setText(datos.get(position).getName() + "  ");
        ((TextView) convertView.findViewById(R.id.tvTestHour)).setText(datos.get(position).getHour());
        ((TextView) convertView.findViewById(R.id.tvPercent)).setText(datos.get(position).getPercent().toString() + "%");
        ((TextView) convertView.findViewById(R.id.tvTestDay)).setText(datos.get(position).getDayOfWeek());

        String qualification = datos.get(position).getQualification().toString();

        if (qualification != "0")
            ((TextView) convertView.findViewById(R.id.tvQualification)).setText(qualification);

        StringTokenizer stringTokenizer = new StringTokenizer(datos.get(position).getDate(), "/");
        String year = stringTokenizer.nextElement().toString();
        String month = stringTokenizer.nextElement().toString();
        String day = stringTokenizer.nextElement().toString();

        ((TextView) convertView.findViewById(R.id.tvDayNumTest)).setText(day);

        View view = convertView.findViewById(R.id.vCircle);
        view.setBackgroundResource(R.drawable.circle);

        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_test, parent, false);
        }

        if (row.getTag() == null)
        {
            TestHolder testHolder = new TestHolder();

            testHolder.setTvTestName((TextView) row.findViewById(R.id.tvTestName));
            testHolder.setTvTestHour((TextView) row.findViewById(R.id.tvTestHour));
            testHolder.setTvPercent((TextView) row.findViewById(R.id.tvPercent));
            testHolder.setView((View) row.findViewById(R.id.vCircle));

            testHolder.setTvDayNumTest((TextView) row.findViewById(R.id.tvDayNumTest));
            testHolder.setTvTestDay((TextView) row.findViewById(R.id.tvTestDay));

            row.setTag(testHolder);
        }

        Test test = datos.get(position);
        ((TestHolder) row.getTag()).getTvTestName().setText(test.getName());
        ((TestHolder) row.getTag()).getTvTestHour().setText(test.getHour());
        ((TestHolder) row.getTag()).getTvPercent().setText(test.getPercent());
        ((TestHolder) row.getTag()).getView();

        ((TestHolder) row.getTag()).getTvTestDay().setText(test.getDayOfWeek());

        StringTokenizer strtoken = new StringTokenizer(test.getDate(), "/");
        String year = strtoken.nextElement().toString();
        String month = strtoken.nextElement().toString();
        String day = strtoken.nextElement().toString();

        ((TestHolder) row.getTag()).getTvDayNumTest().setText(day);

        return row;

    }


    /**
     * Holder of Spinner adapter
     */
    private class TestHolder
    {
        private TextView tvTestName;
        private TextView tvTestHour;
        private TextView tvPercent;
        private TextView tvTestDay;
        private TextView tvDayNumTest;
        private View view;

        public TextView getTvTestName() {
            return tvTestName;
        }

        public void setTvTestName(TextView tvTestName) {
            this.tvTestName = tvTestName;
        }

        public TextView getTvTestHour() {
            return tvTestHour;
        }

        public void setTvTestHour(TextView tvTestHour) {
            this.tvTestHour = tvTestHour;
        }

        public TextView getTvPercent() {
            return tvPercent;
        }

        public void setTvPercent(TextView tvPercent) {
            this.tvPercent = tvPercent;
        }

        public TextView getTvTestDay() {
            return tvTestDay;
        }

        public void setTvTestDay(TextView tvTestDay) {
            this.tvTestDay = tvTestDay;
        }

        public TextView getTvDayNumTest() {
            return tvDayNumTest;
        }

        public void setTvDayNumTest(TextView tvDayNumTest) {
            this.tvDayNumTest = tvDayNumTest;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
            this.view.setBackgroundResource(R.drawable.circle);
        }
    }
}
