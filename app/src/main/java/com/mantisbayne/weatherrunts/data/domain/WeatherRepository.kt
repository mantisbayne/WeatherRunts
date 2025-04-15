package com.mantisbayne.weatherrunts.data.domain

import com.mantisbayne.weatherrunts.data.api.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun fetchWeatherForecast(latitude: Double, longitude: Double) =
        api.getWeather(latitude, longitude)
}