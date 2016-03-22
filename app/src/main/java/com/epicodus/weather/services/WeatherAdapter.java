package com.epicodus.weather.services;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.epicodus.weather.R;
import com.epicodus.weather.model.Weather;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/21/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>{
    private ArrayList<Weather> weatherList = new ArrayList<>();
    private Context context;
    private final LayoutInflater inflater;

    public WeatherAdapter (Context context, ArrayList<Weather> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(weatherList.get(position));
        holder.dayRecycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        holder.dayRecycleView.setAdapter(new DayAdapter(context, weatherList));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dayTextView) TextView dayTextView;
        @Bind(R.id.dayRecyclerView) RecyclerView dayRecycleView;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindWeather (Weather weather) {
            dayTextView.setText (weather.getDate());
        }
    }
}
