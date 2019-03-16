package com.igorgvozdic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private double mSpeed;

    public Wind(double mSpeed) {
        this.mSpeed = mSpeed;
    }

    public double getmSpeed() {
        return mSpeed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "mSpeed=" + mSpeed +
                '}';
    }
}
