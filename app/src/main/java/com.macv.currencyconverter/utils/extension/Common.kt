package com.macv.currencyconverter.utils.extension

import com.macv.currencyconverter.ui.activity.ConversionActivity.Companion.DEFAULT_COUNTRY
import timber.log.Timber
import java.sql.Timestamp
import java.text.DecimalFormat
import java.util.*

const val PREF_TIME = "PREF_TIME"
const val TIME_EXPIRE_MINUTES = 30

/**
 *  Remove Prefix from currency name
 */
fun String.removePrefix(): String {
    return this.replaceFirst(DEFAULT_COUNTRY, "")
}

/**
 *  Precise with two decimal place
 */
fun Double.getDecimalFormat(): Double {
    return DecimalFormat("#.##").format(this).toDouble()
}

/**
 *  Get current timestamp
 */
fun getCurrentTimeStamp() : Long {
    return Date().time / 1000
}

/**
 *  Check if time is expire with 30 minutes
 */
fun Long.isTimeExpire(): Boolean {
    return if(this == 0L)
        true
    else {
        val currentTime = Timestamp(Date().time / 1000)
        val existingTime = Timestamp(this)
        val timeDiffMinutes = (((currentTime.time - existingTime.time) ) % 3600) / 60

        Timber.e("current time : $currentTime")
        Timber.e("Existing time : $existingTime")
        Timber.e("Diff time :%s", timeDiffMinutes)
        timeDiffMinutes >= TIME_EXPIRE_MINUTES
    }
}