package com.mantisbayne.weatherrunts.domain

import androidx.compose.ui.text.intl.Locale
import com.mantisbayne.weatherrunts.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    fun getWeatherData(latitude: Double, longitude: Double): Flow<WeatherDomainState> =
        flow {
            try {
                val response = repository.fetchWeatherForecast(latitude, longitude)
                val feelsLike = response.hourly.apparentTemperature.firstOrNull()
                val times = response.hourly.time
                emit(
                    WeatherDomainState(
                        response.hourly.temperature2m.map { it.toInt() },
                        feelsLike?.toInt(),
                        response.hourly.temperature2m.firstOrNull()?.toInt()
                    )
                )
            } catch (e: Exception) {
                emit(
                    WeatherDomainState(error = e.message)
                )
            }
        }

    private fun mapToForecastItem(temperatures: List<Int>, times: List<String>): List<ForecastItem> {
        // go through the temperatures
        // go through the times
        // put them together in some kind of data structure
        // if the LOCAL time is between 4am - 11am -> "Morning"
        // if 12pm - 4pm -> "Daytime"
        // 4pm - 10pm -> "Evening"
        // 10pm - 4am -> "Night"
        // for each of these time ranges, get the average temperature
        // return a list of ForecastItem
        return emptyList()
    }
}

data class ForecastItem(
    val temperature: Int = 0,
    val timeOfDay: String = ""
)

data class WeatherDomainState(
    val forecastList: List<Int> = emptyList(),
    val feelsLike: Int? = 0,
    val temperature: Int? = 0,
    val error: String? = null
)
