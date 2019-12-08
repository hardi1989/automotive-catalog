package com.ehi.aca.data.remote;

import com.ehi.aca.data.remote.model.GetManufacturer;

import retrofit2.Call;
import retrofit2.http.GET;

/*
 * File Description
 * Author: Hardi
 */

public interface JsonApi {

    @GET("getallmanufacturers?ManufacturerType=Intermediate&format=json")
    Call<GetManufacturer> getAllManufacturers();
}

