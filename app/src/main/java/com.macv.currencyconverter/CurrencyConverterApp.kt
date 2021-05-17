package com.macv.currencyconverter

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.macv.currencyconverter.utils.NetworkUtil
import com.macv.currencyconverter.utils.ProductFlavor
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CurrencyConverterApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initAppCenter()
        initTimber()
        observeNetwork()
    }

    private fun initAppCenter() {
        if (ProductFlavor.CURRENT == ProductFlavor.Flavor.DEV) {
            AppCenter.setLogLevel(Log.VERBOSE)
        }
    }

    private fun isFlavorProductionOrQA(): Boolean = ProductFlavor.CURRENT == ProductFlavor.Flavor.QA ||
            ProductFlavor.CURRENT == ProductFlavor.Flavor.PRODUCTION

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            // Show logs only when on debug
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
    * Observe network state.
    */
    private fun observeNetwork() {
        val cm: ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().build()

        cm.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    NetworkUtil.isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    NetworkUtil.isNetworkConnected = false
                }
            })
    }
}
