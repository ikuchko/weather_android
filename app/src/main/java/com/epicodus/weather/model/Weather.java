package com.epicodus.weather.model;

import android.content.Context;
import android.content.res.TypedArray;

import com.epicodus.weather.R;

/**
 * Created by Guest on 3/21/16.
 */
public class Weather {
    private String mName;
    private String mDate;
    private Double mTempMain;
    private Double mTempMax;
    private Double mTempMin;
    private Integer mHumidity;
    private Double mPressure;
    private String mMainWeather;
    private String mDescription;
    private Double mWindSpeed;
    private String mIcon;
    private Context mContext;

    public Weather(Context context, String name, String date, Double tempMain, Double tempMax, Double tempMin, Integer humidity, Double pressure, String mainWeather, String description, Double windSpeed, String icon) {
        this.mName = name;
        this.mDate = date;
        this.mTempMain = tempMain;
        this.mTempMax = tempMax;
        this.mTempMin = tempMin;
        this.mHumidity = humidity;
        this.mPressure = pressure;
        this.mMainWeather = mainWeather;
        this.mDescription = description;
        this.mWindSpeed = windSpeed;
        this.mIcon = icon;
        this.mContext = context;
    }

    public String getName() {
        return mName;
    }

    public String getDate() {
        return mDate;
    }

    public Double getTempMain() {
        return mTempMain;
    }

    public Double getTempMax() {
        return mTempMax;
    }

    public Double getTempMin() {
        return mTempMin;
    }

    public Integer getHumidity() {
        return mHumidity;
    }

    public Double getPressure() {
        return mPressure;
    }

    public String getMainWeather() {
        return mMainWeather;
    }

    public String getDescription() {
        return mDescription;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public String getIcon() {
        return mIcon;
    }

    public Integer getImageId() {
        Context context = mContext;
        TypedArray imageHashMap = mContext.getResources().obtainTypedArray(R.array.icons);
        TypedArray imageArray;
        for (int i=0; i<imageHashMap.length(); i++) {
            imageArray = mContext.getResources().obtainTypedArray(imageHashMap.getResourceId(i, -1));
            if (imageArray.getString(0).toString() == mIcon) {
                return imageArray.getResourceId(1, -1);
            }
        }
        return -1;
    }

}
