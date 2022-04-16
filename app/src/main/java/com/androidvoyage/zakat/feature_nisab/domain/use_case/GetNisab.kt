package com.androidvoyage.zakat.feature_nisab.domain.use_case

import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.repository.NisabRepository


class GetNisab(
    private val repository: NisabRepository
) {

    suspend operator fun invoke(id: Long): Nisab? {
        return repository.getNoteById(id)
    }
}