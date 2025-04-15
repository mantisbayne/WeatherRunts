package com.mantisbayne.weatherrunts.data.api

import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "precipitation_probability,apparent_temperature"
    ): WeatherResponse
}
