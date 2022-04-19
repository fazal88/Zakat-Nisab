package com.androidvoyage.zakat.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Fazal on 16/04/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */
@Entity(tableName = "rates")
@Parcelize
data class NisabRateItem(
    @PrimaryKey
    val purity : String,
    val karat24 : Double = 0.0,
    val karat22 : Double = 0.0,
    val karat18 : Double = 0.0,
    val karat14 : Double = 0.0,
    val kdm23 : Double = 0.0,
    val silver : Double = 0.0,
    val lastUpdated : Long = System.currentTimeMillis(),
) : Parcelable
