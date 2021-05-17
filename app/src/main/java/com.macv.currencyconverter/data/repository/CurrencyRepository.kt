package com.macv.currencyconverter.data.repository

import com.macv.currencyconverter.data.remote.ApiService
import com.macv.currencyconverter.model.response.CurrencyListModel
import com.macv.currencyconverter.model.response.CurrencyRateModel
import com.macv.currencyconverter.utils.Urls
import com.macv.currencyconverter.utils.extension.response
import javax.inject.Inject
import javax.inject.Singleton

interface CurrencyRepository {

    /**
     *  load currency List [CurrencyListModel]
     */
    suspend fun loadCurrencyList(): CurrencyListModel

    /**
     *  load currency rate [CurrencyRateModel]
     */
    suspend fun loadCurrencyRate(): CurrencyRateModel
}

@Singleton
class CurrencyRepositoryImpl @Inject constructor(private val apiService: ApiService): CurrencyRepository {

    override suspend fun loadCurrencyList(): CurrencyListModel {
        return apiService.getCurrency(Urls.getAccessToken()).response()
    }

    override suspend fun loadCurrencyRate(): CurrencyRateModel {
        return apiService.getCurrencyRate(Urls.getAccessToken()).response()
    }
}
