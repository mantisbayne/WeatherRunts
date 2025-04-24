package com.mantisbayne.weatherrunts.domain.usecase

import com.mantisbayne.weatherrunts.data.repository.WeatherRepository
import com.mantisbayne.weatherrunts.domain.model.WeatherDomainState
import com.mantisbayne.weatherrunts.domain.reducer.WeatherDomainStateReducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val domainStateReducer: WeatherDomainStateReducer,
    private val repository: WeatherRepository
) {

    fun getWeatherData(latitude: Double, longitude: Double): Flow<WeatherDomainState> =
        flow {
            try {
                val response = repository.fetchWeatherForecast(latitude, longitude)
                emit(
                    domainStateReducer.reduceSuccess(response)
                )
            } catch (e: Exception) {
                emit(
                    domainStateReducer.reduceError(e.message)
                )
            }
        }

}
