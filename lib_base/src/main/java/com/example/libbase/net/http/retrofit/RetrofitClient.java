/*
 * SuperNovaFramework
 * RetrofitClient
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http.retrofit;

import android.util.Log;

import com.example.libbase.net.http.SNNetworkCenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitClient
 *
 * @author leo
 * @date 2017/12/21
 */
public class RetrofitClient {
    private static final String TAG = "RetrofitClient";

    private final static long CONNECT_TIME_OUT = 10;
    private final static long WRITE_TIME_OUT = 10;
    private final static long READ_TIME_OUT = 10;

    private static RetrofitClient instance;

    private static OkHttpClient okHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.e(TAG, "okHttp:" + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        Map<String, String> headers = new HashMap<>();

        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(new BaseInterceptor(headers))
                .build();
    }

    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SNNetworkCenter.getInstance().getServerUrl())
                .build();
    }

    public static RetrofitClient get() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public static void reset() {
        if (instance != null) {
            instance = null;
        }
    }
}
