package com.mantisbayne.weatherrunts.domain

import com.mantisbayne.weatherrunts.data.repository.WeatherRepository
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
}

data class WeatherDomainState(
    val forecastList: List<Int> = emptyList(),
    val feelsLike: Int? = 0,
    val temperature: Int? = 0,
    val error: String? = null
)
