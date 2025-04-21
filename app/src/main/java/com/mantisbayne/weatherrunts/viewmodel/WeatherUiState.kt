package com.mantisbayne.weatherrunts.viewmodel

data class WeatherUiState(
    val error: String = "",
    val loading: Boolean = false,
    val currentWeather: CurrentWeatherDisplayable = CurrentWeatherDisplayable(),
    val weatherList: WeatherListDisplayable = WeatherListDisplayable()
)
