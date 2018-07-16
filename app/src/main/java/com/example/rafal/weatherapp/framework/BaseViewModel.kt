package com.example.rafal.weatherapp.framework

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    protected abstract val initialViewData: T
    protected val liveViewData by lazy { MediatorLiveData<T>().initWith(initialViewData) }
    protected val viewData: T get() = liveViewData.value!!

    fun getViewData(): LiveData<T> = liveViewData

    protected fun updateViewData(newViewData: T) {
        liveViewData.value = newViewData
    }

    fun <R> LiveData<R>.observe(observer: (R) -> Unit) {
        liveViewData.addSourceNonNull(this, observer)
    }
}