package com.example.rafal.weatherapp

import com.example.rafal.weatherapp.data.WeatherRepository

// easy access to the component implementation
fun app() = AppComponent.instance

interface AppComponent {

    val weatherRepo: WeatherRepository


    // here we can do either
    // constructor injection
    // or
    // fields injection

    companion object {
        val instance: AppComponent = AppModule()
    }
}

class AppModule : AppComponent {

    override val weatherRepo by lazy { WeatherRepository() }
}