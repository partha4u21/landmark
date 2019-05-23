package com.landmark.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse {

    @SerializedName("conversion")
    @Expose
    private List<Conversion> conversion;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("products")
    @Expose
    private List<Products> products;

    public class Products {

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("price")
        @Expose
        private float price;

        @SerializedName("currency")
        @Expose
        private String currency;

        public Products() {
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
    }

    public class Conversion {

        @SerializedName("from")
        @Expose
        private String from;

        @SerializedName("to")
        @Expose
        private String to;

        @SerializedName("rate")
        @Expose
        private String rate;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
    }

    public List<Conversion> getConversion() {
        return conversion;
    }

    public void setConversion(List<Conversion> conversion) {
        this.conversion = conversion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
