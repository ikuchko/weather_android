package com.epicodus.weather.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.epicodus.weather.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Guest on 3/21/16.
 */
public class Weather {
    private String mName;
    private String mDate;
    private String mDayOfWeek;
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
    private static ArrayList<String> dayOfWeekList = new ArrayList<>();

    public Weather(Context context, String name, String date, String dayOfWeek, Double tempMain, Double tempMax, Double tempMin, Integer humidity, Double pressure, String mainWeather, String description, Double windSpeed, String icon) {
        this.mName = name;
        this.mDate = date;
        this.mDayOfWeek = dayOfWeek;
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

        Boolean flag = false;
        for (int i=0; i<dayOfWeekList.size(); i++) {
            if (dayOfWeekList.get(i).equals(dayOfWeek)) {
                flag = true;
            }
        }
        if (!flag) {
            dayOfWeekList.add(dayOfWeek);
        }
    }

    public String getmDayOfWeek() { return mDayOfWeek; }

    public String getName() {
        return mName;
    }

    public String getDate() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(mDate);
            return dateFormat.format(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return mDate;
    }

    public String getTempMain() {
        return mTempMain.toString() + " ºF";
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

    public static ArrayList<String> getDayOfWeekList() {
        return dayOfWeekList;
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
