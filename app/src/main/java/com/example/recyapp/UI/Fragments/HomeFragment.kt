package com.example.recyapp.UI.Fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.recyapp.R
import com.example.recyapp.UI.Activities.MainActivity
import com.example.recyapp.viewmodel.RecompensasViewModel
import kotlinx.android.synthetic.main.fragment_home.TVUserPoints
import kotlinx.android.synthetic.main.fragment_home.TVtextopuntos
import org.w3c.dom.Text

class HomeFragment : Fragment() {


    private val viewModel: RecompensasViewModel by lazy{
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(RecompensasViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val vista=inflater.inflate(R.layout.fragment_home, container, false)
        //recupera el id del toolbar
        val toolbar: Toolbar=vista.findViewById(R.id.TBHome)
        val activity = activity as MainActivity?
        activity?.setSupportActionBar(toolbar)

        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val tvUserPoints = view.findViewById<TextView>(R.id.TVUserPoints)
        val tvTextoPuntos = view.findViewById<TextView>(R.id.TVtextopuntos)

        viewModel.getCurrentUserToRecompensas().observe(viewLifecycleOwner) {
            Log.i("HomeFragment", it.nombrecompleto)
            //TVUserPoints.text = it.puntos.toString()
            //TVtextopuntos.isVisible = true
        }
    }

}