package com.epicodus.weather.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.epicodus.weather.R;

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

    public String getmTempMain() {
        return (mTempMain.toString() + " ºF");
    }

    public String getmHumidity() {
        return (mHumidity + " %");
    }

    public String getmPressure() {
        return (mPressure + " hPa");
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmWindSpeed() {
        return (mWindSpeed + " MPH");
    }

    public String getmIcon() {
        return mIcon;
    }

    public Context getmContext() {
        return mContext;
    }

    public Integer getImageId() {
        Context context = mContext;
        TypedArray imageHashMap = mContext.getResources().obtainTypedArray(R.array.icons);
        TypedArray imageArray;
        for (int i=0; i<imageHashMap.length(); i++) {
            imageArray = mContext.getResources().obtainTypedArray(imageHashMap.getResourceId(i, -1));
            Log.d("icon: ", imageArray.getString(0).toString());
            if (imageArray.getString(0).toString().equals(mIcon)) {
                return imageArray.getResourceId(1, -1);
            }
        }
        return -1;
    }
}
