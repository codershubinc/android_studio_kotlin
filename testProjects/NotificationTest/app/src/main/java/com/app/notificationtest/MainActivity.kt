package com.app.notificationtest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.musiccontroller.MediaControlHelper

class MainActivity : AppCompatActivity() {
    private lateinit var mediaControlHelper: MediaControlHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaControlHelper = MediaControlHelper(this)

        // Check notification access
        if (!isNotificationAccessGranted(this)) {
            showNotificationAccessDialog()
        } else {
            println("Notification access granted")
        }

        findViewById<Button>(R.id.playPauseButton).setOnClickListener {
            mediaControlHelper.playPause()
        }
        findViewById<Button>(R.id.nextButton).setOnClickListener {
            mediaControlHelper.nextTrack()
        }
        findViewById<Button>(R.id.prevButton).setOnClickListener {
            mediaControlHelper.previousTrack()
        }
    }

    private fun isNotificationAccessGranted(context: Context): Boolean {
        val enabledListeners = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        val packageName = context.packageName
        return enabledListeners != null && enabledListeners.contains(packageName)
    }

    private fun showNotificationAccessDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Notification Access Required")
        builder.setMessage("To control music playback, this app needs access to notifications. Please grant access in the settings.")
        builder.setPositiveButton("Grant Access") { _, _ ->
            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            startActivity(intent)
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setCancelable(false) // Prevent dismissing by tapping outside
        builder.show()
    }
}
