package com.eslam.vehicletracker.overview.model

/**
 * A model representing the state of the data (LOADING, SUCCESS with data, or ERROR with message)
 */
sealed class State {

    /**
     * Represents a loading state
     */
    object LOADING : State()

    /**
     * Represents a success state, with the associated data
     */
    data class SUCCESS<T>(val data: T) : State()

    /**
     * Represents an error state, with the associated error message
     */
    data class ERROR(val message: String) : State()
}