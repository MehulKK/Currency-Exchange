package com.macv.currencyconverter.di

import com.macv.currencyconverter.data.local.pref.FlavorPreferences
import com.macv.currencyconverter.data.local.pref.FlavorPreferencesImpl
import com.macv.currencyconverter.data.repository.FlavorRepository
import com.macv.currencyconverter.data.repository.FlavorRepositoryImpl
import com.macv.currencyconverter.ui.delegate.FlavorDelegate
import com.macv.currencyconverter.ui.delegate.FlavorDelegateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Defines all the classes that need to be provided in the scope of the app.
 * If they are singleton mark them as '@Singleton'.
 *
 * NOTE : This module should only be used for app's flavor.
 */
@Module
@InstallIn(ApplicationComponent::class)
object FlavorModule {

    @Singleton
    @Provides
    fun provideFlavorPreferences(flavorPreferencesImpl: FlavorPreferencesImpl): FlavorPreferences = flavorPreferencesImpl

    @Singleton
    @Provides
    fun provideFlavorRepository(flavorRepositoryImpl: FlavorRepositoryImpl): FlavorRepository = flavorRepositoryImpl

    @Singleton
    @Provides
    fun provideFlavorDelegate(flavorDelegateImpl: FlavorDelegateImpl): FlavorDelegate = flavorDelegateImpl
}
