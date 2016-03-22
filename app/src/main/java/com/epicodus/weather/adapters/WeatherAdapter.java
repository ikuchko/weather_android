package com.epicodus.weather.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private ArrayList<String> dayOfWeekList = Weather.getDayOfWeekList();
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
        holder.bindWeather(dayOfWeekList.get(position));

        ArrayList<Weather> hourWeatherList = new ArrayList<>();
        for (int i=0; i<weatherList.size(); i++) {
            if (weatherList.get(i).getmDayOfWeek().equals(dayOfWeekList.get(position))) {
                hourWeatherList.add(weatherList.get(i));
            }
        }

            holder.dayRecycleView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        holder.dayRecycleView.setAdapter(new DayAdapter(context, hourWeatherList));
    }

    @Override
    public int getItemCount() {
        return dayOfWeekList.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dayTextView) TextView dayTextView;
        @Bind(R.id.dayRecyclerView) RecyclerView dayRecycleView;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindWeather (String dayOfWeek) {
            dayTextView.setText (dayOfWeek);
        }
    }
}
