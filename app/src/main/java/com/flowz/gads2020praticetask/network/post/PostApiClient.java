package com.flowz.gads2020praticetask.network.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApiClient {

    public static final String BASE_URL = " https://docs.google.com/forms/d/e/";

    public  static Retrofit retrofit;


    public static Retrofit getApiClient() {

        Gson gson = new GsonBuilder().create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    public static PostApiInterface submit(){

        PostApiInterface postApiInterface = getApiClient().create(PostApiInterface.class);
        return postApiInterface;
    }
}
