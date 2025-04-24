package com.mantisbayne.weatherrunts.utils

import com.mantisbayne.weatherrunts.R

object OutfitMapper {

    // TODO vectors
    fun getOutfitAsset(temperature: Int, precipitation: Int) =
        when (mapTempToCategory(temperature, precipitation)) {
            OutfitCategory.RAINY -> R.drawable.rainy_day_outfit
            OutfitCategory.COLD -> R.drawable.cold_weather_outfit
            OutfitCategory.COOL -> R.drawable.cool_weather_outfit
            OutfitCategory.MILD -> R.drawable.mild_weather_outfit
            OutfitCategory.WARM -> R.drawable.warm_weather_outfit
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

enum class OutfitCategory {
    COLD,
    RAINY,
    COOL,
    MILD,
    WARM
}
