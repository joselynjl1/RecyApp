package com.example.recyapp.viewholders

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyapp.R
import com.example.recyapp.model.Materiales
import com.example.recyapp.model.Recompensas
import com.example.recyapp.model.Usuario
import com.example.recyapp.network.FirestoreServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class VHRecompensas(view: View, var user: MutableLiveData<Usuario>): RecyclerView.ViewHolder(view) {
    val nombreRecompensa=view.findViewById<TextView>(R.id.TVnombreRecompensa)
    val valorRecompensa=view.findViewById<TextView>(R.id.TVvalorRecompensa)
    val imagenurl=view.findViewById<ImageView>(R.id.IVImagenRecompensa)
    val btnCanjeo = view.findViewById<Button>(R.id.BtnCanjear)
    val firestoreServices = FirestoreServices()
    private lateinit var auth: FirebaseAuth

    fun render(recompensa: Recompensas){

        nombreRecompensa.text=recompensa.nombreRecompensa
        valorRecompensa.text=recompensa.valorRecompensa.toString()
        Glide.with(imagenurl.context).load(recompensa.imagenurlRecompensa).into(imagenurl)

        user.observeForever {
            if(user.value!!.puntos < recompensa.valorRecompensa){
                btnCanjeo.isVisible = false
            }
        }

        btnCanjeo.setOnClickListener {
            showConfirmDialog("¿Desea confirmar el canjeo?", user.value!!, recompensa)
        }

    }

    fun showConfirmDialog(message:String, user: Usuario, recompensa: Recompensas){
        val confirmDialog = Dialog(btnCanjeo.context)
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        confirmDialog.setCancelable(false)
        confirmDialog.setContentView(R.layout.custom_confirm_dialog)
        confirmDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnAceptar = confirmDialog.findViewById<Button>(R.id.btnConfirmar)
        val btnCancelar = confirmDialog.findViewById<Button>(R.id.btnCancelar)

        btnAceptar.setOnClickListener {
            val userAuth: FirebaseUser? = auth.currentUser
            firestoreServices
                .updatePoints(user.puntos - recompensa.valorRecompensa, userAuth!!.uid)
                .observeForever {
                    Toast.makeText(btnAceptar.context,
                        "Ahora tu puntaje es: ${it.puntos}",
                        Toast.LENGTH_SHORT)
                        .show()
                    confirmDialog.dismiss()
                }
        }

        btnCancelar.setOnClickListener {
            confirmDialog.dismiss()
        }

        confirmDialog.show()
    }
}