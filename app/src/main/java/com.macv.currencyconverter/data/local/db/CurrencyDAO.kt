package com.macv.currencyconverter.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.macv.currencyconverter.model.data.CurrencyRate


@Dao
interface CurrencyDAO {

    @Query("SELECT * FROM CurrencyRate")
    fun getAllCurrencyRate() : List<CurrencyRate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCurrencyRate(order: List<CurrencyRate>)
}