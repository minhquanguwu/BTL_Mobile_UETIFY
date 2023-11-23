package com.example.btl_mobile_spotify.di

import com.example.btl_mobile_spotify.utils.AppDispatcher
import com.example.btl_mobile_spotify.utils.Dispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineModule {

    @Singleton
    @Binds
    abstract fun bindsCoroutineDispatcher(
        appCoroutineDispatcher: AppDispatcher
    ): Dispatcher
}
