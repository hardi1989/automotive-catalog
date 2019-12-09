package com.ehi.aca.data.remote;

import com.ehi.aca.Global;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * File Description
 * Author: Hardi
 */

public class RetrofitService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Global.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static JsonApi createService() {
        return retrofit.create(JsonApi.class);
    }
}
