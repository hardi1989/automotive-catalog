package com.ehi.aca.viewmodel;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ehi.aca.Global;
import com.ehi.aca.data.local.entity.ManufacturerEntity;
import com.ehi.aca.data.remote.model.GetManufacturer;
import com.ehi.aca.data.repository.ManufacturerRepository;

import java.util.List;

/*
 * File Description
 * Author: Hardi
 */

public class ManufacturerDetailsViewModel extends ViewModel {
    MutableLiveData<GetManufacturer> manufacturerMutableLiveData;
    ManufacturerRepository manufacturerRepository;
    private LiveData<List<ManufacturerEntity>> listLiveData;

    public void init(Application application, String type) {
        if (manufacturerMutableLiveData != null) {
            return;
        }
        if (type.equals(Global.dataType1))
            manufacturerRepository = new ManufacturerRepository(application);
        else
            manufacturerRepository = ManufacturerRepository.getInstance();

    }

    public void insert(ManufacturerEntity manufacturerEntity) {
        manufacturerRepository.insert(manufacturerEntity);
    }

    public LiveData<GetManufacturer> getManuFactureRepository() {
        manufacturerMutableLiveData = manufacturerRepository.getManufacturerMutableLiveData();
        return manufacturerMutableLiveData;
    }
}
