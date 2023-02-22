package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "Firebase"
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String?) {
        Log.i(TAG, "Refreshed token: $p0")

        //sendRegistrationToServer(token)
    }

    override fun onMessageReceived(p0: RemoteMessage?) {
        Log.i(TAG, "From: ${p0?.from}")

        p0?.data?.let {
            Log.i(TAG, "Message data payload: ${p0.data}")
        }

        p0?.notification?.body?.let {
            Log.i(TAG, "Message notification body: ${it}")
            sendNotification(it)
        }
    }

    private fun sendNotification(body: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(body, applicationContext)
    }
}