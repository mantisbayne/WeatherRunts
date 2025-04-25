package com.mantisbayne.weatherrunts.presentation.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.WeatherRuntsTheme
import com.mantisbayne.weatherrunts.presentation.model.CurrentWeatherDisplayable
import com.mantisbayne.weatherrunts.presentation.model.ForecastDisplayable
import com.mantisbayne.weatherrunts.presentation.model.WeatherListDisplayable
import com.mantisbayne.weatherrunts.presentation.model.WeatherUiState
import com.mantisbayne.weatherrunts.ui.components.AppScreenLayout

@Composable
fun HomeScreen(uiState: WeatherUiState, modifier: Modifier) {
    when {
        uiState.error.isNotBlank() -> ErrorScreen(uiState.error)
        uiState.loading -> CircularProgressIndicator()
        else -> HomeScreenContent(uiState, modifier)
    }
}

@Composable
fun ErrorScreen(error: String?) {
    // TODO style, refresh option
    Text("There was an error: $error")
}

@Composable
fun HomeScreenContent(uiState: WeatherUiState, modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = uiState.currentWeather.time,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = uiState.currentWeather.temperature,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(uiState.currentWeather.feelsLike)
                }
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = painterResource(uiState.currentWeather.outfitImage),
                    contentDescription = "outfit"
                )
            }
        }

        val forecastList = uiState.weatherList.items
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Add horizontal padding if needed
        ) {
            items(forecastList) { forecastItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(forecastItem.time)
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(forecastItem.temperature)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenFullPreview() {
    WeatherRuntsTheme {
        AppScreenLayout(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Weather Runts") }
                )
            }
        ) {
            HomeScreen(
                uiState = WeatherUiState(
                    loading = false,
                    error = "",
                    currentWeather = CurrentWeatherDisplayable(
                        temperature = "72°F",
                        feelsLike = "Feels like 70°F",
                        time = "8 AM"
                    ),
                    weatherList = WeatherListDisplayable(
                        items = listOf(
                            ForecastDisplayable("8 AM", "65°F"),
                            ForecastDisplayable("9 AM", "68°F"),
                            ForecastDisplayable("10 AM", "70°F")
                        )
                    )
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    }
}
