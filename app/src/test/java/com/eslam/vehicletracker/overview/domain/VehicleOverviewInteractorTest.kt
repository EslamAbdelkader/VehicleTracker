package com.eslam.vehicletracker.overview.domain

import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.nhaarman.mockitokotlin2.whenever
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

private val VEHICLE_A = VehicleApiModel(nickname = "A")
private val VEHICLE_B = VehicleApiModel(nickname = "B")
private val VEHICLE_C = VehicleApiModel(nickname = "C")

/**
 * Unit tests for [VehicleOverviewInteractor]
 */
class VehicleOverviewInteractorTest {

    /**
     * This rule overrides any RxJava scheduling, to be able to run sequentially in unit tests
     */
    @get:Rule
    val schedulerRule = RxSchedulerRule()

    /**
     * The interactor under test
     */
    private val interactor: VehicleOverviewInteractor by lazy { VehicleOverviewInteractor() }

    /**
     * Mocking the repository to initialize the interactor
     */
    @Before
    fun setUp() {
        val repo = Mockito.mock(IVehicleOverviewRepository::class.java)
        whenever(repo.getAllVehicles()).thenReturn(dummyResponse())

        interactor.vehicleOverviewRepository = repo
    }


    /**
     * Passing Single observable with the constant response
     */
    private fun dummyResponse(): Observable<VehicleApiModel> {
        return Observable.just(VEHICLE_B, VEHICLE_A, VEHICLE_C)
    }

    /**
     * Test interactor sort items before passing to view model
     */
    @Test
    fun testInteractorSortingByNickname(){
        val test = interactor.loadData().test()
        test.assertValueAt(0, VEHICLE_A)
        test.assertValueAt(1, VEHICLE_B)
        test.assertValueAt(2, VEHICLE_C)
    }
}