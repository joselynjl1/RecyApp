package com.example.recyapp.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyapp.R
import com.example.recyapp.view.adapter.RecompensasAdapter
import com.example.recyapp.viewmodel.RecompensasViewModel

class RecompensasFragment : Fragment() {

    private val viewModel: RecompensasViewModel by lazy{
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(RecompensasViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recompensas, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerRecompensas(view)
    }
    private fun initRecyclerRecompensas(view: View){
        val recyclerView= view.findViewById<RecyclerView>(R.id.RVrecompensas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getRecompensas().observe(viewLifecycleOwner, Observer {
            recyclerView.adapter=RecompensasAdapter(it)
        })
    }


}