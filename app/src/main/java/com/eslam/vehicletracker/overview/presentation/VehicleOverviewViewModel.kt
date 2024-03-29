package com.eslam.vehicletracker.overview.presentation

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eslam.vehicletracker.R
import com.eslam.vehicletracker.overview.model.State
import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.eslam.vehicletracker.overview.model.VehicleUIModel
import com.eslam.vehicletracker.util.IStringProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * The view model attached to the Vehicle Overview Activity
 * Responsible for fetching data from the domain layer [IVehicleOverviewInteractor],
 * converting them to [VehicleUIModel] using the help of a [mapper],
 * and acts as the Single Source of Truth for the data and its [state]
 */
class VehicleOverviewViewModel : ViewModel() {

    /**
     * The interactor used to provide the data to the view model
     * after doing any business logic on it (sorting in this case)
     */
    @Inject
    lateinit var interactor: IVehicleOverviewInteractor

    /**
     * The mapper used to convert the domain layer's data [VehicleApiModel]
     * to presentation layer's data [VehicleUIModel]
     */
    @Inject
    lateinit var mapper: Function<VehicleApiModel, VehicleUIModel>

    /**
     * String provider used to extract data from the resources
     */
    @Inject
    lateinit var stringProvider: IStringProvider

    /**
     * Data state at any moment (Loading, Success, Error)
     */
    private val state by lazy { MutableLiveData<State>() }

    /**
     * Used to keep track of disposables and clear them when no longer needed
     */
    private val compositeDisposable by lazy { CompositeDisposable() }

    /**
     * Explicit getter to expose LiveData instead of MutableLiveData
     */
    fun getState(): LiveData<State> = state


    /**
     * Retrieves the data from the interactor, and sets the data and state in the LiveData holders
     */
    fun loadData() {
        val disposable = interactor.loadData()
            .doOnSubscribe { state.value = State.LOADING }
            .map { mapper.apply(it) }
            .toList()
            .subscribeBy(
                onSuccess = { model -> state.value = State.SUCCESS(model) },
                onError = { throwable -> state.value = mapError(throwable) }
            )

        compositeDisposable.add(disposable)
    }

    /**
     * Returns [State.ERROR] with appropriate message based on type of [throwable]
     */
    private fun mapError(throwable: Throwable): State.ERROR {
        val message = when (throwable) {
            is ConnectException, is SocketException, is UnknownHostException ->
                stringProvider.getString(R.string.network_error_message)
            else -> stringProvider.getString(R.string.general_error_message)
        }

        return State.ERROR(message)
    }

    /**
     * Disposes all subscriptions in [compositeDisposable] when ViewModel is finally destroyed
     */
    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
