package com.epicodus.weather.model;

import android.content.Context;

/**
 * Created by Guest on 3/22/16.
 */
public class CurrentWeather {
    private String mName;
    private Double mTempMain;
    private Integer mHumidity;
    private Double mPressure;
    private String mDescription;
    private Double mWindSpeed;
    private String mIcon;
    private Context mContext;

    public CurrentWeather(String mName, Double mTempMain, Integer mHumidity, Double mPressure, String mDescription, Double mWindSpeed, String mIcon, Context mContext) {
        this.mName = mName;
        this.mTempMain = mTempMain;
        this.mHumidity = mHumidity;
        this.mPressure = mPressure;
        this.mDescription = mDescription;
        this.mWindSpeed = mWindSpeed;
        this.mIcon = mIcon;
        this.mContext = mContext;
    }

    public String getmName() {
        return mName;
    }

    public Double getmTempMain() {
        return mTempMain;
    }

    public Integer getmHumidity() {
        return mHumidity;
    }

    public Double getmPressure() {
        return mPressure;
    }

    public String getmDescription() {
        return mDescription;
    }

    public Double getmWindSpeed() {
        return mWindSpeed;
    }

    public String getmIcon() {
        return mIcon;
    }

    public Context getmContext() {
        return mContext;
    }
}
