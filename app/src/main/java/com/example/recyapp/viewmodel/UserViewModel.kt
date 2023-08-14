package com.example.recyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyapp.model.Usuario

import com.example.recyapp.network.FirestoreServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserViewModel: ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val servicesFire = FirestoreServices()

    fun updateUserPoints(puntos: Int): MutableLiveData<Usuario>{
        val mutableLiveData = MutableLiveData<Usuario>()
        val userAuth: FirebaseUser? = auth.currentUser
        servicesFire.updatePoints(puntos, userAuth!!.uid).observeForever{
            mutableLiveData.value = it
        }
        return  mutableLiveData
    }

    fun getCurrentUser(): MutableLiveData<Usuario>{
        val mutableLiveData = MutableLiveData<Usuario>()
        val userAuth: FirebaseUser? = auth.currentUser
        servicesFire.getUserById(userAuth!!.uid).observeForever{
            mutableLiveData.value = it
        }
        return  mutableLiveData
    }
}