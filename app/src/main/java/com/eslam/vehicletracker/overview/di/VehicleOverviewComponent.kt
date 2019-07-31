package com.eslam.vehicletracker.overview.di

import com.eslam.vehicletracker.overview.presentation.VehicleOverviewViewModel
import dagger.Component

/**
 * The component whose lifetime is attached to the Overview screen activity lifetime
 */
@PerActivity
@Component(dependencies = [AppComponent::class], modules = [VehicleOverviewModule::class])
interface VehicleOverviewComponent {
    fun inject(overviewViewModel: VehicleOverviewViewModel)
}