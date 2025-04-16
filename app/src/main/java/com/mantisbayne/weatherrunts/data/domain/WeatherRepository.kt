package com.mantisbayne.weatherrunts.data.domain

import com.mantisbayne.weatherrunts.data.api.WeatherApi
import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun fetchWeatherForecast(latitude: Double, longitude: Double): WeatherResponse =
        api.getWeather(latitude, longitude)
}