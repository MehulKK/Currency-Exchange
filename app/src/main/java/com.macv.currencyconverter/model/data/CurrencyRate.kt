package com.macv.currencyconverter.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyRate(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val currencyName: String = "",
        val currencyRate: Double = 0.0
)