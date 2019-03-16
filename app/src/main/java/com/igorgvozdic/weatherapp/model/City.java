package com.igorgvozdic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("country")
    @Expose
    private String mCountry;


    public String getmName() {
        return mName;
    }

    public String getmCountry() {
        return mCountry;
    }

    @Override
    public String toString() {
        return "City{" +
                "\nmName='" + mName + '\'' +
                "\n, mCountry='" + mCountry + '\'' +
                '}';
    }
}
