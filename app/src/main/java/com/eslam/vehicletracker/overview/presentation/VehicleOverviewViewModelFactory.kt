package com.eslam.vehicletracker.overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eslam.vehicletracker.application.VehicleTrackerApplication
import com.eslam.vehicletracker.overview.di.DaggerVehicleOverviewComponent

/**
 * Factory class for [VehicleOverviewViewModel]
 */
@Suppress("UNCHECKED_CAST")
class VehicleOverviewViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = VehicleOverviewViewModel()

        val appComponent = VehicleTrackerApplication.context.component
        DaggerVehicleOverviewComponent.builder()
            .appComponent(appComponent)
            .build()
            .inject(viewModel)

        viewModel.loadData()
        return viewModel as T
    }
}