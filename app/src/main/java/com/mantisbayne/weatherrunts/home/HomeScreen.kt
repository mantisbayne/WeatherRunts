package com.mantisbayne.weatherrunts.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.WeatherRuntsTheme
import com.mantisbayne.weatherrunts.viewmodel.CurrentWeatherDisplayable
import com.mantisbayne.weatherrunts.viewmodel.ForecastDisplayable
import com.mantisbayne.weatherrunts.viewmodel.WeatherListDisplayable
import com.mantisbayne.weatherrunts.viewmodel.WeatherUiState

@Composable
fun HomeScreen(uiState: WeatherUiState, modifier: Modifier = Modifier) {

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
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Column {
                Text(uiState.currentWeather.temperature)
                Text(uiState.currentWeather.feelsLike)
            }
        }

        val forecastList = uiState.weatherList.items
        LazyColumn(
            modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(forecastList) { forecastItem ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation()
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(forecastItem.time)
                        Spacer(modifier.width(24.dp))
                        Text(forecastItem.temperature)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    WeatherRuntsTheme {
        HomeScreenContent(
            uiState = WeatherUiState(
                currentWeather = CurrentWeatherDisplayable("72°F", "Feels like 70°F"),
                weatherList = WeatherListDisplayable(
                    items = listOf(
                        ForecastDisplayable("8 AM", "65°F"),
                        ForecastDisplayable("9 AM", "68°F")
                    )
                )
            ),
            modifier = Modifier
        )
    }
}