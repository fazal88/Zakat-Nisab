package com.androidvoyage.zakat.feature_nisab.presentation.all_nisab

import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.util.NisabOrder

sealed class NisabEvent {
    data class Order(val noteOrder: NisabOrder): NisabEvent()
    data class DeleteNisab(val note: Nisab): NisabEvent()
    object RestoreNisab: NisabEvent()
    object ToggleOrderSection: NisabEvent()
}
