package com.ehi.aca.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.ehi.aca.R;
import com.ehi.aca.data.local.entity.ManufacturerEntity;

import java.util.List;

public class SpinnerCustomAdapter extends BaseAdapter implements SpinnerAdapter {
    private List<ManufacturerEntity> spinnerArrayObjects;
    private Context context;

    public SpinnerCustomAdapter(Context context, List<ManufacturerEntity> spinnerArrayObjects) {
        this.spinnerArrayObjects = spinnerArrayObjects;
        this.context = context;
    }
    @Override
    public int getCount() {
        return spinnerArrayObjects.size();
    }

    @Override
    public ManufacturerEntity getItem(int position) {
        return spinnerArrayObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return
                spinnerArrayObjects.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_spinner, null);
        TextView textView = (TextView) view.findViewById(R.id.textview_spinneritem);
        textView.setText((CharSequence) spinnerArrayObjects.get(position).getMfr_Name());
        return textView;
    }

//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//
//        View view;
//        view = View.inflate(context, R.layout.company_dropdown, null);
//        final TextView textView = (TextView) view.findViewById(R.id.dropdown);
//        textView.setText(spinnerArrayObjects.size());
//        return view;
//    }
}
