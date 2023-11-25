package com.example.btl_mobile_spotify.di

import com.example.btl_mobile_spotify.data.remote.FirebaseMusicDataSource
import com.example.btl_mobile_spotify.data.remote.MusicDataSource
import com.example.btl_mobile_spotify.data.repo.MusicRepo
import com.example.btl_mobile_spotify.data.repo.MusicRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Binds
    abstract fun bindsMusicDataSource(
        musicDataSourceImpl: FirebaseMusicDataSource
    ): MusicDataSource

    @Binds
    abstract fun bindsMusicRepo(
        musicRepoImpl: MusicRepoImpl
    ): MusicRepo

//    @Binds
//    abstract fun bindsLyricDataSource(
//        lyricsDataSourceImpl: LyricsDataSourceImpl
//    ): LyricDataSource
//
//    @Singleton
//    @Binds
//    abstract fun bindsLyricsRepo(
//        lyricsRepoImpl: LyricsRepoImpl
//    ): LyricsRepo
}
