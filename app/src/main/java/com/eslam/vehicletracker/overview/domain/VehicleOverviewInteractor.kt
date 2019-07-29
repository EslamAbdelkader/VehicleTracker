package com.eslam.vehicletracker.overview.domain

import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.eslam.vehicletracker.overview.presentation.IVehicleOverviewInteractor
import com.eslam.vehicletracker.overview.repository.VehicleOverviewRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Vehicle Interactor Implementation.
 * Responsible for handling any business logic, currently it's responsible
 * for sorting data before sending them to the ViewModel
 */
class VehicleOverviewInteractor @Inject constructor() : IVehicleOverviewInteractor {
    @Inject
    lateinit var vehicleOverviewRepository: VehicleOverviewRepository

    /**
     * retrieves an Observable of vehicles from the Repository and sort it
     */
    override fun loadData(): Observable<VehicleApiModel> {
        return vehicleOverviewRepository
            .getAllVehicles()
            .sorted { vehicle, other -> vehicle.nickname!!.compareTo(other.nickname!!) }
    }
}

