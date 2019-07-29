package com.eslam.vehicletracker.overview.network

import com.eslam.vehicletracker.overview.model.VehicleApiModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Network data source for vehicles api
 */
interface VehiclesApi {
    /**
     * Retrieves the list of market price values from the charts api
     */
    @GET("vehicles.json")
    fun getVehicles(): Single<List<VehicleApiModel>>
}