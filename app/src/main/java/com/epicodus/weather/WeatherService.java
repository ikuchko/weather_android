package com.epicodus.weather;

import android.content.Context;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://http://api.openweathermap.org/data/2.5/forecast?APPID=" + CONSUMER_KEY).newBuilder();
        urlBuilder.addQueryParameter("q", location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
