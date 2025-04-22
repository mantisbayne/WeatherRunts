package com.mantisbayne.weatherrunts.reducer

import com.mantisbayne.weatherrunts.dateutils.DateUtils
import com.mantisbayne.weatherrunts.domain.WeatherDomainState
import com.mantisbayne.weatherrunts.viewmodel.CurrentWeatherDisplayable
import com.mantisbayne.weatherrunts.viewmodel.ForecastDisplayable
import com.mantisbayne.weatherrunts.viewmodel.WeatherListDisplayable
import com.mantisbayne.weatherrunts.viewmodel.WeatherUiState
import javax.inject.Inject

class WeatherUiStateReducer @Inject constructor() {

    fun reduce(domainState: WeatherDomainState): WeatherUiState = when {
        domainState.error != null -> WeatherUiState(error = errorMessage(domainState.error))
        else -> WeatherUiState(
            currentWeather = currentWeather(domainState.feelsLike, domainState.temperature),
            weatherList = weatherList(domainState.forecastList)
        )
    }

    // TODO string resources
    private fun errorMessage(error: String?) = error ?: "An error occurred, please try again"

    private fun currentWeather(feelsLike: Int?, temperature: Int?) =
        CurrentWeatherDisplayable(
            feelsLikeText(feelsLike),
            temperature(temperature),
            DateUtils.formatToLocalTime(shouldShowDayOfWeek = true)
        )

    private fun feelsLikeText(feelsLike: Int?) = feelsLike?.let {
        "Feels like $it°F"
    } ?: "Unable to get feels like temperature, try again later"

    private fun temperature(temperature: Int?) = temperature?.let {
        "$it°F"
    } ?: "Unable to get current temperature, try again later"

    private fun weatherList(forecastList: List<Int>) =
        WeatherListDisplayable(
            items = forecastList.map {
                ForecastDisplayable(
                    time = DateUtils.formatToLocalTime(
                        utcDateString = "2025-04-21T15:00"
                    ),
                    temperature = "$it°F"
                )
            }
        )
}