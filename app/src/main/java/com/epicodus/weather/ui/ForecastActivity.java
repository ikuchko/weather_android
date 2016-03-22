package com.epicodus.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.weather.R;
import com.epicodus.weather.model.CurrentWeather;
import com.epicodus.weather.model.Weather;
import com.epicodus.weather.adapters.WeatherAdapter;
import com.epicodus.weather.services.WeatherService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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

    @Bind(R.id.currentWeatherImageView) ImageView iconImageView;
    @Bind(R.id.temperatureTextView) TextView temperatureTextView;
    @Bind(R.id.locationTextView) TextView locationTextView;
    @Bind(R.id.descriptionTextView) TextView descriptionTextView;
    @Bind(R.id.humidityTextView) TextView humidityTextView;
    @Bind(R.id.pressureTextView) TextView pressureTextView;
    @Bind(R.id.windTextView) TextView windTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        getCurrentForcast(location);
        getForcast(location);
    }

    private void getForcast(final String location) {
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

    private void getCurrentForcast(final String location) {
        final WeatherService weatherService = new WeatherService(this);

        weatherService.findCurrentWeather(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final CurrentWeather mCurrentWeather = weatherService.processCurrentWeatherResults(response);

                ForecastActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Integer iconId = mCurrentWeather.getImageId();
                        Picasso
                                .with(mCurrentWeather.getmContext())
                                .load(iconId)
                                .fit() // will explain later
                                .into(iconImageView);

                        String test = mCurrentWeather.getmTempMain();
                        temperatureTextView.setText(mCurrentWeather.getmTempMain());
                        locationTextView.setText(mCurrentWeather.getmName());
                        descriptionTextView.setText(mCurrentWeather.getmDescription());
                        humidityTextView.setText(mCurrentWeather.getmHumidity());
                        pressureTextView.setText(mCurrentWeather.getmPressure());
                        windTextView.setText(mCurrentWeather.getmWindSpeed());

                    }
                });
            }
        });
    }
}
