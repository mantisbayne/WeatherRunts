package com.mantisbayne.weatherrunts.domain

import com.mantisbayne.weatherrunts.data.repository.WeatherRepository
import com.mantisbayne.weatherrunts.utils.TempUtils
import com.mantisbayne.weatherrunts.utils.TimeOfDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    fun getWeatherData(latitude: Double, longitude: Double): Flow<WeatherDomainState> =
        flow {
            try {
                val response = repository.fetchWeatherForecast(latitude, longitude)
                val tempToTimes = TempUtils.mapTempsToTimes(
                    response.hourly.temperature2m.map { it.toInt() },
                    response.hourly.time
                )
                val currentFeelsLike = response.current.apparentTemperature
                val currentTemperature = response.current.temperature2m

                emit(
                    WeatherDomainState(
                        forecastList = mapToForecastItem(tempToTimes),
                        feelsLike = currentFeelsLike.toInt(),
                        temperature = currentTemperature.toInt()
                    )
                )
            } catch (e: Exception) {
                emit(
                    WeatherDomainState(error = e.message)
                )
            }
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

data class WeatherDomainState(
    val forecastList: List<ForecastItem> = emptyList(),
    val feelsLike: Int? = 0,
    val temperature: Int? = 0,
    val error: String? = null
)
