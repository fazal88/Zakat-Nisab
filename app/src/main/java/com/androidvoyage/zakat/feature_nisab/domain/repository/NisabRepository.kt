package com.androidvoyage.zakat.feature_nisab.domain.repository

import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import kotlinx.coroutines.flow.Flow

interface NisabRepository {

    fun getNotes(): Flow<List<Nisab>>

    suspend fun getNoteById(id: Int): Nisab?

    suspend fun insertNote(note: Nisab)

    suspend fun deleteNote(note: Nisab)
}