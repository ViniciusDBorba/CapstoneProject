package com.nanodegree.udacity.podcaps.utils;

import com.nanodegree.udacity.podcaps.BuildConfig;
import com.nanodegree.udacity.podcaps.data.services.FirebaseService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public static Retrofit getRetrofit(String baseURL) {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static FirebaseService getMoviesService(){
        return getRetrofit(BuildConfig.FIREBASE_URL).create(FirebaseService.class);
    }
}
