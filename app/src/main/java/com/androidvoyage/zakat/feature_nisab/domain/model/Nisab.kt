package com.androidvoyage.zakat.feature_nisab.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "nisab")
@Parcelize
data class Nisab(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0L,
    var price: Long = 0L,
    var date: Long = 0L,
    var name: String = "",
    var type: String = "",
    var karat: String = "",
    var weight: String = ""
) : Parcelable

class InvalidNisabException(message: String): Exception(message)