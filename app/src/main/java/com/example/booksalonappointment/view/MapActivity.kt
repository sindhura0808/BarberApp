package com.example.booksalonappointment.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.booksalonappointment.R
import com.example.booksalonappointment.databinding.ActivityMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapActivity : AppCompatActivity() , OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val lat = intent.getDoubleExtra("lat", 0.00)
        val long = intent.getDoubleExtra("long", 0.00)

        // Add a marker in Sydney and move the camera
        val home = LatLng(lat, long)

        mMap.addMarker(
            MarkerOptions().position(home).title("Marker is at home")
                .icon(bitmapFromVector(R.drawable.ic_baseline_add_location_alt_24))
        )
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home, 18f))

    }

    private fun bitmapFromVector(vecResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(this, vecResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}