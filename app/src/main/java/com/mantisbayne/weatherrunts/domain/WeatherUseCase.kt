package com.mantisbayne.weatherrunts.domain

import com.mantisbayne.weatherrunts.data.repository.WeatherRepository
import com.mantisbayne.weatherrunts.dateutils.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    fun getWeatherData(latitude: Double, longitude: Double): Flow<WeatherDomainState> =
        flow {
            try {
                val response = repository.fetchWeatherForecast(latitude, longitude)
                val feelsLike = response.hourly.apparentTemperature.firstOrNull()
                emit(
                    WeatherDomainState(
                        forecastList = mapToForecastItem(
                            response.hourly.temperature2m.map { it.toInt() },
                            response.hourly.time
                        ),
                        feelsLike = feelsLike?.toInt(),
                        temperature = response.hourly.temperature2m.firstOrNull()?.toInt()
                    )
                )
            } catch (e: Exception) {
                emit(
                    WeatherDomainState(error = e.message)
                )
            }
        }

    // TODO extract
    private fun mapToForecastItem(temperatures: List<Int>, times: List<String>): List<ForecastItem> {
        var index = 0

        val timeOfDayToTemps = linkedMapOf<TimeOfDay, MutableList<Int>>(
            TimeOfDay.MORNING to mutableListOf(),
            TimeOfDay.MIDDAY to mutableListOf(),
            TimeOfDay.EVENING to mutableListOf(),
            TimeOfDay.NIGHT to mutableListOf()
        )

        while (index < temperatures.size) {
            val tempToTime = times[index] to temperatures[index]
            val timeOfDayToTemp = mapTempToTimeOfDay(tempToTime)
            val key = timeOfDayToTemp.first
            val value = timeOfDayToTemps.getOrDefault(key, mutableListOf())

            value.add(timeOfDayToTemp.second)

            timeOfDayToTemps[key] = value
            index++
        }

        val result = mutableListOf<ForecastItem>()
        println("DEBUG:: ${timeOfDayToTemps.entries}")
        timeOfDayToTemps.entries.forEach { (timeOfDay, temps) ->
            val avg = temps.average()
            result.add(
                ForecastItem(
                    temperature = avg.toInt(),
                    timeOfDay = timeOfDay
                )
            )
        }

        return result
    }

    private fun mapTempToTimeOfDay(tempToTime: Pair<String, Int>): Pair<TimeOfDay, Int> {
        val hour = DateUtils.getHourOfDay(tempToTime.first)

        val timeOfDay = when (hour) {
            in 4..11 -> TimeOfDay.MORNING
            in 12..17 -> TimeOfDay.MIDDAY
            in 17..22 -> TimeOfDay.EVENING
            else -> TimeOfDay.NIGHT
        }

        return timeOfDay to tempToTime.second
    }
}

enum class TimeOfDay {
    MORNING,
    MIDDAY,
    EVENING,
    NIGHT
}

data class ForecastItem(
    val temperature: Int = 0,
    val timeOfDay: TimeOfDay = TimeOfDay.MIDDAY
)

data class WeatherDomainState(
    val forecastList: List<ForecastItem> = emptyList(),
    val feelsLike: Int? = 0,
    val temperature: Int? = 0,
    val error: String? = null
)
