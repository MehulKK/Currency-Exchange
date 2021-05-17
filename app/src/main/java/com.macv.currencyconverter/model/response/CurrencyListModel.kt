package com.macv.currencyconverter.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyListModel(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("currencies")
    val currencies: Map<String, String>,
)