package com.androidvoyage.zakat.feature_nisab.data.data_source

import androidx.room.*
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import kotlinx.coroutines.flow.Flow

@Dao
interface NisabDao {

    @Query("SELECT * FROM nisab")
    fun getNotes(): Flow<List<Nisab>>

    @Query("SELECT * FROM nisab WHERE id = :id")
    suspend fun getNoteById(id: Long): Nisab?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Nisab)

    @Delete
    suspend fun deleteNote(note: Nisab)
}