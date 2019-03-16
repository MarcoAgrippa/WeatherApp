package com.igorgvozdic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("main")
    @Expose
    private String mMain;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("icon")
    @Expose
    private String mIcon;

    public Weather(int mId, String mMain, String mDescription, String mIcon) {
        this.mId = mId;
        this.mMain = mMain;
        this.mDescription = mDescription;
        this.mIcon = mIcon;
    }

    public int getmId() {
        return mId;
    }

    public String getmMain() {
        return mMain;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmIcon() {
        return mIcon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "\nmId=" + mId +
                "\n, mMain='" + mMain + '\'' +
                "\n, mDescription='" + mDescription + '\'' +
                "\n, mIcon='" + mIcon + '\'' +
                '}';
    }
}
