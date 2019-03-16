package com.igorgvozdic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastFeed {

    @SerializedName("list")
    @Expose
    private List<WeatherFeed> mForecast;

    @SerializedName("city")
    @Expose
    private City mCity;

    public List<WeatherFeed> getmForecast() {
        return mForecast;
    }

    public City getmCity() {
        return mCity;
    }

    @Override
    public String toString() {
        return "ForecastFeed{" +
                "\nmForecast=" + mForecast +
                "\n, mCity=" + mCity +
                '}';
    }
}
