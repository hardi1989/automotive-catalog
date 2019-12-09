package com.ehi.aca.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.ehi.aca.R;
import com.ehi.aca.data.remote.model.Make;

import java.util.List;

public class SpinnerCustomAdapter_Makes extends BaseAdapter implements SpinnerAdapter {
    private List<Make> spinnerArrayObjects;
    private Context context;

    public SpinnerCustomAdapter_Makes(Context context, List<Make> spinnerArrayObjects) {
        this.spinnerArrayObjects = spinnerArrayObjects;
        this.context = context;
    }
    @Override
    public int getCount() {
        return spinnerArrayObjects.size();
    }

    @Override
    public Make getItem(int position) {
        return spinnerArrayObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return
                spinnerArrayObjects.get(position).getMake_ID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_spinner, null);
        TextView textView = (TextView) view.findViewById(R.id.textview_spinneritem);
        textView.setText((CharSequence) spinnerArrayObjects.get(position).getMake_Name());

        return textView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
