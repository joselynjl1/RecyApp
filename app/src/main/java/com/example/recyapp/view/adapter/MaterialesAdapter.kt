package com.example.recyapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyapp.R
import com.example.recyapp.model.Materiales
import com.example.recyapp.viewholders.VHMateriales

class MaterialesAdapter(val materiales: List<Materiales>): RecyclerView.Adapter<VHMateriales>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHMateriales {
        val layoutInflater=LayoutInflater.from(parent.context)
        return VHMateriales(layoutInflater.inflate(R.layout.itemmateriales, parent, false))
    }

    override fun getItemCount(): Int {
        return materiales.size
    }

    override fun onBindViewHolder(holder: VHMateriales, position: Int) {
        val item=materiales[position]
        holder.render(item)
    }

}