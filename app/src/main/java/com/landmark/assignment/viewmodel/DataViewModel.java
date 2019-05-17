
package com.landmark.assignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.landmark.assignment.BR;
import com.landmark.assignment.adapter.DataAdapter;
import com.landmark.assignment.helpers.ConfigHelper;
import com.landmark.assignment.model.ProductModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DataViewModel extends BaseObservable {
    private static final String TAG = "DataViewModel";
    private DataAdapter adapter;
    private List<ProductModel> data;

    public DataViewModel() {
        data = new ArrayList<>();
        adapter = new DataAdapter();
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
        data.clear();

        try {
            JSONObject producConfig = ConfigHelper.getProductConfig();
            for (int i = 0; i < producConfig.length(); i++) {
                JSONObject item = producConfig.getJSONObject(String.valueOf(i));
                data.add(ProductModel.getModel(item));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        notifyPropertyChanged(BR.data);
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public List<ProductModel> getData() {
        return this.data;
    }

    @Bindable
    public DataAdapter getAdapter() {
        return this.adapter;
    }
}
