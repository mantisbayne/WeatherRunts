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
                        response.hourly.temperature2m,
                        feelsLike,
                        response.hourly.temperature2m.firstOrNull()
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
    val forecastList: List<Double> = emptyList(),
    val feelsLike: Double? = 0.0,
    val temperature: Double? = 0.0,
    val error: String? = null
)
