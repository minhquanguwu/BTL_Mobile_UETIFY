package com.example.btl_mobile_spotify.exoplayer

import android.app.PendingIntent
import android.graphics.Bitmap
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import com.example.btl_mobile_spotify.R
import com.example.btl_mobile_spotify.exoplayer.MusicService
import com.example.btl_mobile_spotify.utils.CHANNEL_ID
import com.example.btl_mobile_spotify.utils.NOTIFICATION_ID
import com.example.btl_mobile_spotify.utils.loadImageBitmap
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import dev.vaibhav.musicx.exoplayer.callbacks.MusicNotificationListener


class MusicNotificationManager(
    private val musicService: MusicService,
    sessionToken: MediaSessionCompat.Token,
    private val onMusicChanged: () -> Unit
) {

    private val mediaController = MediaControllerCompat(musicService, sessionToken)

    private val notificationManager = PlayerNotificationManager.Builder(
        musicService, NOTIFICATION_ID, CHANNEL_ID
    ).apply {
        setChannelNameResourceId(R.string.notification_channel_name)
        setChannelDescriptionResourceId(R.string.notification_channel_description)
        setMediaDescriptionAdapter(MusicMediaDescriptionAdapter(mediaController))
        setNotificationListener(MusicNotificationListener(musicService))
    }.build().apply {
        setMediaSessionToken(sessionToken)
    }

    fun showNotification(player: Player) {
        notificationManager.setPlayer(player)
    }

    private inner class MusicMediaDescriptionAdapter(private val mediaController: MediaControllerCompat) :
        PlayerNotificationManager.MediaDescriptionAdapter {
        override fun getCurrentContentTitle(player: Player): CharSequence {
            onMusicChanged()
            return mediaController.metadata.description.title.toString()
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            return mediaController.sessionActivity
        }

        override fun getCurrentContentText(player: Player): CharSequence {
            return mediaController.metadata.description.subtitle.toString()
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            musicService.loadImageBitmap(mediaController.metadata.description.iconUri.toString()) {
                callback.onBitmap(it)
            }
            return null
        }
    }
}
