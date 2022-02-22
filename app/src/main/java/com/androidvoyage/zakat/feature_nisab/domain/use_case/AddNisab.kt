package com.androidvoyage.zakat.feature_nisab.domain.use_case

import com.androidvoyage.zakat.feature_nisab.domain.model.InvalidNisabException
import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.repository.NisabRepository


class AddNisab(
    private val repository: NisabRepository
) {

    @Throws(InvalidNisabException::class)
    suspend operator fun invoke(note: Nisab) {
        if(note.name.isBlank()) {
            throw InvalidNisabException("The title of the note can't be empty.")
        }
        if(note.price.equals(0)) {
            throw InvalidNisabException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}