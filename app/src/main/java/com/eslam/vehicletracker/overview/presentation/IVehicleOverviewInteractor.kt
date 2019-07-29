package com.eslam.vehicletracker.overview.presentation

import com.eslam.vehicletracker.overview.model.VehicleApiModel
import io.reactivex.Observable

/**
 * Vehicle Interactor Interface
 */
interface IVehicleOverviewInteractor {
    /**
     * retrieves a Single Observable of vehicles from the Repository
     */
    @Throws(Exception::class)
    fun loadData(): Observable<VehicleApiModel>
}