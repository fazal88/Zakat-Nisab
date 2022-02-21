package com.androidvoyage.zakat

import android.app.Application
import com.androidvoyage.zakat.fire_base_remote_config.RemoteConfigUtil
import com.androidvoyage.zakat.pref.SharedPreferencesManager

/**
 * Created by Fazal on 09/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */
class ZakatApp : Application() {

    companion object{
        private lateinit var mInstance: ZakatApp

        @Synchronized
        fun getInstance(): ZakatApp {
            return mInstance
        }
    }


    override fun onCreate() {
        super.onCreate()
        mInstance = this
        RemoteConfigUtil.initFirebaseRemoteConfig()
        SharedPreferencesManager.getInstance().init(this)
    }
}