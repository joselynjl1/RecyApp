package com.example.recyapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.recyapp.R
import com.example.recyapp.model.Recompensas
import com.example.recyapp.model.Usuario
import com.example.recyapp.network.FirestoreServices
import com.example.recyapp.viewholders.VHRecompensas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RecompensasAdapter(val recompensas: List<Recompensas>) : RecyclerView.Adapter<VHRecompensas>() {

    val firestoreServices: FirestoreServices = FirestoreServices()
    var userLiveData = MutableLiveData<Usuario>()
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHRecompensas {
        val user: FirebaseUser? = auth.currentUser
        firestoreServices.getUserById(user!!.uid).observeForever {
            userLiveData.value = it
        }

        val layoutInflater=LayoutInflater.from(parent.context)
        val vista = VHRecompensas(layoutInflater.inflate(R.layout.itemrecompensas, parent, false), userLiveData)
        return vista
    }

    override fun getItemCount(): Int {
        return recompensas.size
    }

    override fun onBindViewHolder(holder: VHRecompensas, position: Int) {
        val item=recompensas[position]
        holder.render(item)
    }

}