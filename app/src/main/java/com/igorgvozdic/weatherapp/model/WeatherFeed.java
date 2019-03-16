package com.igorgvozdic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherFeed {

    @SerializedName("weather")
    @Expose
    private List<Weather> mWeather;

    @SerializedName("main")
    @Expose
    private Main mMain;

    @SerializedName("visibility")
    @Expose
    private int mVisibility;

    @SerializedName("wind")
    @Expose
    private Wind mWind;

    @SerializedName("clouds")
    @Expose
    private Clouds mClouds;

    @SerializedName("name")
    @Expose
    private String mCity;

    @SerializedName("dt_txt")
    @Expose
    private String mDate;

    public List<Weather> getmWeather() {
        return mWeather;
    }

    public Main getmMain() {
        return mMain;
    }

    public int getmVisibility() {
        return mVisibility;
    }

    public Wind getmWind() {
        return mWind;
    }

    public Clouds getmClouds() {
        return mClouds;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return "WeatherFeed{" +
                "\nmWeather=" + mWeather.toString() +
                "\n, mMain=" + mMain.toString() +
                "\n, mVisibility=" + mVisibility +
                "\n, mWind=" + mWind.toString() +
                "\n, mClouds=" + mClouds.toString() +
                "\n, mCity='" + mCity + '\'' +
                "\n, mDate='" + mDate + '\'' +
                '}';
    }
}
