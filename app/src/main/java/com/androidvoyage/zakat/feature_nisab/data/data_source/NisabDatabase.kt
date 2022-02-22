package com.androidvoyage.zakat.feature_nisab.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab

@Database(
    entities = [Nisab::class],
    version = 1
)
abstract class NisabDatabase: RoomDatabase() {

    abstract val nisabDao: NisabDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}