package com.flowz.gads2020praticetask.network.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://gadsapi.herokuapp.com/";

    public  static Retrofit retrofit;


    public static Retrofit getApiClient() {

        Gson gson = new GsonBuilder().create();

        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}
