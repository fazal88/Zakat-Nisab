package com.androidvoyage.zakat.model

import com.androidvoyage.zakat.R

object Features {
    private const val CASH_IN_HAND: String = "Cash in Hand"
    private const val GOLD_SILVER: String = "Gold + Silver"
    private const val SAVINGS_FUNDS: String = "Savings & Funds"
    private const val BUSINESS_ASSETS: String = "Business Assets"
    private const val DEBT_OWNED: String = "Debts Owed"
    private const val SHARES_STOCKS: String = "Shares & Stocks"
    private const val PROPERTIES_CARS: String = "Properties + Cars"

    private const val PREF_CASH_IN_HAND = "PREF_CASH_IN_HAND"
    private const val PREF_GOLD_SILVER = "PREF_GOLD_SILVER"
    private const val PREF_SAVINGS_FUNDS = "PREF_SAVINGS_FUNDS"
    private const val PREF_BUSINESS_ASSETS = "PREF_BUSINESS_ASSETS"
    private const val PREF_DEBT_OWNED = "PREF_DEBT_OWNED"
    private const val PREF_SHARES_STOCKS = "PREF_SHARES_STOCKS"
    private const val PREF_PROPERTIES_CARS = "PREF_PROPERTIES_CARS"

    val prefKeyList = listOf(
        PREF_CASH_IN_HAND,
        PREF_GOLD_SILVER,
        PREF_SAVINGS_FUNDS,
        PREF_BUSINESS_ASSETS,
        PREF_DEBT_OWNED,
        PREF_SHARES_STOCKS,
        PREF_PROPERTIES_CARS
    )

    fun getTitle(key : String) : String
    {
        return when (key) {
            PREF_CASH_IN_HAND -> {
                CASH_IN_HAND
            }
            PREF_GOLD_SILVER -> {
                GOLD_SILVER
            }
            PREF_SAVINGS_FUNDS -> {
                SAVINGS_FUNDS
            }
            PREF_BUSINESS_ASSETS -> {
                BUSINESS_ASSETS
            }
            PREF_DEBT_OWNED -> {
                DEBT_OWNED
            }
            PREF_SHARES_STOCKS -> {
                SHARES_STOCKS
            }
            PREF_PROPERTIES_CARS -> {
                PROPERTIES_CARS
            }
            else -> {
                ""
            }
        }
    }
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

    fun getColor(key : String) : Int{
        return when (key) {
            PREF_CASH_IN_HAND -> {
                R.color.BlueViolet3
            }
            PREF_GOLD_SILVER -> {
                R.color.LightGreen3
            }
            PREF_SAVINGS_FUNDS -> {
                R.color.Pink3
            }
            PREF_BUSINESS_ASSETS -> {
                R.color.Violet3
            }
            PREF_DEBT_OWNED -> {
                R.color.OrangeYellow3
            }
            PREF_SHARES_STOCKS -> {
                R.color.Blue3
            }
            PREF_PROPERTIES_CARS -> {
                R.color.Beige3
            }
            else -> {
                R.color.black
            }
        }
    }


}
