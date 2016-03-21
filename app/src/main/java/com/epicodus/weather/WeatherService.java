package com.epicodus.weather;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

/**
 * Created by Guest on 3/21/16.
 */
public class WeatherService {
    private Context mContext;

    public WeatherService (Context context) {
        this.mContext = context;
    }

    public void findForcast(String location, Callback callback) {
        String CONSUMER_KEY = mContext.getString(R.string.consumer_key);

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.openweathermap.org/data/2.5/forecast?APPID=" + CONSUMER_KEY).newBuilder();
        urlBuilder.addQueryParameter("q", location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weatherList = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject responseJSON = new JSONObject(jsonData);
                JSONObject cityJSON = responseJSON.getJSONObject("city");
                String name = cityJSON.getString("name");
                JSONArray listJSON = responseJSON.getJSONArray("list");
                for (int i=0; i<listJSON.length(); i++) {
                    JSONObject weatherDetailedJSON = listJSON.getJSONObject(i);
                    JSONObject mainJSON = weatherDetailedJSON.getJSONObject("main");
                    Double tempMain = mainJSON.getDouble("temp");
                    Double tempMax = mainJSON.getDouble("temp_max");
                    Double tempMin = mainJSON.getDouble("temp_min");
                    Double pressure = mainJSON.getDouble("pressure");
                    Integer humidity = mainJSON.getInt("humidity");

                    JSONObject weatherJSON = weatherDetailedJSON.getJSONObject("weather");
                    String mainWeather = weatherJSON.getString("main");
                    String description = weatherJSON.getString("description");
                    String icon = "http://openweathermap.org/img/w/" + weatherJSON.getString("icon") + ".png";

                    JSONObject windJSON = weatherDetailedJSON.getJSONObject("wind");
                    Double speed = windJSON.getDouble("speed");

                    String dateTime = weatherDetailedJSON.getString("dt_txt");

                    Weather weather = new Weather(name, dateTime, tempMain, tempMax, tempMin, humidity, pressure, mainWeather, description, speed, icon);
                    weatherList.add(weather);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherList;
    }
}


