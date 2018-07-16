package com.example.rafal.weatherapp.framework

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData

fun <T> MediatorLiveData<*>.addSourceNonNull(source: LiveData<T>, observer: (T) -> Unit) {
    this.addSource(source, { it?.let { observer(it) } })
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, android.arch.lifecycle.Observer {
        it?.let(observer)
    })
}

fun <T> MediatorLiveData<T>.initWith(initialValue: T) = apply { value = initialValue }
