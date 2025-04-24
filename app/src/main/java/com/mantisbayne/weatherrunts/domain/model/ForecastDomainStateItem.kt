package com.mantisbayne.weatherrunts.domain.model

import com.mantisbayne.weatherrunts.utils.TimeOfDay

data class ForecastDomainStateItem(
    val temperature: Int = 0,
    val timeOfDay: TimeOfDay = TimeOfDay.MIDDAY
)
