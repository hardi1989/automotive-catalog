package com.ehi.aca.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ehi.aca.Global;
import com.ehi.aca.data.local.ACADatabase;
import com.ehi.aca.data.local.dao.ManufacturerDao;
import com.ehi.aca.data.local.entity.ManufacturerEntity;
import com.ehi.aca.data.remote.model.GetManufacturer;
import com.ehi.aca.data.remote.JsonApi;
import com.ehi.aca.data.remote.RetrofitService;
import com.ehi.aca.data.remote.model.Manufacturer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * File Description
 * Author: Hardi
 */

public class ManufacturerRepository {
    private static final String TAG = ManufacturerRepository.class.getName();
    private static ManufacturerRepository manufacturerRepository;
    private JsonApi jsonApi;
    private ACADatabase acaDatabase;
    private LiveData<List<ManufacturerEntity>> allManufacturer;
    private ManufacturerDao manufacturerDao;

    public ManufacturerRepository(Application application) {
        acaDatabase = ACADatabase.getInstance(application);
        manufacturerDao = acaDatabase.manufacturerDao();
        allManufacturer = manufacturerDao.getAllManufacturer();

    }

    private void provideService() {
        jsonApi = RetrofitService.createService();
    }

    public MutableLiveData<GetManufacturer> getManufacturerMutableLiveData() {
        provideService();
        final MutableLiveData<GetManufacturer> manufactureData = new MutableLiveData<>();
        if (jsonApi == null)
            jsonApi.getAllManufacturers().enqueue(new Callback<GetManufacturer>() {
                @Override
                public void onResponse(Call<GetManufacturer> call, Response<GetManufacturer> response) {
                    if (response.isSuccessful()) {
                        manufactureData.setValue(response.body());
                        if (response.body().getResults() != null) {
                            for (Manufacturer manufacturer : response.body().getResults()) {
                                insert(new ManufacturerEntity(manufacturer.getMfr_ID(), manufacturer.getMfr_Name()));
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetManufacturer> call, Throwable t) {
                    Global.eLog(TAG, t.getMessage());
                    manufactureData.setValue(null);
                }
            });
        return manufactureData;
    }


    public void insert(ManufacturerEntity manufacturerEntity) {
        new ManufacturerDetailAsyncTask(manufacturerDao, "insert").execute(manufacturerEntity);
    }

    public LiveData<List<ManufacturerEntity>> getAlManufacturer() {
        return allManufacturer;
    }


    private static class ManufacturerDetailAsyncTask extends AsyncTask<ManufacturerEntity, Void, Void> {
        ManufacturerDao manufacturerDao;
        String methodName;

        ManufacturerDetailAsyncTask(ManufacturerDao pdDao, String methodName) {
            this.manufacturerDao = pdDao;
            this.methodName = methodName;
        }

        @Override
        protected Void doInBackground(ManufacturerEntity... manufacturerEntities) {
            switch (methodName) {
                case "insert":
                    manufacturerDao.insert(manufacturerEntities[0]);
                    break;
                case "update":
                    manufacturerDao.update(manufacturerEntities[0]);
                    break;
                case "delete":
                    manufacturerDao.delete(manufacturerEntities[0]);
                    break;


            }
            return null;
        }
    }

}

