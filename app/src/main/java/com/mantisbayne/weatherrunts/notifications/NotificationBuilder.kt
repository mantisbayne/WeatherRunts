package com.mantisbayne.weatherrunts.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import javax.inject.Inject

class NotificationBuilder @Inject constructor(
    private val context: Context
) {

    private val channelId = CHANNEL_ID

    fun showNotification(content: NotificationContent) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(content.image)
            .setContentText(content.text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private companion object {
        const val CHANNEL_ID = "daily_outfit_channel"
        const val NAME = "Daily Outfit"
        const val NOTIFICATION_ID = 1001
    }
}
