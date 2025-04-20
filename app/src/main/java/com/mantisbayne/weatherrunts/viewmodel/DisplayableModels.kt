package com.mantisbayne.weatherrunts.viewmodel

data class CurrentWeatherDisplayable(
    val feelsLike: String = "",
    val temperature: String = ""
)

data class WeatherListDisplayable(
    val items: List<ForecastDisplayable> = emptyList()
)

// TODO image
data class ForecastDisplayable(
    val time: String = "",
    val temperature: String = ""
)
