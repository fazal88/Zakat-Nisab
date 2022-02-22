package com.androidvoyage.zakat.feature_nisab.domain.util

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

sealed class NisabOrder(val orderType: OrderType) {
    class Price(orderType: OrderType): NisabOrder(orderType)
    class Date(orderType: OrderType): NisabOrder(orderType)
    class Type(orderType: OrderType): NisabOrder(orderType)

    fun copy(orderType: OrderType): NisabOrder {
        return when(this) {
            is Price -> Price(orderType)
            is Date -> Date(orderType)
            is Type -> Type(orderType)
        }
    }
}
