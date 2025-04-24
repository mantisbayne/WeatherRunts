package com.mantisbayne.weatherrunts

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun WeatherApp() {
//    AppScreenLayout(
//        topBar = { TopAppBar(title = { Text("Weather Runts") }) }
//    ) { innerPadding ->
//        val viewModel = hiltViewModel<WeatherViewModel>()
//        val uiState by viewModel.uiState.collectAsState()
//        val context = LocalContext.current
//
//        val launcher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//            if (isGranted) {
//                viewModel.loadWeatherFromCurrentLocation()
//            } else {
//                // TODO fallback to manual input
//            }
//        }
//
//        LaunchedEffect(Unit) {
//            val isGranted = ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//            if (isGranted) {
//                viewModel.loadWeatherFromCurrentLocation()
//            } else {
//                launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//        }
//
//        HomeScreen(uiState = uiState, modifier = Modifier.padding(innerPadding))
//    }
//}
