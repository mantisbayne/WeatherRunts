package com.mantisbayne.weatherrunts.viewmodel

import androidx.annotation.DrawableRes
import com.mantisbayne.weatherrunts.R

data class CurrentWeatherDisplayable(
    val feelsLike: String = "",
    val temperature: String = "",
    val time: String = "",
    @DrawableRes val outfitImage: Int = R.drawable.girl_warm_weather
)

data class WeatherListDisplayable(
    val items: List<ForecastDisplayable> = emptyList()
)

// TODO image
data class ForecastDisplayable(
    val time: String = "",
    val temperature: String = ""
)
