package com.landmark.assignment;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static final String TAG = "ProductRepository";

    private static Repository instance;
    private RestApi mRestApi;

    private Repository() {
        Log.d(TAG, "CONSTRUCTOR");

        mRestApi = RestDao.getRestDao();
    }

    public static Repository getInstance() {
        Log.d(TAG, "GET_INSTANCE");

        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public MutableLiveData<APIResponse> getAPIResponse() {
        Log.d(TAG, "get data");

        final MutableLiveData<APIResponse> data = new MutableLiveData<>();

        mRestApi.getProductList().enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.code() == 200) {
                    APIResponse temp = response.body();
                    data.setValue(response.body());
                    Log.d(TAG + "res", response.toString());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
//                List<Products> Products = new ArrayList<>();
//                Products.add(new Products(0, "Test", 3.551, 50.52, 3.55122, 50.52625));
//                data.setValue(Products);
                Log.d(TAG, t.getMessage());
            }
        });

        return data;
    }




}