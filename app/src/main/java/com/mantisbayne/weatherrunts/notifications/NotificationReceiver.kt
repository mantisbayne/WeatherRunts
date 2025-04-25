package com.mantisbayne.weatherrunts.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mantisbayne.weatherrunts.di.AppModule.ApplicationScope
import com.mantisbayne.weatherrunts.domain.usecase.NotificationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NotificationReceiver @Inject constructor(
    private val notificationBuilder: NotificationBuilder,
    private val useCase: NotificationUseCase,
    @ApplicationScope private val scope: CoroutineScope
) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // TODO handle fallback
        val latitude = intent?.getDoubleExtra("latitude", 0.0) ?: 0.0
        val longitude = intent?.getDoubleExtra("longitude", 0.0) ?: 0.0

        useCase.getWeatherDataForNotification(latitude, longitude)
            .onEach { content ->
                notificationBuilder.showNotification(content)
            }
            .launchIn(scope)
    }
}
