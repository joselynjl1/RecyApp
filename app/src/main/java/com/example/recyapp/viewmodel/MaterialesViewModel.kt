package com.example.recyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyapp.model.Materiales
import com.example.recyapp.model.Recompensas
import com.example.recyapp.network.FirestoreServices

class MaterialesViewModel:ViewModel() {
    val servicesFire = FirestoreServices()

    /*
    var spinnerData: List<String>? = null
    var spinnerDataLoaded = false*/
    //conectar datos de la bd
    fun getMateriales():LiveData<MutableList<Materiales>>{
        val mutableList=MutableLiveData<MutableList<Materiales>>()
        servicesFire.getMaterialesFirestore().observeForever {
            mutableList.value=it
        }
        return mutableList
    }

}