package com.epicodus.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForecastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

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
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v("JSON DATA", jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
