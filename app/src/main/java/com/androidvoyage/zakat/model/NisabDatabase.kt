package com.androidvoyage.zakat.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Fazal on 16/02/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */
@Database(entities = [NisabItem::class,NisabCategoryItem::class,NisabRateItem::class], version = 1)
abstract class NisabDatabase : RoomDatabase() {

    abstract fun nisabDao(): NisabDao

    companion object {

        @Volatile
        private var INSTANCE: NisabDatabase? = null

        fun getDatabase(context: Context): NisabDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NisabDatabase::class.java,
                        "nisabDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}