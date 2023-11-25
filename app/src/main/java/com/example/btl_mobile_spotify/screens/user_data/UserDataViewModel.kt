package com.example.btl_mobile_spotify.screens.user_data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.example.btl_mobile_spotify.screens.user_data.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class UserDataViewModel:ViewModel(){
    val state = mutableStateOf(UserInfo())
//
//    init {
//        getData()
//    }
//
//    private fun getData(){
//        viewModelScope.launch {
//            state.value = getUserFromFirestore()
//        }
//    }

    fun retrieveData(
//        userID: String,
        context: Context,
        data: (UserInfo) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("users")
            .document(Firebase.auth.currentUser?.uid ?: "")

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    // for getting single or particular document
                    if (it.exists()) {
                        val userData = it.toObject<UserInfo>()!!
                        data(userData)
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}

suspend fun getUserFromFirestore():UserInfo{
    val db = FirebaseFirestore.getInstance()
    var userInfo = UserInfo()
    try {
        db.collection("users").get().await().map {
            val result = it.toObject(UserInfo::class.java)
            userInfo = result
        }
    } catch (e: FirebaseFirestoreException){
        Log.d("error", "getUserFromFirestore: $e")
    }
    return userInfo
}