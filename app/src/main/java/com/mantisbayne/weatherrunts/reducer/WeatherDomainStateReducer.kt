package com.mantisbayne.weatherrunts.reducer

import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import com.mantisbayne.weatherrunts.utils.TempUtils
import com.mantisbayne.weatherrunts.utils.TimeOfDay

object WeatherDomainStateReducer {

    // TODO map to success error in repo
    fun reduceSuccess(response: WeatherResponse): WeatherDomainState {

        val tempToTimes = TempUtils.mapTempsToTimes(
            response.hourly.temperature2m.map { it.toInt() },
            response.hourly.time
        )
        val currentFeelsLike = response.current.apparentTemperature.toInt()
        val currentTemperature = response.current.temperature2m.toInt()


        return WeatherDomainState.Success(
            mapToForecastItem(tempToTimes),
            currentFeelsLike,
            currentTemperature
        )
    }

    fun reduceError(errorMessage: String?): WeatherDomainState {
        return WeatherDomainState.Error(errorMessage ?: "An Unknown Error occurred")
    }

    private fun mapToForecastItem(tempToTimes: List<Pair<Int, String>>): List<ForecastItem> =
        mutableListOf<ForecastItem>().apply {
            val timeOfDayToTemps = TempUtils.mapTempsToTimeOfDay(tempToTimes)
            timeOfDayToTemps.entries.forEach { (timeOfDay, temps) ->
                val avg = temps.average()
                add(
                    ForecastItem(
                        temperature = avg.toInt(),
                        timeOfDay = timeOfDay
                    )
                )
            }
        }
}

data class ForecastItem(
    val temperature: Int = 0,
    val timeOfDay: TimeOfDay = TimeOfDay.MIDDAY
)

sealed class WeatherDomainState {
    data class Success(
        val forecastList: List<ForecastItem> = emptyList(),
        val feelsLike: Int = 0,
        val temperature: Int = 0,
    ) : WeatherDomainState()

    data class Error(
        val errorMessage: String = ""
    ) : WeatherDomainState()
}