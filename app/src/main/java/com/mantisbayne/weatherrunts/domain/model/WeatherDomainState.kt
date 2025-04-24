package com.mantisbayne.weatherrunts.domain.model

sealed class WeatherDomainState {
    data class Success(
        val forecastList: List<ForecastDomainStateItem> = emptyList(),
        val feelsLike: Int = 0,
        val temperature: Int = 0,
        val precipitation: Int = 0
    ) : WeatherDomainState()

    data class Error(
        val errorMessage: String = ""
    ) : WeatherDomainState()
}
