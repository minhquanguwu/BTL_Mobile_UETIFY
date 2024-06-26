package com.example.btl_mobile_spotify.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_DURATION
import android.support.v4.media.MediaMetadataCompat.METADATA_KEY_GENRE
import android.support.v4.media.session.PlaybackStateCompat
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.btl_mobile_spotify.data.models.local.Music
import com.example.btl_mobile_spotify.exoplayer.isPlaying
import com.example.btl_mobile_spotify.exoplayer.isPrepared


infix fun <T, F> Resource<T>.mapTo(change: (T) -> F): Resource<F> = when (this) {
    is Resource.Error -> Resource.Error(errorType, message)
    is Resource.Loading -> Resource.Loading()
    is Resource.Success -> Resource.Success(data?.let { change(it) }, message)
}

fun Resource<*>.mapToUnit() = this mapTo {}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Long.formatDuration(): String {
    var seconds = this / 1000
    val minutes = seconds / 60
    seconds %= 60
    return String.format("%02d:%02d", minutes, seconds)
}

fun List<String>.getArtistsString() = joinToString(", ")

fun MediaMetadataCompat.getMusic(): Music = Music(
    id = description.mediaId ?: "",
    title = description.title.toString(),
    duration = getLong(METADATA_KEY_DURATION),
    artists = description.subtitle.toString().split(","),
    imageUrl = description.iconUri.toString(),
    musicUrl = description.mediaUri.toString(),
    genre = getString(METADATA_KEY_GENRE) ?: "Unkown",
    lyrics = getString(METADATA_KEY_DISPLAY_DESCRIPTION) ?:"Unkown"
)

fun MediaBrowserCompat.MediaItem.getMusic(): Music = Music(
    id = description.mediaId ?: "",
    title = description.title.toString(),
    duration = description.extras?.getLong("duration") ?: 0,
    artists = description.subtitle.toString().split(","),
    imageUrl = description.iconUri.toString(),
    musicUrl = description.mediaUri.toString(),
    genre = description.extras?.getString("genre") ?: "",
    lyrics = description.extras?.getString("lyrics") ?: "",

)

fun Context.loadImageBitmap(url: String, onImageLoaded: (Bitmap) -> Unit) {
    Glide.with(this).asBitmap().load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                onImageLoaded(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) = Unit
        })
}

fun PlaybackStateCompat.getMusicState(): MusicState = when {
    isPlaying -> MusicState.PLAYING
    isPrepared -> MusicState.PAUSED
    else -> MusicState.NONE
}
