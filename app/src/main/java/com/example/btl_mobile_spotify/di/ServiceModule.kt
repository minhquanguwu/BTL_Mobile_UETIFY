package com.example.btl_mobile_spotify.di


import android.content.Context
import androidx.room.Room
import com.example.btl_mobile_spotify.data.local.room.MusicDao
import com.example.btl_mobile_spotify.data.local.room.MusicXDatabase
import com.example.btl_mobile_spotify.data.models.mapper.MusicMapper
import com.example.btl_mobile_spotify.data.remote.MusicDataSource
import com.example.btl_mobile_spotify.exoplayer.datasource.FirebaseMusicPlayerDataSource
import com.example.btl_mobile_spotify.exoplayer.datasource.MusicPlayerDataSource
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Singleton


@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun providesAudioAttributes() = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @ServiceScoped
    @Provides
    fun providesExoPlayer(
        @ApplicationContext context: Context,
        audioAttributes: AudioAttributes
    ): ExoPlayer = ExoPlayer.Builder(context)
        .setAudioAttributes(audioAttributes, true)
        .setHandleAudioBecomingNoisy(true)
        .build().apply {
            setThrowsWhenUsingWrongThread(false)
        }

    @ServiceScoped
    @Provides
    fun providesDataSourceFactor(
        @ApplicationContext context: Context
    ): DefaultDataSource.Factory = DefaultDataSource.Factory(context)

    @ServiceScoped
    @Provides
    fun providesMusicPlayerDataSource(
        dataSource: MusicDataSource,
        musicMapper: MusicMapper,
        musicDao: MusicDao
    ): MusicPlayerDataSource = FirebaseMusicPlayerDataSource(dataSource, musicMapper, musicDao)

}
