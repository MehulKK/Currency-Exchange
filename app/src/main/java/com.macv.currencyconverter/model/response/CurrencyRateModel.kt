package com.macv.currencyconverter.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyRateModel(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("source")
    val source: String,
    @SerializedName("quotes")
    val quotes: Map<String, Double>,
)