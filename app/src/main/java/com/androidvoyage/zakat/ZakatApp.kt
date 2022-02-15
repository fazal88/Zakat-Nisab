package com.androidvoyage.zakat

import android.app.Application
import com.androidvoyage.zakat.pref.SharedPreferencesManager

/**
 * Created by Fazal on 09/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */
class ZakatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesManager.getInstance().init(this)
    }
}