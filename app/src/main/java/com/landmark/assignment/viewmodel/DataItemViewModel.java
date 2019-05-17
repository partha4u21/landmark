
package com.landmark.assignment.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.landmark.assignment.model.ProductModel;

public class DataItemViewModel extends BaseObservable {
    private ProductModel productModel;

    public DataItemViewModel(ProductModel dataModel) {
        this.productModel = dataModel;
    }

    @Bindable
    public String getTitle() {
        return !TextUtils.isEmpty(productModel.getName()) ? productModel.getName() : "";
    }

    @Bindable
    public String getCurrency() {
        return !TextUtils.isEmpty(productModel.getCurrency()) ? productModel.getCurrency() : "";
    }

    @Bindable
    public String getPrice() {
        return String.valueOf(productModel.getPrice());
    }

    @Bindable
    public String getUrl() {
        return !TextUtils.isEmpty(productModel.getUrl()) ? productModel.getUrl() : "";
    }
}
