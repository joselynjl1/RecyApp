package com.example.recyapp.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyapp.R
import com.example.recyapp.model.Materiales

class VHMateriales(view: View): RecyclerView.ViewHolder(view) {
    val nombreMaterial=view.findViewById<TextView>(R.id.TVNombreMaterial)
    val valorMaterial=view.findViewById<TextView>(R.id.TVValorMateriales)
    val imagenurl=view.findViewById<ImageView>(R.id.IVImagenMaterial)

    fun render(materiales:Materiales){
        nombreMaterial.text=materiales.nombreMaterial
        valorMaterial.text= materiales.valorMaterial.toString()
        Glide.with(imagenurl.context).load(materiales.imagenurlMaterial).into(imagenurl)
    }

}