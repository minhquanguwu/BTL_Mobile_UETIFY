package com.example.btl_mobile_spotify.data.remote

import com.example.btl_mobile_spotify.data.models.remote.MusicDTO
import com.example.btl_mobile_spotify.utils.Resource
import com.example.btl_mobile_spotify.utils.mapToUnit
import com.example.btl_mobile_spotify.utils.safeCall
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseMusicDataSource @Inject constructor(private val fireStore: FirebaseFirestore) :
    MusicDataSource {
    val MUSIC_KEY = "music"

    override suspend fun getAllMusic(): Resource<List<MusicDTO>> = safeCall {
        fireStore.collection(MUSIC_KEY).get().await().toObjects(MusicDTO::class.java)
    }

    override suspend fun uploadMusic(music: MusicDTO) = safeCall {
        fireStore.collection(MUSIC_KEY).document(music.title).set(music).await()
    }.mapToUnit()
}
