package com.eslam.vehicletracker.map.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eslam.vehicletracker.R
import com.eslam.vehicletracker.overview.model.VehicleUIModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import mumayank.com.airlocationlibrary.AirLocation

/**
 * Map's zooming level
 */
const val ZOOM_LEVEL = 13f

/**
 * A keu in the activity's bundle used for transferring vehicle's data
 */
const val VEHICLE_KEY = "vehicle"

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    /**
     * airLocation is a helper library to simplify the boilerplate of location permission and retrieving
     */
    private lateinit var airLocation: AirLocation
    private lateinit var googleMap: GoogleMap
    private val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(this).get(MapViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        intent?.extras?.getParcelable<VehicleUIModel>(VEHICLE_KEY).let { viewModel.vehicle.value = it }
    }

    /**
     * Asks user for location permission and shows it on map
     */
    private fun enableUserLocation() {
        airLocation = AirLocation(
            activity = this,
            shouldWeRequestPermissions = true,
            shouldWeRequestOptimization = true,
            callbacks = object : AirLocation.Callbacks {
                override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
                    // Do nothing for now
                }

                override fun onSuccess(location: Location) {
                    @SuppressLint("MissingPermission")  // Permission is checked by library
                    googleMap.isMyLocationEnabled = true
                }
            }
        )
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap ?: return

        this.googleMap = googleMap
        enableUserLocation()
        viewModel.vehicle.observe(this, Observer { showDataOnMap(it) })
    }

    /**
     * Shows vehicle's data as a marker on map
     */
    private fun showDataOnMap(vehicle: VehicleUIModel?) {
        vehicle ?: return

        with(googleMap) {
            val latLng = LatLng(vehicle.lat, vehicle.lng)
            moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(latLng).title("${vehicle.name} - ${vehicle.licencePlate}"))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        airLocation.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}