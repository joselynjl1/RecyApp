package com.example.recyapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyapp.model.Materiales
import com.example.recyapp.model.Recompensas
import com.google.firebase.firestore.FirebaseFirestore

const val MATERIALES_COLLECTION_NAMES="Materiales"
const val RECOMPENSAS_COLLECTION_NAMES="Recompensas"

class FirestoreServices {


    private val firebaseFirestore: FirebaseFirestore= FirebaseFirestore.getInstance()

    //obtener materiales de bd
    fun getMaterialesFirestore(): LiveData<MutableList<Materiales>>{
        val mutableList=MutableLiveData<MutableList<Materiales>>()
        val materiales= mutableListOf<Materiales>()

        firebaseFirestore.collection(MATERIALES_COLLECTION_NAMES).get().addOnSuccessListener {
            if (!it.isEmpty){
                for(data in it.documents){
                    val material: Materiales=data.toObject(Materiales::class.java)!!
                    materiales.add(material)
                }
            }
            mutableList.value=materiales
        }
        return mutableList
    }

    //obtener recopensas de bd
    fun getRecompensasFirestore(): LiveData<MutableList<Recompensas>>{
        val mutableList=MutableLiveData<MutableList<Recompensas>>()
        val recompensas= mutableListOf<Recompensas>()

        firebaseFirestore.collection(RECOMPENSAS_COLLECTION_NAMES).get().addOnSuccessListener {
            if (!it.isEmpty){
                for(data in it.documents){
                    val recompensa: Recompensas=data.toObject(Recompensas::class.java)!!
                    recompensas.add(recompensa)
                }
            }
            mutableList.value=recompensas
        }
        return mutableList
    }

    //obtener solo 1 material de la bd
    fun getMaterial(valor: Int): MutableLiveData<Materiales>{
        var materialSeleccionado = Materiales()
        val mutableLiveData = MutableLiveData<Materiales>()
        firebaseFirestore.collection(MATERIALES_COLLECTION_NAMES).get().addOnSuccessListener {
            if (!it.isEmpty){
                for(data in it.documents){
                    if(data.toObject(Materiales::class.java)!!.valorMaterial == valor){
                        materialSeleccionado = data.toObject(Materiales::class.java)!!
                    }
                }
            }
            mutableLiveData.value = materialSeleccionado
        }
        return mutableLiveData
    }



}