package com.macv.currencyconverter.di

import com.macv.currencyconverter.data.repository.*
import com.macv.currencyconverter.utils.ResourceHelper
import com.macv.currencyconverter.utils.ResourceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app.
 * If they are singleton mark them as '@Singleton'.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class AppBindingModule {

    @Singleton
    @Binds
    abstract fun bindResourceHelper(impl: ResourceHelperImpl): ResourceHelper

    @Singleton
    @Binds
    abstract fun bindCurrencyRepository(impl: CurrencyRepositoryImpl): CurrencyRepository

    @Singleton
    @Binds
    abstract fun bindCurrencyRateRepository(impl: CurrencyRateRepositoryImpl) : CurrencyRateRepository
}
