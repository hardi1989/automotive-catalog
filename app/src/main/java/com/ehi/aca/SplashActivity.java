package com.ehi.aca;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ehi.aca.viewmodel.ManufacturerDetailsViewModel;

/*
 * File Description
 * Author: Hardi
 */

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private Context mContext;
    ManufacturerDetailsViewModel manufacturerDetailsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = SplashActivity.this;
        manufacturerDetailsViewModel = ViewModelProviders.of(this).get(ManufacturerDetailsViewModel.class);
        manufacturerDetailsViewModel.init(getApplication(), Global.dataType1);
        /*
        manufacturerDetailsViewModel.getManuFactureRepository().observe(this, new Observer<GetManufacturer>() {
            @Override
            public void onChanged(GetManufacturer getManufacturer) {

            }
        });*/

    }


}
