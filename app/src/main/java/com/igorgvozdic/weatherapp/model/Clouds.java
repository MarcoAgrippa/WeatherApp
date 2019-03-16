package com.igorgvozdic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Clouds {

    @SerializedName("all")
    @Expose
    private int mAll;

    public Clouds(int mAll) {
        this.mAll = mAll;
    }

    public int getmAll() {
        return mAll;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "mAll=" + mAll +
                '}';
    }


}
