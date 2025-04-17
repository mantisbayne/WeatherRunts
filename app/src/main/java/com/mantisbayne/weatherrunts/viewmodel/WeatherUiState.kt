package com.mantisbayne.weatherrunts.viewmodel

data class WeatherUiState(
    val error: Boolean = false,
    val loading: Boolean = false,
    val currentWeather: CurrentWeatherDisplayable = CurrentWeatherDisplayable(),
    val weatherList: WeatherListDisplayable = WeatherListDisplayable()
)
