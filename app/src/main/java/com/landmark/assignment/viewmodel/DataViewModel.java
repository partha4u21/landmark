
package com.landmark.assignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.landmark.assignment.adapter.DataAdapter;
import com.landmark.assignment.model.DataModel;
import com.landmark.assignment.BR;

import java.util.ArrayList;
import java.util.List;


public class DataViewModel extends BaseObservable {
    private static final String TAG = "DataViewModel";
    private DataAdapter adapter;
    private List<DataModel> data;

    public DataViewModel() {
        data = new ArrayList<>();
        adapter = new DataAdapter();
    }

    public void setUp() {
        // perform set up tasks, such as adding listeners, data population, etc.
        populateData();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    @Bindable
    public List<DataModel> getData() {
        return this.data;
    }

    @Bindable
    public DataAdapter getAdapter() {
        return this.adapter;
    }

    private void populateData() {
        // populate the data from the source, such as the database.
        for (int i = 0; i < 50; i++) {
            DataModel dataModel = new DataModel();
            dataModel.setTitle(String.valueOf(i));
            data.add(dataModel);
        }
        notifyPropertyChanged(BR.data);
    }
}
