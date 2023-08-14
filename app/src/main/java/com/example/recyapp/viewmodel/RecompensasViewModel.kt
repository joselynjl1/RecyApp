package com.example.recyapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyapp.model.Recompensas
import com.example.recyapp.model.Usuario
import com.example.recyapp.network.FirestoreServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RecompensasViewModel: ViewModel() {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val servicesFire= FirestoreServices()
    //conectar datos de la bd
    fun getRecompensas(): LiveData<MutableList<Recompensas>> {
        val mutableList= MutableLiveData<MutableList<Recompensas>>()
        servicesFire.getRecompensasFirestore().observeForever {
            mutableList.value=it
        }
        return mutableList
    }

    fun getCurrentUserToRecompensas(): MutableLiveData<Usuario>{
        val mutableLiveData = MutableLiveData<Usuario>()
        val userAuth: FirebaseUser? = auth.currentUser
        Log.d("Userauth", userAuth!!.uid)
        servicesFire.getUserById(userAuth!!.uid).observeForever {
            Log.d("UserCorreo", it.correo)
            mutableLiveData.value = it
        }
        return mutableLiveData
    }
}