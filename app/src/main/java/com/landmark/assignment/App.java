
package com.landmark.assignment;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.landmark.assignment.databinding.AppDataBindingComponent;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
    }
}
