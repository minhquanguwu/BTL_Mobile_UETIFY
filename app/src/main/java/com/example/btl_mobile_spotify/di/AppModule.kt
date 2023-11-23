package com.example.btl_mobile_spotify.di

import android.content.Context
import androidx.room.Room
import com.example.btl_mobile_spotify.data.local.room.MusicDao
import com.example.btl_mobile_spotify.data.local.room.MusicXDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesFirestore(): FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun providesMusicDb(
        @ApplicationContext context: Context
    ): MusicXDatabase = Room.databaseBuilder(context, MusicXDatabase::class.java, "MusicXDatabase")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesMusicDao(database: MusicXDatabase): MusicDao = database.getMusicDao()

}
