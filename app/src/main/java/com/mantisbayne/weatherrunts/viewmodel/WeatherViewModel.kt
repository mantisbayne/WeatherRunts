package com.mantisbayne.weatherrunts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantisbayne.weatherrunts.data.domain.WeatherRepository
import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _weather= MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather.asStateFlow()

    init {
        getWeatherData(52.71, 100.67)
    }

    private fun getWeatherData(latitude: Double, longitude: Double) =
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.fetchWeatherForecast(latitude, longitude)
                }
                _weather.value = response
            } catch (e: Exception) {
                _weather.value = null
            }
        }
}