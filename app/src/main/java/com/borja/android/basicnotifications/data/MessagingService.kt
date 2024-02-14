package com.borja.android.basicnotifications.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.borja.android.basicnotifications.MainActivity
import com.borja.android.basicnotifications.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

class MessagingService @Inject constructor() : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.data.isNotEmpty()) {
            val example1 = message.data["example1"].orEmpty()
            message.data.forEach { key, value ->
                Log.i("BorchxData", "Para la clave $key, tiene el valor: $value")
            }
        }
        message.notification?.let {
            val title = it.title.orEmpty()
            val body = it.body.orEmpty()
            createNotification(title, body)
        }
    }

    private fun createNotification(title: String, body: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent =
            PendingIntent.getActivity(this, 999, intent, PendingIntent.FLAG_IMMUTABLE)

        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        val channelId = getString(R.string.default_channel)

        val notificationBuilder =
            NotificationCompat.Builder(this, "").setSmallIcon(R.drawable.ic_android)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntent)
                .setChannelId(channelId)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //CHANNEL
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Promociones",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToken(token)
    }

    private fun sendRegistrationToken(token: String) {
        Log.i("BorchxToken", token)
        //llamada al backend
    }

}