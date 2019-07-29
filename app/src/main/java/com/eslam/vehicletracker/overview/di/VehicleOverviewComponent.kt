package com.eslam.vehicletracker.overview.di

import com.eslam.vehicletracker.overview.presentation.VehicleOverviewViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [VehicleOverviewModule::class])
interface VehicleOverviewComponent {
    fun inject(overviewViewModel: VehicleOverviewViewModel)
}