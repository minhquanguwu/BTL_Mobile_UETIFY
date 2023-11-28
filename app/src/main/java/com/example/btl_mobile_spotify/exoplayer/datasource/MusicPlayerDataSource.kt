package com.example.btl_mobile_spotify.exoplayer.datasource

import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.MediaMetadataCompat
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.utils.DURATION
import com.example.btl_mobile_spotify.utils.GENRE
import com.example.btl_mobile_spotify.utils.LYRICS
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource


abstract class MusicPlayerDataSource {

    var allMusic: List<Music> = emptyList()

    val allMusicAsMetadata: List<MediaMetadataCompat>
        get() = allMusic.map { getMetadataCompatFromMusic(it) }

    val allMusicAsMediaItem: List<MediaBrowserCompat.MediaItem>
        get() = allMusic.map { getMediaItemFromMusic(it) }

    private val onReadyListeners = mutableListOf<(Boolean) -> Unit>()

    protected var state: State = State.STATE_CREATED
        set(value) {
            if (value == State.STATE_INITIALIZED || value == State.STATE_ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach {
                        it(value == State.STATE_INITIALIZED)
                    }
                }
            } else field = value
        }

    abstract suspend fun getMusic()

    private fun getMetadataCompatFromMusic(music: Music): MediaMetadataCompat =
        MediaMetadataCompat.Builder().apply {
            putString(MediaMetadataCompat.METADATA_KEY_TITLE, music.title)
            putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, music.title)
            putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, music.id)
            putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, music.imageUrl)
            putString(MediaMetadataCompat.METADATA_KEY_ARTIST, music.artists.joinToString(","))
            putString(
                MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE,
                music.artists.joinToString(",")
            )
            putLong(MediaMetadataCompat.METADATA_KEY_DURATION, music.duration)
            putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, music.imageUrl)
            putString(
                MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION,
                music.artists.joinToString(",")
            )
            putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, music.musicUrl)
            putString(MediaMetadataCompat.METADATA_KEY_GENRE, music.genre)
            putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, music.lyrics)

        }.build()

    private fun getMediaItemFromMusic(music: Music) = MediaBrowserCompat.MediaItem(
        MediaDescriptionCompat.Builder().apply {
            setMediaUri(music.musicUrl.toUri())
            setTitle(music.title)
            setSubtitle(music.artists.joinToString(","))
            setMediaId(music.id)
            setIconUri(music.imageUrl.toUri())
            setExtras(bundleOf(DURATION to music.duration))
            setExtras(bundleOf(GENRE to music.genre))
            setExtras(bundleOf(LYRICS to music.lyrics))
        }.build(),
        MediaBrowserCompat.MediaItem.FLAG_PLAYABLE
    )

    fun asMediaSource(dataSourceFactory: DefaultDataSource.Factory): MediaSource {
        val mediaSources = allMusic.map {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(it.musicUrl.toUri())
        }
        return ConcatenatingMediaSource().apply { addMediaSources(mediaSources) }
    }

    fun whenReady(onReady: (Boolean) -> Unit): Boolean =
        if (state == State.STATE_CREATED || state == State.STATE_INITIALIZING) {
            onReadyListeners += onReady
            false
        } else {
            onReady(state == State.STATE_INITIALIZED)
            true
        }

    enum class State {
        STATE_CREATED,
        STATE_INITIALIZING,
        STATE_INITIALIZED,
        STATE_ERROR
    }
}
