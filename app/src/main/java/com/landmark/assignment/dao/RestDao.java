package com.landmark.assignment.dao;

import com.landmark.assignment.Constants;
import com.landmark.assignment.api.RestApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestDao {
    private static Retrofit instance;

    private static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(Constants.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public static RestApi getRestDao() {
        return getInstance().create(RestApi.class);
    }
}