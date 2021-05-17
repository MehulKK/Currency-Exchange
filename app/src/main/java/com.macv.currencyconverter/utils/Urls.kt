package com.macv.currencyconverter.utils

/**
 * API URLs collection.
 */
object Urls {
    private const val BASE_DEV = "http://api.currencylayer.com/"
    private const val BASE_QA = "http://api.currencylayer.com/"
    private const val BASE_PRODUCTION = "http://api.currencylayer.com/"
    private const val ACCESS_KEY = "686b32eb8b1b7ca382a31296af07fdae"

    /**
     * Get Base URL for [flavor].
     */
    fun getBaseUrl(flavor: ProductFlavor.Flavor): String = when(flavor) {
        ProductFlavor.Flavor.DEV -> BASE_DEV
        ProductFlavor.Flavor.QA -> BASE_QA
        ProductFlavor.Flavor.PRODUCTION -> BASE_PRODUCTION
    }

    fun getAccessToken() : String = ACCESS_KEY
}
