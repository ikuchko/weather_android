package com.epicodus.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.weather.R;
import com.epicodus.weather.model.Weather;
import com.epicodus.weather.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity {
    private ArrayList<Weather> mWeatherList = new ArrayList<>();
    public static final String TAG = ForecastActivity.class.getSimpleName();
    @Bind(R.id.weatherListView) ListView weatherListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getForcast(location);
    }

    private void getForcast(String location) {
        final WeatherService weatherService = new WeatherService(this);

        weatherService.findForcast(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mWeatherList = weatherService.processResults(response);

                ForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       String[] weather = new String[mWeatherList.size()];
                        for (int i=0; i<weather.length; i++) {
                            weather[i] = mWeatherList.get(i).getDescription();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ForecastActivity.this, android.R.layout.simple_list_item_1, weather);
                        weatherListView.setAdapter(adapter);

                        Log.i(TAG, "Name: " + mWeatherList.get(0).getName());
                        Log.i(TAG, "Description: " + mWeatherList.get(0).getDescription());
                        Log.i(TAG, "Date: " + mWeatherList.get(0).getDate());
                        Log.i(TAG, "Icon: " + mWeatherList.get(0).getIcon());
                        Log.i(TAG, "MainWeather: " + mWeatherList.get(0).getMainWeather());
                        Log.i(TAG, "Humidity: " + mWeatherList.get(0).getHumidity());
                        Log.i(TAG, "Pressure: " + mWeatherList.get(0).getPressure());
                        Log.i(TAG, "TempMain: " + mWeatherList.get(0).getTempMain());
                        Log.i(TAG, "TempMax: " + mWeatherList.get(0).getTempMax());
                        Log.i(TAG, "TempMin: " + mWeatherList.get(0).getTempMin());
                        Log.i(TAG, "WindSpeed: " + mWeatherList.get(0).getWindSpeed());
                    }
                });
            }
        });
    }
}
