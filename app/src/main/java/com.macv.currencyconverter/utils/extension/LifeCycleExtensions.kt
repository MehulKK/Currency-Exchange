package com.macv.currencyconverter.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.macv.currencyconverter.utils.result.Event
import com.macv.currencyconverter.utils.result.EventObserver

/**
 * Extension function for observing [LiveData]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 */
fun <T> LiveData<T>.observe(owner: LifecycleOwner, func: (T) -> Unit) =
    observe(owner, Observer { value ->
        value?.let {
            func(it)
        }
    })

/**
 * Extension function for observing [LiveData] containing [Event]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 */
fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, func: (T) -> Unit) =
    observe(owner, EventObserver {
        func(it)
    })
