package com.ehi.aca.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.ehi.aca.R;
import com.ehi.aca.data.remote.model.Make;
import com.ehi.aca.data.remote.model.VModel;

import java.util.List;

public class SpinnerCustomAdapter_Models extends BaseAdapter implements SpinnerAdapter {
    private List<VModel> spinnerArrayObjects;
    private Context context;

    public SpinnerCustomAdapter_Models(Context context, List<VModel> spinnerArrayObjects) {
        this.spinnerArrayObjects = spinnerArrayObjects;
        this.context = context;
    }
    @Override
    public int getCount() {
        return spinnerArrayObjects.size();
    }

    @Override
    public VModel getItem(int position) {
        return spinnerArrayObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return
                spinnerArrayObjects.get(position).getModel_ID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_spinner, null);
        TextView textView = (TextView) view.findViewById(R.id.textview_spinneritem);
        textView.setText((CharSequence) spinnerArrayObjects.get(position).getModel_Name());

        return textView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
