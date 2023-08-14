package com.example.recyapp.viewholders

import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.recyapp.R
import com.example.recyapp.model.Usuario

class VHUsuario(view: View) {

    val usuariosnombres = view.findViewById<TextView>(R.id.ETFullName)
    val puntosU= view.findViewById<TextView>(R.id.TVUserPoints)

    fun render(cuenta: Usuario) {
        usuariosnombres.text = cuenta.nombrecompleto
        puntosU.text=cuenta.puntos.toString()
    }

}