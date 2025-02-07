package com.app.notificationtest

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MusicNotificationListenerService : NotificationListenerService() {

    override fun onListenerConnected() {
        super.onListenerConnected()
        println("Notification Listener connected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Handle notifications here
        println("Notification posted: ${sbn.notification.tickerText}")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Handle notification removal here
        println("Notification removed: ${sbn.notification.tickerText}")
    }
}