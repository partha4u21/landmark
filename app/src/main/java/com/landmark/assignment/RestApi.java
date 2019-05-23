package com.landmark.assignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {
    @GET("/common/json/assignment.json")
    Call<APIResponse> getProductList();
}
