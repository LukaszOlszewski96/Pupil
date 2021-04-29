package com.example.pupil.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pupil.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationMark(val longitude: Double, val latitude: Double, val cameraFocus: Boolean)

class MapSmall : Fragment() {

    private var googleMap: GoogleMap? = null
    private var markersToAdd: ArrayList<LocationMark> = arrayListOf()

    private val onMapReadyCallback = OnMapReadyCallback { gMap ->
        googleMap = gMap
        updateMarkPosition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_small_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.small_map) as SupportMapFragment
        mapFragment.getMapAsync(onMapReadyCallback)
    }

    fun addLocationMark(longitude: Double, latitude: Double, focus: Boolean)
    {
        markersToAdd.add(LocationMark(longitude, latitude, focus))
        updateMarkPosition()
    }

    fun updateMarkPosition()
    {
        if(googleMap != null)
        {
            for(locationMark in markersToAdd)
            {
                val location = LatLng(locationMark.latitude, locationMark.longitude)
                googleMap?.addMarker(MarkerOptions().position(location))
                if(locationMark.cameraFocus)
                {
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14.0f))
                }
            }
            markersToAdd.clear()
        }
    }
}