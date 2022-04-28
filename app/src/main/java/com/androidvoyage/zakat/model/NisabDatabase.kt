package com.androidvoyage.zakat.model

import android.content.Context
import androidx.room.*

/**
 * Created by Fazal on 16/02/22.
 * Copyright (c) 2022 PayNearby. All rights reserved.
 */
@Database(entities = [NisabItem::class,NisabCategoryItem::class], version = 1)
@TypeConverters(Converters::class)
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