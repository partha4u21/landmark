package com.landmark.assignment.api;

import com.landmark.assignment.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("/common/json/assignment.json")
    Call<APIResponse> getProductList();
}
