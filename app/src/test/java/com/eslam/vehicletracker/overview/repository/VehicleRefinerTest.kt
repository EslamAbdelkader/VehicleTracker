package com.eslam.vehicletracker.overview.repository

import com.eslam.vehicletracker.overview.model.VehicleApiModel
import junit.framework.Assert.assertNotNull
import org.junit.Test

/**
 * Unit tests for [VehicleRefiner]
 */
class VehicleRefinerTest{
    private val refiner = VehicleRefiner()

    /**
     * Test refiner handles all nulls
     */
    @Test
    fun testRefiner(){
        val vehicle = VehicleApiModel()
        val result = refiner.apply(vehicle)

        assertNotNull(result.id)
        assertNotNull(result.brand)
        assertNotNull(result.nickname)
        assertNotNull(result.model)
        assertNotNull(result.licensePlate)
        assertNotNull(result.lastPosition)
        assertNotNull(result.lastPosition!!.lat)
        assertNotNull(result.lastPosition!!.lng)
        assertNotNull(result.lastPosition!!.timestamp)
    }
}