package com.app.musiccontroller

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MusicNotificationListenerService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.packageName.contains("spotify") || sbn.packageName.contains("music")) {
            sbn.notification?.let {
                val extras = it.extras
                val title = extras.getString("android.title")
                val text = extras.getString("android.text")

                // Log or display the notification details
                println("Music Notification - Title: $title, Text: $text")
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        if (sbn.packageName.contains("spotify") || sbn.packageName.contains("music")) {
            println("Music Notification Removed from ${sbn.packageName}")
        }
    }
}
