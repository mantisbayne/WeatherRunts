package com.mantisbayne.weatherrunts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mantisbayne.weatherrunts.data.domain.WeatherRepository
import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {


    fun getWeatherData(latitude: Double, longitude: Double) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                flow<List<WeatherResponse>> {
                    repository.fetchWeatherForecast(latitude, longitude)
                }.collectLatest {

                }
            }
        }
}