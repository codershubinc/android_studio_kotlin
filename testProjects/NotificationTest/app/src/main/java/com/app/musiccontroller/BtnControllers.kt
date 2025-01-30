package com.app.musiccontroller

import android.content.Context
import android.media.session.MediaController
import android.media.session.MediaSessionManager

class MediaControlHelper(context: Context) {
    private val mediaSessionManager = context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager

    fun getActiveMediaSession(): MediaController? {
        val mds =  mediaSessionManager.getActiveSessions(null).firstOrNull()
        println("Active Media Sessions: $mds")
        return mds
    }

    fun playPause() {
        getActiveMediaSession()?.transportControls?.let {
            it.play() // Toggle between play and pause if necessary
        }
    }

    fun nextTrack() {
        getActiveMediaSession()?.transportControls?.skipToNext()
    }

    fun previousTrack() {
        getActiveMediaSession()?.transportControls?.skipToPrevious()
    }
}
