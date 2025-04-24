package com.mantisbayne.weatherrunts.presentation.model

import androidx.annotation.DrawableRes
import com.mantisbayne.weatherrunts.R

data class CurrentWeatherDisplayable(
    val feelsLike: String = "",
    val temperature: String = "",
    val time: String = "",
    @DrawableRes val outfitImage: Int = R.drawable.warm_weather_outfit
)

data class WeatherListDisplayable(
    val items: List<ForecastDisplayable> = emptyList()
)

data class ForecastDisplayable(
    val time: String = "",
    val temperature: String = ""
)
