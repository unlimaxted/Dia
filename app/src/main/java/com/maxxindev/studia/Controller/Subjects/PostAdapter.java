package com.maxxindev.studia.Controller.Subjects;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxxindev.studia.R;

public class PostAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView ivColorRectangle;
        TextView tvSubject;
        TextView tvUcNumber;
        TextView tvUC;
        TextView tvProfessor;
    }

    private static final String TAG = "CustomAdapter";
    private static int convertViewCounter = 0;

    private ArrayList<SubjectData> data;
    private LayoutInflater inflater = null;

    public PostAdapter(Context c, ArrayList<SubjectData> d)
    {
        this.data = d;
        inflater = LayoutInflater.from(c);
    }

    protected Activity activity;

    public PostAdapter(Activity activity, ArrayList<SubjectData> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        Log.v(TAG, "in getCount()");
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        Log.v(TAG, "in getItem() for position " + position);
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        Log.v(TAG, "in getItemId() for position " + position);
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
        Log.v(TAG, "in getViewTypeCount()");
        return 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        Log.v(TAG, "in getItemViewType() for position " + position);
        return 0;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_subject, null);
            holder = new ViewHolder();

            holder.ivColorRectangle = (TextView) convertView
                    .findViewById(R.id.ivColorRectangle);
            holder.tvSubject = (TextView) convertView
                    .findViewById(R.id.tvSubject);
            holder.tvUcNumber = (TextView) convertView
                    .findViewById(R.id.tvUcNumber);
            holder.tvUC = (TextView) convertView
                    .findViewById(R.id.tvUC);
            holder.tvProfessor = (TextView) convertView
                    .findViewById(R.id.tvProfessor);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvSubject.setText(data.get(position).getSubject());
        holder.tvUcNumber.setText(data.get(position).getUc());
        holder.tvProfessor.setText(data.get(position).getProfessor());

        return convertView;
    }


}