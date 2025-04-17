package com.mantisbayne.weatherrunts.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.material3.Text
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.mantisbayne.weatherrunts.data.domain.WeatherRepository
import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun loadWeatherFromCurrentLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        val permission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (permission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        getWeatherData(location.latitude, location.longitude)
                    } else {
                        // TODO fallback or error handling
                    }
                }
        } else {
            // TODO handle permission denied or skip
        }
    }

    private fun getWeatherData(latitude: Double, longitude: Double) =
        viewModelScope.launch {
            // TODO implement usecase, finish state mapping
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.fetchWeatherForecast(latitude, longitude)
                }
                _uiState.value = WeatherUiState(
                    weatherList = WeatherListDisplayable(
                        items = response.hourly.apparentTemperature.map {
                            ForcastDisplayable(
                                feelsLike = it.toString()
                            )
                        }
                    )
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState()
            }
        }

    private fun feelsLikeForecastText(weather: WeatherResponse) = if (weather != null) {
        "Feels like: ${weather.hourly.apparentTemperature.firstOrNull()}°F"
    } else {
        "Loading..."
    }
}
