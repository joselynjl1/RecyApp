package com.example.recyapp.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyapp.model.Materiales
import com.example.recyapp.model.Recompensas
import com.example.recyapp.model.Usuario
import com.google.firebase.firestore.FirebaseFirestore

const val MATERIALES_COLLECTION_NAMES="Materiales"
const val RECOMPENSAS_COLLECTION_NAMES="Recompensas"
const val USUARIOS_COLLECTION_NAME = "Usuario"

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

    //obtener recompensas de bd
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

    fun getUserById(id: String): MutableLiveData<Usuario> {
        val mutableLiveDataUser = MutableLiveData<Usuario>()
        firebaseFirestore.collection(USUARIOS_COLLECTION_NAME)
            .document(id)
            .get()
            .addOnSuccessListener {
                mutableLiveDataUser.value = it.toObject(Usuario::class.java)
            }

        return mutableLiveDataUser
    }

    fun updatePoints(points: Int, currentUserId: String): MutableLiveData<Usuario>{
        val mutableLiveDataUser = MutableLiveData<Usuario>()
        val updateData = mapOf(
            "puntos" to points
        )
        firebaseFirestore.collection(USUARIOS_COLLECTION_NAME)
            .document(currentUserId)
            .update(updateData)
            .addOnSuccessListener {
                getUserById(currentUserId).observeForever {
                    mutableLiveDataUser.value = it
                }
            }

        return mutableLiveDataUser

    }

}