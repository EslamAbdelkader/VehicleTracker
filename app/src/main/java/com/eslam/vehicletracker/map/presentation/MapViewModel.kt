package com.eslam.vehicletracker.map.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eslam.vehicletracker.overview.model.VehicleUIModel

/**
 * ViewModel for the Maps Activity, holding and passing the vehicle's data
 * The class is only used to hold the vehicle data through the orientation changes
 * It can be replaced with onSaveInstanceState handling, but is used for simplicity
 */
class MapViewModel : ViewModel() {
    val vehicle = MutableLiveData<VehicleUIModel>()
}