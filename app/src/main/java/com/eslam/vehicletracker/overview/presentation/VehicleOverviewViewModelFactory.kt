package com.eslam.vehicletracker.overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eslam.vehicletracker.application.VehicleTrackerApplication

/**
 * Factory class for [VehicleOverviewViewModel]
 */
@Suppress("UNCHECKED_CAST")
class VehicleOverviewViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = VehicleOverviewViewModel()
        VehicleTrackerApplication.component.inject(viewModel)
        viewModel.loadData()
        return viewModel as T
    }
}