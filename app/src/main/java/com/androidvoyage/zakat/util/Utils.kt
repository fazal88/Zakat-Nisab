package com.androidvoyage.zakat.util

import java.text.NumberFormat
import java.util.*

object Utils{
    fun getCurrencySymbol() : String{
        return "₹ "
    }

    fun getAmountWithCommas(subtitle: String): String {
        val myNumber = NumberFormat.getNumberInstance(Locale.UK)
            .format(subtitle.toInt())
        return myNumber
    }
}
