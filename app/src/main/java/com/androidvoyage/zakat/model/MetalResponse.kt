package com.androidvoyage.zakat.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Fazal on 17/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */


@Parcelize
data class MetalResponse(
    val success : Boolean,
    val timestamp : Long,
    val date : String,
    val base : String,
    val rates : MetalRates,
    val unit : String
):Parcelable


@Parcelize
data class MetalRates(
    val XAG : Double,
    val XAU : Double
):Parcelable