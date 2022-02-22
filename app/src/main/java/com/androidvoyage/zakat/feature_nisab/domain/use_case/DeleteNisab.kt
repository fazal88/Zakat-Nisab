package com.androidvoyage.zakat.feature_nisab.domain.use_case

import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.repository.NisabRepository


class DeleteNisab(
    private val repository: NisabRepository
) {

    suspend operator fun invoke(note: Nisab) {
        repository.deleteNote(note)
    }
}