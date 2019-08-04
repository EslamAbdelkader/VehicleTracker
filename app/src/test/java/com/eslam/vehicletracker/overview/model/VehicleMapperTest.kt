package com.eslam.vehicletracker.overview.model

import junit.framework.Assert.assertEquals
import org.junit.Test

private const val ID = "id"
private const val MODEL = "model"
private const val LICENCE_PLATE = "licence_plate"
private const val BRAND = "brand"
private const val NAME = "name"
private const val LAT = 0.0
private const val LNG = 0.0

/**
 * Unit tests for [VehicleMapper]
 */
class VehicleMapperTest{

    /**
     * The mapper under test
     */
    private val mapper = VehicleMapper()

    /**
     * Test mapper functionality
     */
    @Test
    fun testMapper(){
        val apiModel = VehicleApiModel(
            id = ID,
            brand = BRAND,
            model = MODEL,
            nickname = NAME,
            licensePlate = LICENCE_PLATE,
            lastPosition = TimedPosition(lat = LAT, lng = LNG)
        )

        val result = mapper.apply(apiModel)

        assertEquals(result.id, ID)
        assertEquals(result.name, NAME)
        assertEquals(result.model, MODEL)
        assertEquals(result.brand, BRAND)
        assertEquals(result.licencePlate, LICENCE_PLATE)
        assertEquals(result.lat, LAT)
        assertEquals(result.lng, LNG)
    }
}