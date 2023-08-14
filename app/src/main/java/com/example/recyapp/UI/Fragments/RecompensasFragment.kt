package com.example.recyapp.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.recyapp.R
import com.example.recyapp.view.adapter.RecompensasAdapter
import com.example.recyapp.viewmodel.RecompensasViewModel
import kotlinx.android.synthetic.main.fragment_recompensas.TVTextoPuntosRecompensa
import kotlinx.android.synthetic.main.fragment_recompensas.TVtotalptRecompensa

class RecompensasFragment : Fragment() {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout

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

        viewModel.getCurrentUserToRecompensas().observe(viewLifecycleOwner) {
            TVtotalptRecompensa.text = it.puntos.toString()
            TVTextoPuntosRecompensa.isVisible = true
        }

        this.swipeRefreshLayout = view.findViewById(R.id.SRLayout)
        this.swipeRefreshLayout.setOnRefreshListener {

            viewModel.getCurrentUserToRecompensas().observe(viewLifecycleOwner) {
                TVtotalptRecompensa.text = it.puntos.toString()
                TVTextoPuntosRecompensa.isVisible = true
            }

            initRecyclerRecompensas(view)

            Handler(Looper.getMainLooper()).postDelayed({
                swipeRefreshLayout.isRefreshing = false
            },1000)

        }


    }
    private fun initRecyclerRecompensas(view: View){
        val recyclerView= view.findViewById<RecyclerView>(R.id.RVrecompensas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getRecompensas().observe(viewLifecycleOwner, Observer {
            recyclerView.adapter=RecompensasAdapter(it)
        })
    }


}