package com.mantisbayne.weatherrunts.presentation.reducer

import com.mantisbayne.weatherrunts.domain.model.ForecastDomainStateItem
import com.mantisbayne.weatherrunts.domain.model.WeatherDomainState
import com.mantisbayne.weatherrunts.presentation.model.CurrentWeatherDisplayable
import com.mantisbayne.weatherrunts.presentation.model.ForecastDisplayable
import com.mantisbayne.weatherrunts.presentation.model.WeatherListDisplayable
import com.mantisbayne.weatherrunts.presentation.model.WeatherUiState
import com.mantisbayne.weatherrunts.utils.DateFormatter
import com.mantisbayne.weatherrunts.utils.TimeOfDay
import javax.inject.Inject

class WeatherUiStateReducer @Inject constructor(
    private val dateFormatter: DateFormatter
) {

    fun reduce(domainState: WeatherDomainState): WeatherUiState =
        when (domainState) {
            is WeatherDomainState.Error -> WeatherUiState(
                error = errorMessage(domainState.errorMessage)
            )
            is WeatherDomainState.Success -> WeatherUiState(
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
            dateFormatter.formatToLocalTime(shouldShowDayOfWeek = true)
        )

    private fun feelsLikeText(feelsLike: Int?) = feelsLike?.let {
        "Feels like $it°F"
    } ?: "Unable to get feels like temperature, try again later"

    private fun temperature(temperature: Int?) = temperature?.let {
        "$it°F"
    } ?: "Unable to get current temperature, try again later"

    private fun weatherList(forecastList: List<ForecastDomainStateItem>) =
        WeatherListDisplayable(
            items = forecastList.map {
                ForecastDisplayable(
                    time = timeOfDay(it.timeOfDay),
                    temperature = "${it.temperature}°F"
                )
            }
        )

    private fun timeOfDay(timeOfDay: TimeOfDay) =
        when (timeOfDay) {
            TimeOfDay.MORNING -> "Morning"
            TimeOfDay.MIDDAY -> "Midday"
            TimeOfDay.EVENING -> "Evening"
            TimeOfDay.NIGHT -> "Night"
        }
}
