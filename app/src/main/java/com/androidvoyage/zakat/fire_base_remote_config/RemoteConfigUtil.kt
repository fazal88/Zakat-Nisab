package com.androidvoyage.zakat.fire_base_remote_config

import com.androidvoyage.zakat.BuildConfig
import com.androidvoyage.zakat.R
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.paynearby.core.fire_base_remote_config.RemoteConfigKeys
import java.util.concurrent.TimeUnit

/*
*  Created by Fazal on 20/8/21.
*  Copyright (c) 2021 PayNearBy. All rights reserved.
* */
object RemoteConfigUtil {

    fun initFirebaseRemoteConfig() {
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder().build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        var cacheExpiration = TimeUnit.HOURS.toSeconds(12).toInt()
        if (BuildConfig.DEBUG) {
            cacheExpiration = 0
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration.toLong())
            .addOnCompleteListener { task: Task<Void?> ->
                if (task.isSuccessful) {
                    mFirebaseRemoteConfig.activate()
                }
            }
    }


    fun getRemoteConfigMessage():String{
        return FirebaseRemoteConfig.getInstance().getString(RemoteConfigKeys.KEY_RC_MESSAGE)
    }

    fun getLatestAppVersion():String{
        return FirebaseRemoteConfig.getInstance().getString(RemoteConfigKeys.KEY_RC_IN_APP_UPDATE_VERSION)
    }
}