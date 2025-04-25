package com.mantisbayne.weatherrunts.notifications

import androidx.annotation.DrawableRes

data class NotificationContent(
    val text: String = "",
    @DrawableRes val image: Int = 0
)
