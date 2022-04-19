package com.androidvoyage.zakat.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Fazal on 16/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */

@Entity(tableName = "nisab")
@Parcelize
data class NisabItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var datePurchase: Long = System.currentTimeMillis(),
    var name: String = "",
    var type: String = "",
    var purity: String = "",
    var weight: String = "",
    var price: String = "",
    var estimatedValue: String = ""
) : Parcelable