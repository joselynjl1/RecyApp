package com.example.recyapp.UI.Fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyapp.R
import com.example.recyapp.view.adapter.MaterialesAdapter
import com.example.recyapp.viewmodel.MaterialesViewModel

class MaterialesReciclablesFragment : Fragment() {

    private val viewModel: MaterialesViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(MaterialesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_materiales_reciclables, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.RVMateriales)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getMateriales().observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = MaterialesAdapter(it)
        })
    }

    /*
    override fun onMaterialClicked(materiales: Materiales, position: Int) {
        val bundle= bundleOf("Materiales" to materiales)
        findNavController().navigate(R.id.materialesReciclablesDetalleDFragment,bundle)
    }*/

}