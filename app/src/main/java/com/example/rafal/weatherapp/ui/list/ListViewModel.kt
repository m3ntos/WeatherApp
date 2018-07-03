package com.example.rafal.weatherapp.ui.list

import android.arch.lifecycle.*
import android.util.Log
import com.example.rafal.weatherapp.app
import com.example.rafal.weatherapp.data.WeatherRepository
import java.util.*
import kotlin.concurrent.schedule

class ListViewModel(
    private val repo: WeatherRepository = app().weatherRepo
) : ViewModel() {

    val viewData = MediatorLiveData<ListViewData>()

    private val initialViewData = ListViewData(Date(), listOf())

    init {
        viewData.postValue(initialViewData)
        //startTimeUpdates()

        repo.getWeatherEntries().observe {
            viewData.postValue(initialViewData.copy(items = it))
        }
    }

    private fun startTimeUpdates() {
        Timer().schedule(500, 1000) {
            viewData.postValue(initialViewData.copy(currentTime = Date()))
            Log.d("observe", "TIMER TICK")
        }
    }

    fun <S> MediatorLiveData<*>.addSourceNonNull(source: LiveData<S>, observer: (S) -> Unit) {
        this.addSource(source, { it?.let { observer(it) } })
    }

    fun <T> LiveData<T>.observe(observer: (T) -> Unit){
        viewData.addSourceNonNull(this, observer)
    }
}
