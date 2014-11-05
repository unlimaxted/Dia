package com.maxxindev.studia.Controller.Subjects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxxindev.studia.Model.Color;
import com.maxxindev.studia.R;

import java.util.List;

/**
 * Class of spinner-s color adapter
 */
public class SpinnerColorsAdapter extends ArrayAdapter<Color> {

    private Context context;

    List<Color> datos = null;

    public SpinnerColorsAdapter(Context context, List<Color> datos)
    {
        super(context, R.layout.spinner_selected_item, datos);
        this.context = context;
        this.datos = datos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.spinner_selected_item,null);
        }
        ((TextView) convertView.findViewById(R.id.texto)).setText(datos.get(position).getNombre());
        ((ImageView) convertView.findViewById(R.id.icono)).setBackgroundResource(datos.get(position).getIcono());

        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.spinner_list_item, parent, false);
        }

        if (row.getTag() == null)
        {
            ColorHolder colorHolder = new ColorHolder();
            colorHolder.setIcono((ImageView) row.findViewById(R.id.icono));
            colorHolder.setTextView((TextView) row.findViewById(R.id.texto));
            row.setTag(colorHolder);
        }

        Color color = datos.get(position);
        ((ColorHolder) row.getTag()).getIcono().setImageResource(color.getIcono());
        ((ColorHolder) row.getTag()).getTextView().setText(color.getNombre());

        return row;
    }

    /**
     * Holder of Spinner adapter
     */
    private class ColorHolder
    {

        private ImageView icono;

        private TextView textView;

        public ImageView getIcono()
        {
            return icono;
        }

        public void setIcono(ImageView icono)
        {
            this.icono = icono;
        }

        public TextView getTextView()
        {
            return textView;
        }

        public void setTextView(TextView textView)
        {
            this.textView = textView;
        }

    }
}
