package com.macv.currencyconverter.ui.activity

import androidx.activity.viewModels
import com.macv.currencyconverter.R
import com.macv.currencyconverter.base.BaseAppCompatActivity
import com.macv.currencyconverter.databinding.ActivitySplashBinding
import com.macv.currencyconverter.utils.extension.launchActivity
import com.macv.currencyconverter.utils.extension.observeEvent
import com.macv.currencyconverter.viewmodel.SplashViewModel
import com.macv.currencyconverter.viewmodel.SplashViewModel.Destination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseAppCompatActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initializeObservers(viewModel: SplashViewModel) {
        super.initializeObservers(viewModel)

        viewModel.goToScreen.observeEvent(this) { destination ->
            when (destination) {
                Destination.Home -> openHomeScreen()
                Destination.Login -> openLoginScreen()
                Destination.Sample -> openSampleScreen()
            }
        }
    }

    private fun openLoginScreen() {
        // TODO : Open Login screen
    }

    private fun openHomeScreen() {
        // TODO : Open Home screen
    }

    private fun openSampleScreen() {
        launchActivity<ConversionActivity>()
    }
}
