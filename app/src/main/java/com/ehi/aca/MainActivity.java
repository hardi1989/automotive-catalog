package com.ehi.aca;

/*
 * File Description
 * Author: Hardi
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.Spinner;

import com.ehi.aca.adapter.SpinnerCustomAdapter;
import com.ehi.aca.data.local.entity.ManufacturerEntity;
import com.ehi.aca.viewmodel.ManufacturerDetailsViewModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Context mContext;

    @BindView(R.id.spinner_manufacturer)
    Spinner materialBetterSpinner;

    private ManufacturerDetailsViewModel manufacturerDetailsViewModel;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        unbinder = ButterKnife.bind(this);

        manufacturerDetailsViewModel = ViewModelProviders.of(this).get(ManufacturerDetailsViewModel.class);
        manufacturerDetailsViewModel.init(getApplication(), Global.dataType1);
        manufacturerDetailsViewModel.getAlManufacturer().observe(this, new Observer<List<ManufacturerEntity>>() {
            @Override
            public void onChanged(List<ManufacturerEntity> manufacturerEntities) {
                SpinnerCustomAdapter adapter = new SpinnerCustomAdapter(mContext, manufacturerEntities);
                materialBetterSpinner.setAdapter(adapter);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
