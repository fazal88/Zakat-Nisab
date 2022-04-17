package com.androidvoyage.zakat.model

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Fazal on 16/02/22.
 * Copyright (c) 2022 Fazal. All rights reserved.
 */


@Dao
interface NisabDao {

    @Insert
    suspend fun insertNisab(item : NisabItem)

    @Update
    suspend fun updateNisab(item : NisabItem)

    @Delete
    suspend fun deleteNisab(item : NisabItem)

    @Query("SELECT * FROM nisab")
    fun getNisabs() : LiveData<List<NisabItem>>

    @Query("SELECT * FROM nisab WHERE type IN (:nisabType)")
    fun getNisabs(nisabType : String) : LiveData<List<NisabItem>>
}