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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyapp.R
import com.example.recyapp.model.Materiales
import com.example.recyapp.model.Recompensas
import com.example.recyapp.model.Usuario

class VHRecompensas(view: View): RecyclerView.ViewHolder(view) {
    val nombreRecompensa=view.findViewById<TextView>(R.id.TVnombreRecompensa)
    val valorRecompensa=view.findViewById<TextView>(R.id.TVvalorRecompensa)
    val imagenurl=view.findViewById<ImageView>(R.id.IVImagenRecompensa)
    val btnCanjeo = view.findViewById<Button>(R.id.BtnCanjear)

    fun render(recompensas: Recompensas){
        val user = Usuario()
        user.nombrecompleto = "Mirko"
        user.puntos = 3000

        nombreRecompensa.text=recompensas.nombreRecompensa
        valorRecompensa.text=recompensas.valorRecompensa.toString()
        Glide.with(imagenurl.context).load(recompensas.imagenurlRecompensa).into(imagenurl)

        if(user.puntos < recompensas.valorRecompensa){
            btnCanjeo.isVisible = false
        }

        btnCanjeo.setOnClickListener {
            showConfirmDialog("¿Desea confirmar el canjeo?", user, recompensas)
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

            user.puntos = user.puntos - recompensa.valorRecompensa

            Toast.makeText(btnAceptar.context,
                "Genial ${user.nombrecompleto}! ${btnAceptar.context.resources.getString(R.string.textConfirmarRecompensa)}. Ahora tu puntaje es: ${user.puntos}",
                Toast.LENGTH_SHORT)
                .show()
            confirmDialog.dismiss()
        }

        btnCancelar.setOnClickListener {
            confirmDialog.dismiss()
        }

        confirmDialog.show()
    }
}