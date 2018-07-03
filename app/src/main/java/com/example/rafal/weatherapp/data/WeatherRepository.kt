package com.example.rafal.weatherapp.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

class WeatherRepository {


    private val mockList = listOf(
        WeatherEntry(1, 15.0, 20.0, Date()),
        WeatherEntry(2, 10.0, 21.5, Date().addDays(1)),
        WeatherEntry(3, 9.5, 23.0, Date().addDays(2)),
        WeatherEntry(4, 17.0, 24.0, Date().addDays(3)),
        WeatherEntry(5, 12.0, 31.0, Date().addDays(4)),
        WeatherEntry(6, 11.0, 25.0, Date().addDays(5))
    )

    fun getWeatherEntries(): LiveData<List<WeatherEntry>> {
        val mockData = MutableLiveData<List<WeatherEntry>>()
        var i = 1

        Timer().schedule(500, 1000) {
            mockData.postValue(mockList.subList(0, i))
            if (i > mockList.lastIndex) this.cancel()
            i++
        }
        return mockData
    }

    private fun Date.addDays(days: Int): Date {
        Calendar.getInstance().let {
            it.time = this
            it.add(Calendar.DATE, 1)
            return it.time
        }
    }
}