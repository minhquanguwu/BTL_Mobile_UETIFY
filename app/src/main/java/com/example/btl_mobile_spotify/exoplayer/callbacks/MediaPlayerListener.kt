package com.example.btl_mobile_spotify.exoplayer.callbacks

import com.example.btl_mobile_spotify.exoplayer.MusicService
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player

class MediaPlayerListener constructor(private val musicService: MusicService) : Player.Listener {
    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        super.onPlayerStateChanged(playWhenReady, playbackState)
        if (playbackState == Player.STATE_READY && !playWhenReady)
            musicService.stopForeground(false)
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
//        Timber.d(error.message)
//        musicService.showToast(error.message.toString())
    }
}
