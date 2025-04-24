package com.mantisbayne.weatherrunts.presentation.model

data class WeatherUiState(
    val error: String = "",
    val loading: Boolean = false,
    val currentWeather: CurrentWeatherDisplayable = CurrentWeatherDisplayable(),
    val weatherList: WeatherListDisplayable = WeatherListDisplayable()
)
