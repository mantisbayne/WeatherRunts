package com.mantisbayne.weatherrunts.presentation.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.mantisbayne.weatherrunts.domain.usecase.WeatherUseCase
import com.mantisbayne.weatherrunts.presentation.model.WeatherUiState
import com.mantisbayne.weatherrunts.presentation.reducer.WeatherUiStateReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val useCase: WeatherUseCase,
    private val reducer: WeatherUiStateReducer
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState(loading = true))
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
            withContext(Dispatchers.IO) {
                useCase.getWeatherData(latitude, longitude).collect { result ->
                    _uiState.update {
                        reducer.reduce(result)
                    }
                }
            }
        }
}
