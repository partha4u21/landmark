package com.landmark.assignment.databinding;


public class AppDataBindingComponent implements android.databinding.DataBindingComponent {

    @Override
    public RecyclerViewDataBinding getRecyclerViewDataBinding() {
        return new RecyclerViewDataBinding();
    }
}
