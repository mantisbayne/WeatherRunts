package com.mantisbayne.weatherrunts.reducer

import android.os.Build
import com.mantisbayne.weatherrunts.domain.WeatherDomainState
import com.mantisbayne.weatherrunts.viewmodel.CurrentWeatherDisplayable
import com.mantisbayne.weatherrunts.viewmodel.ForecastDisplayable
import com.mantisbayne.weatherrunts.viewmodel.WeatherListDisplayable
import com.mantisbayne.weatherrunts.viewmodel.WeatherUiState
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
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
            temperature(temperature)
        )

    private fun feelsLikeText(feelsLike: Int?) = feelsLike?.let {
        "Feels like: $it°F"
    } ?: "Unable to get feels like temperature, try again later"

    private fun temperature(temperature: Int?) = temperature?.let {
        "Currently $it°F"
    } ?: "Unable to get current temperature, try again later"

    private fun weatherList(forecastList: List<Int>) =
        WeatherListDisplayable(
            items = forecastList.map {
                ForecastDisplayable(
                    time = getCurrentTimeFormatted(),
                    temperature = "$it°F"
                )
            }
        )

    private fun getCurrentTimeFormatted(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentTime = LocalTime.now()
            val formatter = DateTimeFormatter.ofPattern("hh:mm a")
            currentTime.format(formatter)
        } else {
            val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
            sdf.format(Date())
        }
}