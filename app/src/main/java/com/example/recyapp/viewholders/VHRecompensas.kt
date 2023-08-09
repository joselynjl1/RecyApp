package com.example.recyapp.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyapp.R
import com.example.recyapp.model.Materiales
import com.example.recyapp.model.Recompensas

class VHRecompensas(view: View): RecyclerView.ViewHolder(view) {
    val nombreRecompensa=view.findViewById<TextView>(R.id.TVnombreRecompensa)
    val valorRecompensa=view.findViewById<TextView>(R.id.TVvalorRecompensa)
    val imagenurl=view.findViewById<ImageView>(R.id.IVImagenRecompensa)

    fun render(recompensas: Recompensas){
        nombreRecompensa.text=recompensas.nombreRecompensa
        valorRecompensa.text=recompensas.valorRecompensa.toString()
        Glide.with(imagenurl.context).load(recompensas.imagenurlRecompensa).into(imagenurl)
    }
}