package com.mantisbayne.weatherrunts.viewmodel

data class CurrentWeatherDisplayable(
    val feelsLike: String = "",
    val topText: String = "",
    val bottomText: String = ""
)

data class WeatherListDisplayable(
    val items: List<ForcastDisplayable> = emptyList()
)

// TODO image
data class ForcastDisplayable(
    val time: String = "",
    val feelsLike: String = ""
)
