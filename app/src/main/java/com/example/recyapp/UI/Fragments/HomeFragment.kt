package com.example.recyapp.UI.Fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.example.recyapp.R
import com.example.recyapp.UI.Activities.MainActivity

class HomeFragment : Fragment() {

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

}