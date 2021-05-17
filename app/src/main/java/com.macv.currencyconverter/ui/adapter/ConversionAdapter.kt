package com.macv.currencyconverter.ui.adapter

import android.view.View
import com.macv.currencyconverter.R
import com.macv.currencyconverter.base.BaseRecyclerAdapter
import com.macv.currencyconverter.model.data.CurrencyRate

class ConversionAdapter : BaseRecyclerAdapter<CurrencyRate>() {
    override fun getLayoutIdForType(viewType: Int): Int = R.layout.item_conversion_rate

    override fun onItemClick(view: View?, adapterPosition: Int) {
        /* no-op */
    }

    override fun areItemsSame(oldItem: CurrencyRate, newItem: CurrencyRate): Boolean {
        return oldItem == newItem
    }

    override fun getLoaderItem(): CurrencyRate =
        CurrencyRate()
}
