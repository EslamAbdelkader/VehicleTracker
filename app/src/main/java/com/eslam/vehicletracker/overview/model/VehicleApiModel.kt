package com.eslam.vehicletracker.overview.model

import com.google.gson.annotations.SerializedName


/**
 * The VehicleApiModel Model that is passed between the domain and the data layers
 */
data class VehicleApiModel(
    @SerializedName("id") val id: String? = null,
    @SerializedName("licensePlate") val licensePlate: String? = null,
    @SerializedName("brand") val brand: String? = null,
    @SerializedName("model") val model: String? = null,
    @SerializedName("nickname") val nickname: String? = null,
    @SerializedName("lastPosition") val lastPosition: TimedPosition? = null
)

/**
 * TimedPosition represents the latitude & longitude of the vehicle and the timestamp it was recorded
 */
data class TimedPosition(
    @SerializedName("timestamp") val timestamp: String? = null,
    @SerializedName("lat") val lat: Double? = null,
    @SerializedName("lng") val lng: Double? = null
)