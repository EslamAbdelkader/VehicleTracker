package com.eslam.vehicletracker.overview.repository

import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.eslam.vehicletracker.overview.network.VehiclesApi
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.reactivex.Observer
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Dummy constant response
 */
private val VEHICLE_API_RESPONSE = List(3) { VehicleApiModel() }

/**
 * Unit test for VehicleOverviewRepository
 */
class VehicleOverviewRepositoryTest {

    @get:Rule
    val schedulerRule = RxSchedulerRule()

    private val repo: VehicleOverviewRepository by lazy { VehicleOverviewRepository() }

    private lateinit var refiner: VehicleRefiner

    /**
     * Mocking the network data source to initialize the Repo
     */
    @Before
    fun setUp() {
        val vehicleApi = Mockito.mock(VehiclesApi::class.java)
        whenever(vehicleApi.getVehicles()).thenReturn(dummyResponse())

        refiner = Mockito.mock(VehicleRefiner::class.java)
        whenever(refiner.apply(any())).thenReturn(VehicleApiModel())

        repo.vehiclesApi = vehicleApi
        repo.vehicleRefiner = refiner
    }

    /**
     * Passing Single observable with the constant response
     */
    private fun dummyResponse(): Single<List<VehicleApiModel>> {
        return Single.create { it.onSuccess(VEHICLE_API_RESPONSE) }
    }

    /**
     * Testing that the Repo is calling the refiner for each item in the list
     */
    @Test
    fun testRepoCallingRefinerOncePerItem() {
        repo.getAllVehicles().test()
        verify(refiner, times(VEHICLE_API_RESPONSE.size)).apply(any())
    }

    /**
     * Testing that the Repo flattens the list into stream of items
     */
    @Test
    fun testRepoFlattensTheListOfVehicles() {
        @Suppress("UNCHECKED_CAST")
        val observer = Mockito.mock(Observer::class.java) as Observer<VehicleApiModel>
        repo.getAllVehicles().subscribe(observer)
        verify(observer, times(VEHICLE_API_RESPONSE.size)).onNext(any())
    }
}