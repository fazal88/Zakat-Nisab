package com.androidvoyage.zakat.feature_nisab.presentation.all_nisab

import com.androidvoyage.zakat.feature_nisab.domain.model.Nisab
import com.androidvoyage.zakat.feature_nisab.domain.util.NisabOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType


data class NisabState(
    val nisabs: List<Nisab> = emptyList(),
    val nisabOrder: NisabOrder = NisabOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
