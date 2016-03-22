package com.epicodus.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.weather.R;
import com.epicodus.weather.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 3/21/16.
 */
public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder>{
    private ArrayList<Weather> dayWeatherList = new ArrayList<>();
    private Context context;

    public DayAdapter (Context context, ArrayList<Weather> dayWeatherList) {
        this.context = context;
        this.dayWeatherList = dayWeatherList;
    }

    @Override
    public DayAdapter.DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_list_item, parent, false);
        DayViewHolder viewHolder = new DayViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DayAdapter.DayViewHolder holder, int position) {
        holder.bindWeather(dayWeatherList.get(position));
    }

    @Override
    public int getItemCount() {
        return dayWeatherList.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.hourTextView) TextView hourTextView;
        @Bind(R.id.iconImageView) ImageView iconImageView;
        @Bind(R.id.temperatureTextView) TextView temperatureTextView;

        public DayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindWeather (Weather weather) {
            hourTextView.setText(weather.getDate());
            temperatureTextView.setText(weather.getTempMain().toString());
            Integer iconId = weather.getImageId();
            Picasso
                .with(context)
                .load(iconId)
                .fit() // will explain later
                .into(iconImageView);
        }
    }
}