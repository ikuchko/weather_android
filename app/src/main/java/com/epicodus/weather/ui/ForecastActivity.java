package com.epicodus.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.weather.R;
import com.epicodus.weather.model.Weather;
import com.epicodus.weather.adapters.WeatherAdapter;
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
    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    private WeatherAdapter mAdapter;

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

                        mAdapter = new WeatherAdapter(getApplicationContext(), mWeatherList);
                        recyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ForecastActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);

                    }
                });
            }
        });
    }
}
