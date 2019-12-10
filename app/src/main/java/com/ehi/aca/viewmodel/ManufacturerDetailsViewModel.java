package com.ehi.aca.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ehi.aca.data.local.entity.ManufacturerEntity;
import com.ehi.aca.data.remote.model.GetManufacturer;
import com.ehi.aca.data.remote.model.Make;
import com.ehi.aca.data.remote.model.Progress;
import com.ehi.aca.data.remote.model.VModel;
import com.ehi.aca.data.repository.ManufacturerRepository;

import java.util.List;

/*
 * File Description
 * Author: Hardi
 */

public class ManufacturerDetailsViewModel extends AndroidViewModel {
    private static final String TAG = ManufacturerDetailsViewModel.class.getName();
    MutableLiveData<GetManufacturer> manufacturerMutableLiveData;
    ManufacturerRepository manufacturerRepository;
    private LiveData<List<ManufacturerEntity>> listLiveData;
    LiveData<List<ManufacturerEntity>> manufactureEntityList;
    LiveData<List<Make>> makeObjList;
    LiveData<List<VModel>> modelObjList;


    public ManufacturerDetailsViewModel(@NonNull Application application){
        super(application);
        if (manufacturerMutableLiveData != null) {
            return;
        }
        manufacturerRepository = new ManufacturerRepository(application);
    }

    public void insert(ManufacturerEntity manufacturerEntity) {
        manufacturerRepository.insert(manufacturerEntity);
    }


    public LiveData<Progress> getIsLoading(){
        LiveData<Progress> isLoading=manufacturerRepository.getIsLoading();
        return isLoading;
    }

    //get all manufacturer from database
    public LiveData<List<ManufacturerEntity>> getAlManufacturer() {
        manufactureEntityList=manufacturerRepository.getAlManufacturer();
        return manufactureEntityList;
    }

    //get all make from api
    public LiveData<List<Make>> getMakesForManufactureId(int id) {
        makeObjList=manufacturerRepository.getMakesForManufactureId(id);
        return makeObjList;
    }

    //get all models from api
    public LiveData<List<VModel>> getModelsForMakeId(int id) {
        modelObjList=manufacturerRepository.getModelsForMakeId(id);
        return modelObjList;
    }

    //get all manufacturer from api
    public LiveData<GetManufacturer> getManuFactureData() {
        manufacturerMutableLiveData = manufacturerRepository.getManufacturerMutableLiveData();
        return manufacturerMutableLiveData;
    }
}
