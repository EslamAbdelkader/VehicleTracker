package com.eslam.vehicletracker.overview.domain

import com.eslam.vehicletracker.overview.model.VehicleApiModel
import io.reactivex.Observable

/**
 * Interface for Vehicle Repository, handling CRUD operations on vehicle
 * Currently, the only needed operation is get All
 */
interface IVehicleOverviewRepository {
    /**
     * Fetches all vehicles
     */
    fun getAllVehicles(): Observable<VehicleApiModel>
}
