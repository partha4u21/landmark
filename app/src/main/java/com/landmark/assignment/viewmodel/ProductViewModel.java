
package com.landmark.assignment.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.landmark.assignment.APIResponse;
import com.landmark.assignment.Repository;

import java.util.List;


public class ProductViewModel extends AndroidViewModel {
    private static final String TAG = "ProductViewModel";

    private LiveData<APIResponse> mAPIResponse;
    private Repository mRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "CONSTRUCTOR");

        mRepository = Repository.getInstance();
        mAPIResponse = mRepository.getAPIResponse();
    }

    public LiveData<APIResponse> getProducts() {
        Log.d(TAG, "get product as list");
        return mAPIResponse;
    }
}
