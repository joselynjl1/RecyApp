package com.example.recyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyapp.model.Recompensas
import com.example.recyapp.network.FirestoreServices

class RecompensasViewModel: ViewModel() {

    val servicesFire= FirestoreServices()
    //conectar datos de la bd
    fun getRecompensas(): LiveData<MutableList<Recompensas>> {
        val mutableList= MutableLiveData<MutableList<Recompensas>>()
        servicesFire.getRecompensasFirestore().observeForever {
            mutableList.value=it
        }
        return mutableList
    }
}