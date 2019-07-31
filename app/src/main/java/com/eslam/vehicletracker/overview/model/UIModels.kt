package com.eslam.vehicletracker.overview.model

import android.os.Parcel
import android.os.Parcelable

/**
 * The Vehicle UI Model that is passed between ViewModel and View
 */
data class VehicleUIModel(
    val id: String,
    val licencePlate: String,
    val name: String,
    val brand: String,
    val model: String,
    val lng: Double,
    val lat: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readDouble(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(licencePlate)
        parcel.writeString(name)
        parcel.writeString(brand)
        parcel.writeString(model)
        parcel.writeDouble(lng)
        parcel.writeDouble(lat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleUIModel> {
        override fun createFromParcel(parcel: Parcel): VehicleUIModel {
            return VehicleUIModel(parcel)
        }

        override fun newArray(size: Int): Array<VehicleUIModel?> {
            return arrayOfNulls(size)
        }
    }
}

