package com.example.recyapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyapp.R
import com.example.recyapp.model.Recompensas
import com.example.recyapp.viewholders.VHRecompensas

class RecompensasAdapter(val recompensas: List<Recompensas>) : RecyclerView.Adapter<VHRecompensas>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHRecompensas {
        val layoutInflater=LayoutInflater.from(parent.context)
        val vista = VHRecompensas(layoutInflater.inflate(R.layout.itemrecompensas, parent, false))
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