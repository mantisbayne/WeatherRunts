package com.mantisbayne.weatherrunts.utils

import androidx.annotation.DrawableRes
import com.mantisbayne.weatherrunts.R
import javax.inject.Inject

class OutfitMapper @Inject constructor() {

    // TODO vectors, clean up
    fun getOutfitDisplayable(temperature: Int, precipitation: Int) =
        when (mapTempToCategory(temperature, precipitation)) {
            OutfitCategory.RAINY -> OutfitDisplayable(
                image = R.drawable.rainy_day_outfit,
                text = "Rainy"
            )
            OutfitCategory.COLD -> OutfitDisplayable(
                image = R.drawable.cold_weather_outfit,
                text = "Cold"
            )
            OutfitCategory.COOL -> OutfitDisplayable(
                image = R.drawable.cool_weather_outfit,
                text = "Cool"
            )
            OutfitCategory.MILD -> OutfitDisplayable(
                image = R.drawable.mild_weather_outfit,
                text = "Mild"
            )
            OutfitCategory.WARM -> OutfitDisplayable(
                image = R.drawable.warm_weather_outfit,
                text = "Warm"
            )
        }


    // TODO user input temperature preferences, add HOT
    private fun mapTempToCategory(temperature: Int, precipitation: Int): OutfitCategory =
        when {
            precipitation >= 60 -> OutfitCategory.RAINY
            temperature <= 39 -> OutfitCategory.COLD
            temperature in 40..59 -> OutfitCategory.COOL
            temperature >= 80 -> OutfitCategory.WARM
            else -> OutfitCategory.MILD
        }
}

data class OutfitDisplayable(
    @DrawableRes val image: Int = 0,
    val text: String = ""
)

enum class OutfitCategory {
    COLD,
    RAINY,
    COOL,
    MILD,
    WARM
}
