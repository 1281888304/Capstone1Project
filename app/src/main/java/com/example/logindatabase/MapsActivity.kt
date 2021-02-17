package com.example.logindatabase

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val washington = LatLng(32.6099, -85.4808)
        val california = LatLng(36.778,-119.4179)
        val texas = LatLng(31.9686,-99.9018)
        val Georgia = LatLng(32.1656,-82.9001)
        val taiWan = LatLng(23.6978,120.9605)
        mMap.addMarker(MarkerOptions().position(washington).title("Marker in Washington"))
        mMap.addMarker(MarkerOptions().position(california).title("Marker in California"))
        mMap.addMarker(MarkerOptions().position(texas).title("Marker in Texas"))
        mMap.addMarker(MarkerOptions().position(Georgia).title("Marker in Georgia"))
        mMap.addMarker(MarkerOptions().position(taiWan).title("Marker in Chinese TaiBe"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(washington,15f))
    }
}