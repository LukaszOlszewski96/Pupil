package com.example.pupil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pupil.fragments.MapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_report_pet_type.*


class ReportPetLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_pet_location)

        //Next screen button
        nextReportBtn.setOnClickListener {nextBtn()}
        //Back screen button
        backArrow.setOnClickListener{backBtn()}

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }





    override fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(-33.852, 151.211)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }



    //Function to back screen
    private fun backBtn(){
        onBackPressed()
    }

    //Function to next screen
    private fun nextBtn(){
        startActivity(Intent(this, ReportPetPhotosActivity :: class.java))
    }

}