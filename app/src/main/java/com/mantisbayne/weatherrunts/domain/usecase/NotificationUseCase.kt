package com.mantisbayne.weatherrunts.domain.usecase

import com.mantisbayne.weatherrunts.data.repository.WeatherRepository
import com.mantisbayne.weatherrunts.notifications.NotificationContent
import com.mantisbayne.weatherrunts.utils.OutfitMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

// TODO split up
class NotificationUseCase @Inject constructor(
    private val outfitMapper: OutfitMapper,
    private val repository: WeatherRepository
) {

    fun getWeatherDataForNotification(
        latitude: Double,
        longitude: Double
    ): Flow<NotificationContent> =
        flow {
            try {
                val weather = repository.fetchWeatherForecast(latitude, longitude)
                val temperature = weather.current.temperature2m.toInt()
                val precipitation = weather.current.precipitation.toInt()
                val outfit = outfitMapper.getOutfitDisplayable(
                    temperature, precipitation
                )

                emit(
                    NotificationContent(
                        text = outfit.text,
                        image = outfit.image
                    )
                )
            } catch (e: Exception) {
                // TODO retry logic
                emit(
                    NotificationContent(
                        text = "Error retrieving weather, tap to try again"
                    )
                )
            }
        }

}