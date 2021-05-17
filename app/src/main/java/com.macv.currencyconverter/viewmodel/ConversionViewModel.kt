package com.macv.currencyconverter.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.macv.currencyconverter.base.BaseViewModel
import com.macv.currencyconverter.data.repository.CurrencyRateRepository
import com.macv.currencyconverter.data.repository.CurrencyRepository
import com.macv.currencyconverter.model.response.CurrencyListModel
import com.macv.currencyconverter.model.data.CurrencyRate
import com.macv.currencyconverter.ui.activity.ConversionActivity.Companion.DEFAULT_COUNTRY
import com.macv.currencyconverter.utils.extension.getCurrentTimeStamp
import com.macv.currencyconverter.utils.result.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * ViewModel for [com.macv.currencyconverter.ui.activity.ConversionActivity]
 */
class ConversionViewModel @ViewModelInject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyRateRepository: CurrencyRateRepository
) : BaseViewModel() {

    var timeStamp: Long = 0
    val selectedCurrency = MutableLiveData<String>(DEFAULT_COUNTRY)
    val amount = MutableLiveData<String>("1.0")
    val currencyRateLocal = MutableLiveData<ArrayList<CurrencyRate>>()

    private val _onCurrencyList = MutableLiveData<Event<CurrencyListModel>>()
    val onCurrencyList: LiveData<Event<CurrencyListModel>> get() = _onCurrencyList

    private val _onCurrencyRateList = MutableLiveData<Event<ArrayList<CurrencyRate>>>()
    val onCurrencyRateList: LiveData<Event<ArrayList<CurrencyRate>>> get() = _onCurrencyRateList

    private val _showShimmer = MutableLiveData<Boolean>()
    val showShimmer: LiveData<Boolean> get() = _showShimmer


    init {
        loadCurrencyList()
    }

    /**
     *  load currency list from API
     */
    fun loadCurrencyList() {
        _showShimmer.value = true

        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                currencyRepository.loadCurrencyList()
            }.onSuccess {
                withContext(Dispatchers.Main) {
                    _onCurrencyList.value = Event(it)
                    _showShimmer.value = false
                }
            }.onFailure {
                Timber.e(it)
                withContext(Dispatchers.Main) {
                    _showShimmer.value = false
                }
            }
        }
    }

    /**
     *  load currency Rate from API
     */
    fun loadCurrencyRate() {
        _showShimmer.value = true

        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                currencyRepository.loadCurrencyRate()
            }.onSuccess {
                withContext(Dispatchers.Main) {
                    timeStamp = getCurrentTimeStamp()
                    currencyRateRepository.insetCurrencyList(it)
                    currencyRateLocal.value = ArrayList(currencyRateRepository.getAllCurrencyRate())
                    _onCurrencyRateList.value =
                        Event(ArrayList(currencyRateRepository.getAllCurrencyRate()))
                    _showShimmer.value = false
                }
            }.onFailure {
                Timber.e(it)
                withContext(Dispatchers.Main) {
                    _showShimmer.value = false
                }
            }
        }
    }

    /**
     *  Fetch currency rate from local database
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchCurrencyData() {
        viewModelScope.launch {
            currencyRateLocal.value = ArrayList(currencyRateRepository.getAllCurrencyRate())
            _onCurrencyRateList.value =
                Event(ArrayList(currencyRateRepository.getAllCurrencyRate()))
        }
    }
}
