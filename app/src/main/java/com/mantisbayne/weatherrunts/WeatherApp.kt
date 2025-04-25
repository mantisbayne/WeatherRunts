package com.mantisbayne.weatherrunts

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.mantisbayne.weatherrunts.presentation.features.HomeScreen
import com.mantisbayne.weatherrunts.presentation.viewmodel.WeatherViewModel
import com.mantisbayne.weatherrunts.ui.components.AppScreenLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp() {
    AppScreenLayout(
        topBar = { TopAppBar(title = { Text("Weather Runts") }) }
    ) { innerPadding ->
        val viewModel = hiltViewModel<WeatherViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                viewModel.loadWeatherFromCurrentLocation()
            } else {
                // TODO fallback to manual input
            }
        }

        LaunchedEffect(Unit) {
            val isGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            if (isGranted) {
                viewModel.loadWeatherFromCurrentLocation()
            } else {
                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        HomeScreen(uiState = uiState, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun RequestPermissionsIfNeeded(
    permission: String,
    onPermissionGranted: () -> Unit,
    onPermissionDenied: (() -> Unit)? = null
) {
    val context = LocalContext.current
    var shouldRequestPermission by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied?.invoke()
        }
    }

    LaunchedEffect(shouldRequestPermission) {
        if (shouldRequestPermission) {
            launcher.launch(permission)
        }
    }

    LaunchedEffect(Unit) {
        val isGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (isGranted) {
            onPermissionGranted()
        } else {
            shouldRequestPermission = true
        }
    }
}
