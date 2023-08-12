package com.example.recyapp.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recyapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : Fragment(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapaEstaciones) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

     override fun onMapReady(googleMap: GoogleMap) {
        val ubicaciones = listOf(
            LatLng(-9.126987719526605, -78.52198645412764),//MUNICIPALIDAD
            LatLng(-9.121935499089318, -78.5311197894472)//PLAZA MAYOR
        )

        for (ubicacion in ubicaciones) {
            googleMap.addMarker(
                MarkerOptions()
                    .position(ubicacion)
                    .title("Punto de recolección")
            )
        }

        // Mueve la cámara al primer marcador
        if (ubicaciones.isNotEmpty()) {
            val primeraUbicacion = ubicaciones[0]
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(primeraUbicacion, 16f))
        }

        googleMap.uiSettings.isZoomControlsEnabled = true

        googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(), R.raw.estilomapa
            )
        )
    }

}