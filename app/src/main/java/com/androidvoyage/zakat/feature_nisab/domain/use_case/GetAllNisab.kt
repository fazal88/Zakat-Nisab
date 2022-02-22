package com.androidvoyage.zakat.feature_nisab.domain.use_case

import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.repository.NisabRepository
import com.androidvoyage.zakat.feature_nisab.domain.util.NisabOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNisab(
    private val repository: NisabRepository
) {

    operator fun invoke(
        noteOrder: NisabOrder = NisabOrder.Date(OrderType.Descending)
    ): Flow<List<Nisab>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NisabOrder.Price -> notes.sortedBy { it.price }
                        is NisabOrder.Date -> notes.sortedBy { it.date }
                        is NisabOrder.Type -> notes.sortedBy { it.type }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NisabOrder.Price -> notes.sortedByDescending { it.price }
                        is NisabOrder.Date -> notes.sortedByDescending { it.date }
                        is NisabOrder.Type -> notes.sortedByDescending { it.type }
                    }
                }
            }
        }
    }
}