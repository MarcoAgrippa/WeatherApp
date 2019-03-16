package com.igorgvozdic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private double mTemp;

    @SerializedName("pressure")
    @Expose
    private double mPressure;

    @SerializedName("humidity")
    @Expose
    private int mHumidity;

    @SerializedName("temp_min")
    @Expose
    private double mMinTemp;

    @SerializedName("temp_max")
    @Expose
    private double mMaxTemp;


    public Main(double mTemp, double mPressure, int mHumidity, double mMinTemp, double mMaxTemp) {
        this.mTemp = mTemp;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        this.mMinTemp = mMinTemp;
        this.mMaxTemp = mMaxTemp;
    }

    public double getmTemp() {
        return mTemp;
    }

    public double getmPressure() {
        return mPressure;
    }

    public int getmHumidity() {
        return mHumidity;
    }

    public double getmMinTemp() {
        return mMinTemp;
    }

    public double getmMaxTemp() {
        return mMaxTemp;
    }

    @Override
    public String toString() {
        return "Main{" +
                "\nmTemp=" + mTemp +
                "\n, mPressure=" + mPressure +
                "\n, mHumidity=" + mHumidity +
                "\n, mMinTemp=" + mMinTemp +
                "\n, mMaxTemp=" + mMaxTemp +
                '}';
    }
}
