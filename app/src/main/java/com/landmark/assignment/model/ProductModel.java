
package com.landmark.assignment.model;

import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;


public class ProductModel {
    private String name;
    private String url;
    private float price;
    private String currency;

    public ProductModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static ProductModel getModel(JSONObject json) throws JSONException {
        ProductModel model = new ProductModel();
        model.setName(json.getString("name"));
        model.setUrl(json.getString("url"));
        model.setPrice((float) json.getDouble("price"));
        model.setCurrency(json.getString("currency"));
        return model;
    }

    @BindingAdapter("url")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .into(view);
    }
}
