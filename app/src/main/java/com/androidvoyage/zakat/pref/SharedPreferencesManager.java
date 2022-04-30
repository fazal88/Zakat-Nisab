package com.androidvoyage.zakat.pref;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.androidvoyage.zakat.model.Features;

import javax.inject.Singleton;

/**
 * Created by Fazal on 14/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */


public class SharedPreferencesManager {
    private static final String SECRET_KEY = "ZAKAT";
    private static final String KEY_PREF_NAME = "SharedPreferencesManager";

    private static SharedPreferencesManager sharedPreferencesManager;
    private ObscuredSharedPreferences sSharedPreferenceEncrypted;
    private SharedPreferences sharedPreferences;

    @Singleton
    public static SharedPreferencesManager getInstance() {

        if (sharedPreferencesManager == null) {
            sharedPreferencesManager = new SharedPreferencesManager();
        }

        return sharedPreferencesManager;
    }

    public ObscuredSharedPreferences getSharedPreference() {
        return sSharedPreferenceEncrypted;
    }

    public void init(Application context) {
        sSharedPreferenceEncrypted = new ObscuredSharedPreferences(context, SECRET_KEY, context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE));
        sharedPreferences = context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setValue(String key,String value) {
        sSharedPreferenceEncrypted.edit().putString(key, value).commit();
    }

    public String getValue(String key) {
        return sSharedPreferenceEncrypted.getString(key, "");
    }

    public void setRate(String keyRate, String valueRate) {
        setValue(keyRate,valueRate);
    }

    public String getRate(String key) {
        return sSharedPreferenceEncrypted.getString(key, "");
    }

    public void setTotal( double totalOverAll) {
        sSharedPreferenceEncrypted.edit().putString(Features.PREF_OVER_ALL, totalOverAll + "").apply();
    }

    public String getTotal(){
        return sSharedPreferenceEncrypted.getString(Features.PREF_OVER_ALL, "0");
    }
}