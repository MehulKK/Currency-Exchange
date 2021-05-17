package com.macv.currencyconverter.ui.activity

import android.content.SharedPreferences
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.macv.currencyconverter.R
import com.macv.currencyconverter.base.BaseAppCompatActivity
import com.macv.currencyconverter.databinding.ActivityConversionBinding
import com.macv.currencyconverter.model.data.CurrencyRate
import com.macv.currencyconverter.ui.adapter.ConversionAdapter
import com.macv.currencyconverter.utils.extension.*
import com.macv.currencyconverter.utils.pref.LongPreference
import com.macv.currencyconverter.viewmodel.ConversionViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class ConversionActivity : BaseAppCompatActivity<ActivityConversionBinding, ConversionViewModel>(), View.OnClickListener {
    companion object{
        const val DEFAULT_COUNTRY = "USD"
    }

    private val prefs: Lazy<SharedPreferences> = lazy { this.applicationContext.getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE) }
    private var prefTime by LongPreference(prefs, PREF_TIME, 0)

    override val viewModel: ConversionViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.activity_conversion

    private val conversionAdapter = ConversionAdapter()
    private lateinit var currencyAdapter : ArrayAdapter<String>

    override fun initialize() {
        super.initialize()
        binding.clickHandler = this

        binding.rvCurrencyRate.apply {
            adapter = conversionAdapter
            addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        }

        if(prefTime.isTimeExpire()){
            Timber.e("Time expire")
            viewModel.loadCurrencyRate()
        }else{
            Timber.e("Time not expire : load data from local")
            viewModel.fetchCurrencyData()
        }

        binding.txtCurrency.setOnItemClickListener { _, _, position, _ ->
            viewModel.selectedCurrency.value = currencyAdapter.getItem(position) ?: ""
        }
    }

    override fun initializeObservers(viewModel: ConversionViewModel) {
        super.initializeObservers(viewModel)

        binding.shimmer.startShimmer()

        viewModel.showShimmer.observe(this) {
            if (it) {
                binding.shimmer.startShimmer()
            } else {
                binding.shimmer.stopShimmer()
            }
        }

        viewModel.onCurrencyList.observeEvent(this) { response ->
            val currencyList = response.currencies
            currencyAdapter = ArrayAdapter(this, R.layout.item_conversion, currencyList.toList().map { it.first})
            binding.txtCurrency.setAdapter(currencyAdapter)
        }

        viewModel.onCurrencyRateList.observeEvent(this) { response ->
            if(prefTime.isTimeExpire())
                prefTime = viewModel.timeStamp
            conversionAdapter.addAllItem(response)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnConversion -> {
                hideKeyBoard()
                val usdRate = viewModel.currencyRateLocal.value?.find { it.currencyName.removePrefix() == DEFAULT_COUNTRY }?.currencyRate
                val selectedCurrencyRate = viewModel.currencyRateLocal.value?.find { it.currencyName.removePrefix() == viewModel.selectedCurrency.value }?.currencyRate
                val ratioDifference = usdRate?.div(selectedCurrencyRate!!)

                viewModel.amount.value?.let {
                    if(it.isEmpty())
                        return
                }
                viewModel.currencyRateLocal.value?.map {
                    CurrencyRate(
                        0,
                        it.currencyName,
                        (it.currencyRate * (ratioDifference
                            ?: 1.0) * (viewModel.amount.value?.toDouble()
                            ?: 1.0).getDecimalFormat())
                    )
                }?.let { conversionAdapter.setList(it) }

                Timber.e("usd %s", usdRate)
                Timber.e("selectedCurrencyRate %s", selectedCurrencyRate)
                Timber.e("ratioDifference %s", ratioDifference)
            }

            R.id.inputLayoutSelectCurrency -> hideKeyBoard()
        }
    }
}
