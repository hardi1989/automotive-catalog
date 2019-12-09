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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ehi.aca.adapter.SpinnerCustomAdapter_Makes;
import com.ehi.aca.adapter.SpinnerCustomAdapter_Manufacturer;
import com.ehi.aca.adapter.SpinnerCustomAdapter_Models;
import com.ehi.aca.data.local.entity.ManufacturerEntity;
import com.ehi.aca.data.remote.model.GetMakes;
import com.ehi.aca.data.remote.model.Make;
import com.ehi.aca.data.remote.model.VModel;
import com.ehi.aca.viewmodel.ManufacturerDetailsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Context mContext;

    @BindView(R.id.spinner_manufacturer)
    Spinner spinner_manufacturer;
    @BindView(R.id.spinner_makes)
    Spinner spinner_makes;
    @BindView(R.id.spinner_models)
    Spinner spinner_models;


    private ManufacturerDetailsViewModel manufacturerDetailsViewModel;
    Unbinder unbinder;
    private SpinnerCustomAdapter_Manufacturer manufacturer_adapter;
    private SpinnerCustomAdapter_Makes makes_adapter;
    private SpinnerCustomAdapter_Models models_adapter;
    private int mfr_id,mk_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        unbinder = ButterKnife.bind(this);

        //Create instance of viewmodel
        manufacturerDetailsViewModel = ViewModelProviders.of(this).get(ManufacturerDetailsViewModel.class);
        manufacturerDetailsViewModel.init(getApplication(), Global.dataType1);

        //get all manufacturer from database
        manufacturerDetailsViewModel.getAlManufacturer().observe(this, new Observer<List<ManufacturerEntity>>() {
            @Override
            public void onChanged(List<ManufacturerEntity> manufacturerEntities) {
                manufacturer_adapter = new SpinnerCustomAdapter_Manufacturer(mContext, manufacturerEntities);
                spinner_manufacturer.setAdapter(manufacturer_adapter);
            }
        });

        /*1.On spinner selection ,pass id to getMakesForManufactureId()
          2.Retrive List of make object
          3.initialize spinner_make with List of make object
         */
        spinner_manufacturer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mfr_id  = manufacturer_adapter.getItem(pos).getMfr_Id();
                manufacturerDetailsViewModel.getMakesForManufactureId(mfr_id).observe(MainActivity.this, new Observer<List<Make>>() {
                    @Override
                    public void onChanged(List<Make> makes) {
                        makes_adapter = new SpinnerCustomAdapter_Makes(mContext, makes);
                        spinner_makes.setAdapter(makes_adapter);
                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parent) {   }
        });


          /*1.On spinner selection ,pass id to getModelsForMakeId()
          2.Retrive List of model object
          3.initialize spinner_model with List of model object
         */
        spinner_makes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mk_id=makes_adapter.getItem(position).getMake_ID();
                manufacturerDetailsViewModel.getModelsForMakeId(mk_id).observe(MainActivity.this, new Observer<List<VModel>>() {
                    @Override
                    public void onChanged(List<VModel> vModels) {
                        models_adapter= new SpinnerCustomAdapter_Models(mContext,vModels);
                        spinner_models.setAdapter(models_adapter);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
