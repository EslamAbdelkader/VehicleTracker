package com.eslam.vehicletracker.overview.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.arch.core.util.Function
import com.eslam.vehicletracker.R
import com.eslam.vehicletracker.overview.model.State
import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.eslam.vehicletracker.overview.model.VehicleMapper
import com.eslam.vehicletracker.overview.model.VehicleUIModel
import com.eslam.vehicletracker.util.IStringProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

private const val GENERAL_ERROR_MESSAGE = "general error message"
private const val NETWORK_ERROR_MESSAGE = "network error message"

/**
 * Unit tests for [VehicleOverviewViewModel]
 */
class VehicleOverviewViewModelTest{

    /**
     * For LiveData Instant Execution
     */
    @get:Rule
    var rule = InstantTaskExecutorRule()

    // Initialization of view model and its dependencies
    private val mapper: Function<VehicleApiModel, VehicleUIModel> by lazy { Mockito.mock(VehicleMapper::class.java) }
    private val interactor: IVehicleOverviewInteractor by lazy { Mockito.mock(IVehicleOverviewInteractor::class.java) }
    private val stringProvider: IStringProvider by lazy { Mockito.mock(IStringProvider::class.java) }
    private val viewModel: VehicleOverviewViewModel by lazy {
        VehicleOverviewViewModel().apply {
            mapper = this@VehicleOverviewViewModelTest.mapper
            interactor = this@VehicleOverviewViewModelTest.interactor
            stringProvider = this@VehicleOverviewViewModelTest.stringProvider
        }
    }


    /**
     * Initialize with mocks
     */
    @Before
    fun setUp() {
        whenever(stringProvider.getString(R.string.general_error_message)).thenReturn(GENERAL_ERROR_MESSAGE)
        whenever(stringProvider.getString(R.string.network_error_message)).thenReturn(NETWORK_ERROR_MESSAGE)
    }

    /**
     * Test status [State.LOADING] is returned on subscribe
     */
    @Test
    fun testLoadingStatusOnSubscribe() {
        whenever(interactor.loadData()).thenReturn(Observable.never())

        Assert.assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assertEquals(viewModel.getState().value, State.LOADING)
    }


    /**
     * Test status [State.SUCCESS] is returned on success with model in data
     */
    @Test
    fun testSuccessStatusOnSuccess() {
        val response = VehicleApiModel()                        // Dummy Response
        val model = VehicleUIModel("","","","","",0.0,0.0)      // Dummy Model

        whenever(interactor.loadData()).thenReturn(Observable.just(response))
        whenever(mapper.apply(response)).thenReturn(model)

        Assert.assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assert(viewModel.getState().value is State.SUCCESS<*>)
    }


    /**
     * Test status [State.ERROR] is returned on error with message
     */
    @Test
    fun testErrorStatusOnError() {
        whenever(interactor.loadData()).thenReturn(Observable.error(Error()))

        Assert.assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assert(viewModel.getState().value is State.ERROR)
    }

    /**
     * Test network error message is returned when a connection error is thrown
     */
    @Test
    fun testNetworkErrorMessageOnNetworkError() {
        for (error in listOf(ConnectException(), SocketException(), UnknownHostException())) {

            whenever(interactor.loadData()).thenReturn(Observable.error(error))

            viewModel.loadData()

            assert(viewModel.getState().value is State.ERROR)

            Assert.assertEquals((viewModel.getState().value as State.ERROR).message, NETWORK_ERROR_MESSAGE)
        }
    }

    /**
     * Test general error message is returned when a not-connection-related error is thrown
     */
    @Test
    fun testGeneralErrorMessageOnOtherErrors() {
        whenever(interactor.loadData()).thenReturn(Observable.error(Exception()))

        viewModel.loadData()

        assert(viewModel.getState().value is State.ERROR)

        Assert.assertEquals((viewModel.getState().value as State.ERROR).message, GENERAL_ERROR_MESSAGE)
    }

    /**
     * Test view model uses [mapper] to convert values on success
     */
    @Test
    fun testMapperMethodIsCalledOnSuccess() {
        whenever(interactor.loadData()).thenReturn(Observable.just(VehicleApiModel()))

        viewModel.loadData()

        verify(mapper, times(1)).apply(any())
    }
}