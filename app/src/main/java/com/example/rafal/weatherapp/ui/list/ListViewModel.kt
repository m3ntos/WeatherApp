package com.example.rafal.weatherapp.ui.list

import com.example.rafal.weatherapp.app
import com.example.rafal.weatherapp.data.WeatherRepository
import com.example.rafal.weatherapp.framework.BaseViewModel
import java.util.*

class ListViewModel(
    private val repo: WeatherRepository = app().weatherRepo
) : BaseViewModel<ListViewData>(), ForecastAdapter.ForecastAdapterOnItemClickHandler {

    override val initialViewData = ListViewData(Date(), listOf())

    init {
        repo.getWeatherEntries().observe {
            updateViewData(viewData.copy(items = it))
        }
    }

    override fun onItemClick(weatherEntryId: Int) {
        updateViewData(viewData.copy(goToDetails = GoToDetailsAction(weatherEntryId).toAction()))
    }
}

