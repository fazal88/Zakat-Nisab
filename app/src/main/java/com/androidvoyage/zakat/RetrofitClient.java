package com.androidvoyage.zakat;


import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://metals-api.com";
    public static final int CONNECTION_TIMEOUT = 300;
    public static final int SOCKET_TIMEOUT = 300;
    public static HttpLoggingInterceptor logging;

    public static Retrofit restAdapter;

    static {
        setupRestClient();
    }

    private static void setupRestClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS);

        long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
        File httpCacheDir = ZakatApp.Companion.getInstance().getExternalCacheDir();
        Cache cache = null;
        try {
            if (httpCacheDir != null) {
                cache = new Cache(httpCacheDir, httpCacheSize);
            }
        } catch (Throwable t) {
            cache = null;
        }
        if (cache != null) {
            httpClient.cache(cache);
        }
        //**//**//**//** Interceptors (Order is important) **//**//**//**//*
        // For debugging
        if (BuildConfig.DEBUG) {
            // Logging Interceptor (Add this as the last interceptor)
            logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(logging);
            //httpClient.addInterceptor(new ChuckInterceptor(ZakatApp.Companion.getInstance()));
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build());

        restAdapter = builder.build();
    }
}
