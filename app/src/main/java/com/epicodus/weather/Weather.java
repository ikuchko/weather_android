package com.epicodus.weather;

/**
 * Created by Guest on 3/21/16.
 */
public class Weather {
    private String mName;
    private String mDate;
    private String mTempMain;
    private String mTempMax;
    private String mTempMin;
    private String mHumidity;
    private String mPressure;
    private String mMainWeather;
    private String mDescription;
    private String mWindSpeed;

    public Weather(String name, String date, String tempMain, String tempMax, String tempMin, String humidity, String pressure, String mainWeather, String description, String windSpeed) {
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
    }

    public String getName() {
        return mName;
    }

    public String getmTempMain() {
        return mTempMain;
    }

    public String getmTempMax() {
        return mTempMax;
    }

    public String getmTempMin() {
        return mTempMin;
    }

    public String getmHumidity() {
        return mHumidity;
    }

    public String getmPressure() {
        return mPressure;
    }

    public String getmMainWeather() {
        return mMainWeather;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmWindSpeed() {
        return mWindSpeed;
    }
}
