package com.example.rafal.weatherapp.ui.list

import com.example.rafal.weatherapp.data.WeatherEntry
import java.util.*

data class ListViewData(
    val currentTime: Date,
    val items: List<WeatherEntry>,
    val goToDetails: Action<GoToDetailsAction>? = null
)

data class GoToDetailsAction(val weatherEntryId: Int)

class Action<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun handle(handler: (T) -> Unit) {
        if (!hasBeenHandled) handler.invoke(content)
        hasBeenHandled = true
    }
}

fun <T> T.toAction(): Action<T> = Action(this)