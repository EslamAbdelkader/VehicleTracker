package com.eslam.vehicletracker.overview.repository

import androidx.arch.core.util.Function
import com.eslam.vehicletracker.overview.model.TimedPosition
import com.eslam.vehicletracker.overview.model.VehicleApiModel
import javax.inject.Inject

/**
 * A helper class responsible for eliminating any null possibilities from backend.
 * Should be used by the repository to handle any nulls before storing locally (Not implemented),
 * or passing to the interactor
 */
open class VehicleRefiner @Inject constructor() : Function<VehicleApiModel, VehicleApiModel> {

    /**
     * Eliminate all nulls and replace with better representation
     */
    override fun apply(apiModel: VehicleApiModel): VehicleApiModel {
        val nickname = apiModel.nickname ?: apiModel.licensePlate
        val timeStamp = apiModel.lastPosition?.timestamp ?: ""
        val lat = apiModel.lastPosition?.lat ?: 0.0
        val lng = apiModel.lastPosition?.lng ?: 0.0

        return VehicleApiModel(
            id = apiModel.id ?: "",
            nickname = nickname ?: "",
            brand = apiModel.brand ?: "",
            model = apiModel.model ?: "",
            licensePlate = apiModel.licensePlate ?: "",
            lastPosition = TimedPosition(timeStamp, lat, lng)
        )
    }

}