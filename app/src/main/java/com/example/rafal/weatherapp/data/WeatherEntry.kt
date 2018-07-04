package com.example.rafal.weatherapp.data

import android.support.annotation.DrawableRes
import java.util.*

class WeatherEntry(
    val id: Int,
    val minTemperature: Double,
    val maxTemperature: Double,
    val date: Date,
    val weatherConditionId: Int
)