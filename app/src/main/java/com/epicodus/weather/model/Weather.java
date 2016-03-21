package com.epicodus.weather.model;

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

    public Weather(String name, String date, Double tempMain, Double tempMax, Double tempMin, Integer humidity, Double pressure, String mainWeather, String description, Double windSpeed, String icon) {
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

}
