package com.eslam.vehicletracker.overview.model

/**
 * A model representing the state of the data (LOADING, SUCCESS with data, or ERROR with message)
 */
sealed class State {

    object LOADING : State()

    data class SUCCESS<T>(val data: T) : State()

    data class ERROR(val message: String) : State()
}