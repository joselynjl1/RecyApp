package com.example.recyapp.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
        val toolbar: Toolbar=vista.findViewById(R.id.TBHome)

        /*val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)*/

        val activity = activity as MainActivity?
        activity?.setSupportActionBar(toolbar)

        return vista
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }


}