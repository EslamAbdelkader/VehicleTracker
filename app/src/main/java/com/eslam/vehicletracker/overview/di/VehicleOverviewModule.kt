package com.eslam.vehicletracker.overview.di

import androidx.arch.core.util.Function
import com.eslam.vehicletracker.overview.domain.IVehicleOverviewRepository
import com.eslam.vehicletracker.overview.domain.VehicleOverviewInteractor
import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.eslam.vehicletracker.overview.model.VehicleMapper
import com.eslam.vehicletracker.overview.model.VehicleUIModel
import com.eslam.vehicletracker.overview.network.VehiclesApi
import com.eslam.vehicletracker.overview.network.retrofit
import com.eslam.vehicletracker.overview.presentation.IVehicleOverviewInteractor
import com.eslam.vehicletracker.overview.repository.VehicleOverviewRepository
import com.eslam.vehicletracker.overview.repository.VehicleRefiner
import dagger.Module
import dagger.Provides

/**
 * A module with the lifetime of the Vehicle's overview screen scope
 */
@Module
class VehicleOverviewModule {

    @Provides
    @PerActivity
    fun provideVehiclesApi(): VehiclesApi = retrofit.create(VehiclesApi::class.java)

    @Provides
    @PerActivity
    fun provideOverviewRepository(repository: VehicleOverviewRepository): IVehicleOverviewRepository {
        return repository
    }

    @Provides
    @PerActivity
    fun provideOverviewInteractor(interactor:  VehicleOverviewInteractor): IVehicleOverviewInteractor {
        return interactor
    }

    @Provides
    @PerActivity
    fun provideMapper(mapper: VehicleMapper): Function<VehicleApiModel, VehicleUIModel> {
        return mapper
    }

    @Provides
    @PerActivity
    fun provideRefiner(refiner: VehicleRefiner): Function<VehicleApiModel, VehicleApiModel> {
        return refiner
    }
}