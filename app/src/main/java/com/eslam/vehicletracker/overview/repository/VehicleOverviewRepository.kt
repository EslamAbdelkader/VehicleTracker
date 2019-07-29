package com.eslam.vehicletracker.overview.repository

import com.eslam.vehicletracker.overview.domain.IVehicleOverviewRepository
import com.eslam.vehicletracker.overview.domain.VehicleRefiner
import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.eslam.vehicletracker.overview.network.VehiclesApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Responsible for fetching the data from data sources,
 * so far it only fetches from network data source, but it can also be responsible
 * for fetching from local data source, and synchronization between both sources
 */
class VehicleOverviewRepository @Inject constructor() : IVehicleOverviewRepository {

    @Inject
    lateinit var vehiclesApi: VehiclesApi

    @Inject
    lateinit var vehicleRefiner: VehicleRefiner

    /**
     * Fetches all vehicles from network data source ([VehiclesApi])
     * and refines data for nullability before sending to domain layer
     */
    override fun getAllVehicles(): Observable<VehicleApiModel> {
        return vehiclesApi
            .getVehicles()
            .flattenAsObservable { it }
            .map { vehicleRefiner.apply(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}