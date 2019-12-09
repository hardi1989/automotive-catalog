package com.ehi.aca.data.remote;

import com.ehi.aca.data.remote.model.GetMakes;
import com.ehi.aca.data.remote.model.GetManufacturer;
import com.ehi.aca.data.remote.model.GetModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
 * File Description
 * Author: Hardi
 */

public interface JsonApi {

    @GET("getallmanufacturers?format=json&page=2")
    Call<GetManufacturer> getAllManufacturers();

    @GET("GetMakeForManufacturer/{id}?format=json")
    Call<GetMakes> getMakesForManufactureId(@Path("id") int manufactureId);

    @GET("GetModelsForMakeId/{id}?format=json")
    Call<GetModel> getModelsForMakeId(@Path("id") int makeid);
}

