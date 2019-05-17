package com.landmark.assignment.helpers;

import android.support.annotation.NonNull;

import java.util.HashMap;

public class CurrencyHelper {

    private static final HashMap<String, HashMap<String, Double>> ConversionRate = new HashMap<>();

    public enum CURRENCY {
        AED,
        INR,
        SAR
    }

    public static boolean contains(String currency) {
        for (CURRENCY c : CURRENCY.values()) {
            if (c.name().equals(currency)) {
                return true;
            }
        }
        return false;
    }

    public static void updateConversionRateTable(@NonNull String from, @NonNull String to, @NonNull float rate) {
        updateConversionRate(from, to, rate);
        updateConversionRate(to, from, rate != 0 ? 1 / rate : 0);
    }

    private static void updateConversionRate(@NonNull String from, @NonNull String to, @NonNull float rate) {
        if (ConversionRate.containsKey(from)) {
            HashMap<String, Double> existingValues = ConversionRate.get(from);
            existingValues.put(to, Double.valueOf(rate));
        } else {
            HashMap<String, Double> toRate = new HashMap<>();
            toRate.put(to, Double.valueOf(rate));
            ConversionRate.put(from, toRate);
        }
    }

    public static float getConversionRate(String from, String to) {
        float rate = 0.0f;
        if (ConversionRate.containsKey(from)) {
            HashMap<String, Double> rateMap = ConversionRate.get(from);
            if (rateMap.containsKey(to)) {
                return rateMap.get(to).floatValue();
            }
        } else if (ConversionRate.containsKey(to)) {
            HashMap<String, Double> rateMap = ConversionRate.get(to);
            if (rateMap.containsKey(from)) {
                float value = rateMap.get(from).floatValue();
                if (value != 0) {
                    return 1 / value;
                }
            }
        } else {

        }

        return rate;
    }

}
