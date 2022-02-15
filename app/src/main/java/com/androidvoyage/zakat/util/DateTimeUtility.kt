package com.androidvoyage.zakat.util

import android.text.TextUtils
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateTimeUtility {

    const val DD_MMM_YYYY = "dd MMM yyyy"
    const val D_MMM_YYYY = "d MMM yyyy"
    const val DD_MM_YYYY = "dd/MM/yyyy"
    const val DD_MM_YYYY_dash = "dd-MM-yyyy"
    const val DD_MMM_YYYY_2 = "dd-MMM-yyyy"
    const val DD_MM_YY_HH_MM_AA = "dd MMM ''yy hh:mm aa"
    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val EEE_D_MMM = "EEE, d MMM"
    const val EEE_DD_MM_YYYY = "EEE dd/MM/yyyy"
    const val MM_DD_YYYY_hh_mm_ss_a: String = "MM/dd/yyyy hh:mm:ss a"
    const val hh_mm_a: String = "hh:mm a"
    const val d_mmm_yy = "d MMM ''yy"
    const val DD_MM_YY = "d MMM ''yy"
    const val d_mmmm_yyyy: String = "d MMMM yyyy"
    const val dd_MM_yyyy: String = "dd-MM-yyyy"
    const val YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"
    const val yyyyMMdd: String = "yyyyMMdd"
    const val MMM_YY: String = "MMMM YY"

    @JvmStatic
    fun getFormattedDateTime(
        dateTime: String?,
        fromFormat: String?,
        toFormat: String?,
        toUTC: Boolean
    ): String? {

        if(TextUtils.isEmpty(dateTime)){
            return ""
        }

        var date: String? = null
        val format: DateFormat = SimpleDateFormat(fromFormat, Locale.getDefault())
        try {
            val d1 = format.parse(dateTime)
            val sdf = SimpleDateFormat(toFormat, Locale.getDefault())
            if (toUTC) {
                sdf.timeZone = TimeZone.getTimeZone("UTC")
            }
            date = sdf.format(d1)
        } catch (e: ParseException) {
            e.printStackTrace()
            date = dateTime
        }
        return date
    }

    @JvmStatic
    fun getDateTimeInLongFromString(
        dateTime: String?,
        fromFormat: String?
    ): Long? {
        val format: DateFormat = SimpleDateFormat(fromFormat, Locale.getDefault())
        try {
            val d1 = format.parse(dateTime)
            return d1.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }


    @JvmStatic
    fun convertDateToString(date: Date?, dateFormat: String?): String? {

        if (date == null) {
            return ""
        }

        var strDate = ""
        try {
            val fromFormat: DateFormat = SimpleDateFormat(dateFormat)
            fromFormat.isLenient = false
            strDate = fromFormat.format(Date(date.time))
        } catch (e : Exception) {
            LogUtils.printStacktrace(e)
        }

        return strDate
    }

    @JvmStatic
    fun convertDateToString(date: Long?, dateFormat: String?): String? {

        if (date == null) {
            return ""
        }

        var strDate = ""
        val fromFormat: DateFormat = SimpleDateFormat(dateFormat)
        fromFormat.isLenient = false
        strDate = fromFormat.format(date)

        return strDate
    }

    fun getDifferenceBetweenDates(endDate : Long) : ArrayList<Int> {
        val startDate = Date(Calendar.getInstance().timeInMillis)
        var secs: Long = (endDate - startDate.time) / 1000
        val hours = (secs / 3600).toInt()
        secs %= 3600
        val min = (secs / 60).toInt()
//        secs %= 60
        val list = ArrayList<Int>()
        list.add(hours)
        list.add(min)

        return list
    }

}
