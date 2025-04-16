package com.mantisbayne.weatherrunts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.WeatherRuntsTheme
import com.mantisbayne.weatherrunts.data.model.WeatherResponse
import com.mantisbayne.weatherrunts.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherRuntsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = hiltViewModel<WeatherViewModel>()
                    val weatherData by viewModel.weather.collectAsStateWithLifecycle()

                    HomeScreenContent(
                        data = weatherData?.hourly?.apparentTemperature ?: emptyList(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(data: List<Double>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(data) { weatherItem->
            Text(text = weatherItem.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    WeatherRuntsTheme {

    }
}