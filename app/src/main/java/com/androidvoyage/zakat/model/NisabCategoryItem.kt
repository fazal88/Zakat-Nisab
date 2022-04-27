package com.androidvoyage.zakat.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Fazal on 16/04/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */
@Entity(tableName = "category")
@Parcelize
data class NisabCategoryItem(
    @PrimaryKey
    val type : String,
    val totalValue : Double = 0.0,
    val count : String = "0",
    val lastUpdated : Long = System.currentTimeMillis(),
) : Parcelable
