package com.example.rafal.weatherapp.ui.list

import com.example.rafal.weatherapp.data.WeatherEntry
import java.util.*

data class ListViewData(
    val currentTime: Date,
    val items: List<WeatherEntry>
)