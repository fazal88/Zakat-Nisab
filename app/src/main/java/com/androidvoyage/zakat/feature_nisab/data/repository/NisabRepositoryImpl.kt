package com.androidvoyage.zakat.feature_nisab.data.repository

import com.androidvoyage.zakat.feature_nisab.data.data_source.NisabDao
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.repository.NisabRepository
import kotlinx.coroutines.flow.Flow

class NisabRepositoryImpl(
    private val dao: NisabDao
) : NisabRepository {

    override fun getNotes(): Flow<List<Nisab>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Long): Nisab? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Nisab) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Nisab) {
        dao.deleteNote(note)
    }
}