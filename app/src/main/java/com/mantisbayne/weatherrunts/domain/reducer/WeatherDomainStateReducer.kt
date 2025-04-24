package com.mantisbayne.weatherrunts.domain.reducer

import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import com.mantisbayne.weatherrunts.domain.model.ForecastDomainStateItem
import com.mantisbayne.weatherrunts.domain.model.WeatherDomainState
import com.mantisbayne.weatherrunts.utils.TimeOfDayMapper

class WeatherDomainStateReducer(private val timeOfDayMapper: TimeOfDayMapper) {

    // TODO map to success error in repo
    fun reduceSuccess(response: WeatherResponse): WeatherDomainState {

        val tempToTimes = timeOfDayMapper.mapTempsToTimes(
            response.hourly.temperature2m.map { it.toInt() },
            response.hourly.time
        )
        val currentFeelsLike = response.current.apparentTemperature.toInt()
        val currentTemperature = response.current.temperature2m.toInt()


        return WeatherDomainState.Success(
            mapToForecastDomainStateItem(tempToTimes),
            currentFeelsLike,
            currentTemperature
        )
    }

    fun reduceError(errorMessage: String?): WeatherDomainState {
        return WeatherDomainState.Error(errorMessage ?: "An Unknown Error occurred")
    }

    private fun mapToForecastDomainStateItem(tempToTimes: List<Pair<Int, String>>): List<ForecastDomainStateItem> {
        return mutableListOf<ForecastDomainStateItem>().apply {
            val timeOfDayToTemps = timeOfDayMapper.mapTempsToTimeOfDay(tempToTimes)
            timeOfDayToTemps.entries.forEach { (timeOfDay, temps) ->
                val avg = temps.average()
                add(
                    ForecastDomainStateItem(
                        temperature = avg.toInt(),
                        timeOfDay = timeOfDay
                    )
                )
            }
        }
    }
}
