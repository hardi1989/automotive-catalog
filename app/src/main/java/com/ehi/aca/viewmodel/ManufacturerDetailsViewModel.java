package com.ehi.aca.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehi.aca.data.local.entity.ManufacturerEntity;
import com.ehi.aca.data.remote.model.GetMakes;
import com.ehi.aca.data.remote.model.GetManufacturer;
import com.ehi.aca.data.remote.model.Make;
import com.ehi.aca.data.repository.ManufacturerRepository;

import java.util.List;

/*
 * File Description
 * Author: Hardi
 */

public class ManufacturerDetailsViewModel extends ViewModel {
    private static final String TAG = ManufacturerDetailsViewModel.class.getName();
    MutableLiveData<GetManufacturer> manufacturerMutableLiveData;
    ManufacturerRepository manufacturerRepository;
    private LiveData<List<ManufacturerEntity>> listLiveData;

    public void init(Application application, String type) {
        if (manufacturerMutableLiveData != null) {
            return;
        }

        manufacturerRepository = new ManufacturerRepository(application);

    }

    public void insert(ManufacturerEntity manufacturerEntity) {
        manufacturerRepository.insert(manufacturerEntity);
    }

    //get all manufacturer from database
    public LiveData<List<ManufacturerEntity>> getAlManufacturer() {
        return manufacturerRepository.getAlManufacturer();
    }

    //get all manufacturer from api
    public LiveData<List<Make>> getMakesForManufactureId(int id) {
        return manufacturerRepository.getMakesForManufactureId(id);
    }


    //get all manufacturer from api
    public LiveData<GetManufacturer> getManuFactureData() {
        manufacturerMutableLiveData = manufacturerRepository.getManufacturerMutableLiveData();
        return manufacturerMutableLiveData;
    }
}
