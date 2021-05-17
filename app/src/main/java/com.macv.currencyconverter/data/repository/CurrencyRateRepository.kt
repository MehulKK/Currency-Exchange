package com.macv.currencyconverter.data.repository

import com.macv.currencyconverter.data.local.db.AppDatabase
import com.macv.currencyconverter.model.data.CurrencyRate
import com.macv.currencyconverter.model.response.CurrencyRateModel
import javax.inject.Inject
import javax.inject.Singleton

interface CurrencyRateRepository {
    /**
     *  insert currency rate in local database [CurrencyRateModel]
     */
    suspend fun insetCurrencyList(currencyRateModel: CurrencyRateModel)

    /**
     *  get all currency list List[CurrencyRate] from local databse
     */
    suspend fun getAllCurrencyRate(): List<CurrencyRate>
}

@Singleton
class CurrencyRateRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase): CurrencyRateRepository {

    override suspend fun insetCurrencyList(currencyRateModel: CurrencyRateModel) {
        appDatabase.currencyRateDao().insertAllCurrencyRate(currencyRateModel.quotes.toList().map {
            CurrencyRate(
                0,
                it.first,
                it.second.toString().toDouble()
            )
        })
    }

    override suspend fun getAllCurrencyRate(): List<CurrencyRate> {
        return appDatabase.currencyRateDao().getAllCurrencyRate()
    }

}
