package com.macv.currencyconverter.data.remote

import com.macv.currencyconverter.model.response.CurrencyListModel
import com.macv.currencyconverter.model.response.CurrencyRateModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Provides remote APIs
 */
interface ApiService {
    @GET("list")
    suspend fun getCurrency(@Query("access_key") access_key: String): Response<CurrencyListModel>

    @GET("live")
    suspend fun getCurrencyRate(@Query("access_key") access_key: String): Response<CurrencyRateModel>
}
