package com.eslam.vehicletracker.overview.model

import androidx.arch.core.util.Function
import javax.inject.Inject

/**
 * A mapper class responsible for mapping [VehicleApiModel] into [VehicleUIModel]
 */
open class VehicleMapper @Inject constructor() : Function<VehicleApiModel, VehicleUIModel> {

    /**
     * mapping [apiModel] of type [VehicleApiModel] to [VehicleUIModel]
     */
    override fun apply(apiModel: VehicleApiModel): VehicleUIModel {

        /**
         * Force unwrapping, since we're sure the data can't be null,
         * because it has passed through the VehicleRefiner first
         */
        return VehicleUIModel(
            id = apiModel.id!!,
            name = apiModel.nickname!!,
            brand = apiModel.brand!!,
            model = apiModel.model!!,
            lat = apiModel.lastPosition!!.lat!!,
            lng = apiModel.lastPosition.lng!!
        )
    }

}