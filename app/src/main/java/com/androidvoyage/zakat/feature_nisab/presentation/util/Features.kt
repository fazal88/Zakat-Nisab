package com.androidvoyage.zakat.feature_nisab.presentation.util

import androidx.compose.ui.graphics.Color
import com.androidvoyage.zakat.R
import com.androidvoyage.zakat.ui.theme.*

object Features {

    const val OUNCE_TO_GRAM : Float = 0.0353F

    const val PREF_CASH_IN_HAND: String = "Cash in Hand"
    const val PREF_GOLD_SILVER: String = "Gold & Silver"
    const val PREF_SAVINGS_FUNDS: String = "Savings & Funds"
    const val PREF_BUSINESS_ASSETS: String = "Business Assets"
    const val PREF_DEBT_OWNED: String = "Debts Owed"
    const val PREF_SHARES_STOCKS: String = "Shares & Stocks"
    const val PREF_PROPERTIES_CARS: String = "Properties & Cars"

    const val PREF_24_K: String = "24 Karat or 999"
    const val PREF_22_K: String = "22 Karat or 916"
    const val PREF_18_K: String = "18 Karat or 750"
    const val PREF_14_K: String = "14 Karat or 583"
    const val PREF_23_KDM: String = "23 KDM or 18 Karat"
    const val PREF_SILVER: String = "Silver"

    val prefTitleList = listOf(
        PREF_CASH_IN_HAND,
        PREF_GOLD_SILVER,
        PREF_SAVINGS_FUNDS,
        PREF_BUSINESS_ASSETS,
        PREF_DEBT_OWNED,
        PREF_SHARES_STOCKS,
        PREF_PROPERTIES_CARS
    )

    val prefKaratList = listOf(
        PREF_24_K,
        PREF_22_K,
        PREF_18_K,
        PREF_14_K,
        PREF_23_KDM
    )

    fun getIcon(key: String): Int {
        return when (key) {
            PREF_CASH_IN_HAND -> {
                R.drawable.ic_cash
            }
            PREF_GOLD_SILVER -> {
                R.drawable.ic_gold
            }
            PREF_SAVINGS_FUNDS -> {
                R.drawable.ic_assets
            }
            PREF_BUSINESS_ASSETS -> {
                R.drawable.ic_stocks
            }
            PREF_DEBT_OWNED -> {
                R.drawable.ic_debt
            }
            PREF_SHARES_STOCKS -> {
                R.drawable.ic_invest
            }
            PREF_PROPERTIES_CARS -> {
                R.drawable.ic_asset
            }
            else -> {
                R.drawable.ic_zakat
            }
        }
    }

    fun getColor(key : String) : Color {
        return when (key) {
            PREF_CASH_IN_HAND -> {
                Beige3
            }
            PREF_GOLD_SILVER -> {
                Blue3
            }
            PREF_SAVINGS_FUNDS -> {
                Pink3
            }
            PREF_BUSINESS_ASSETS -> {
                LightGreen3
            }
            PREF_DEBT_OWNED -> {
                OrangeYellow3
            }
            PREF_SHARES_STOCKS -> {
                BlueViolet3
            }
            PREF_PROPERTIES_CARS -> {
                Violet3
            }
            else -> {
                ColorBlack
            }
        }
    }


}