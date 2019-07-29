package com.eslam.vehicletracker.overview.di

import android.content.Context
import androidx.arch.core.util.Function
import com.eslam.vehicletracker.util.IStringProvider
import com.eslam.vehicletracker.util.StringProvider
import com.eslam.vehicletracker.overview.domain.IVehicleOverviewRepository
import com.eslam.vehicletracker.overview.domain.VehicleOverviewInteractor
import com.eslam.vehicletracker.overview.model.VehicleApiModel
import com.eslam.vehicletracker.overview.model.VehicleMapper
import com.eslam.vehicletracker.overview.model.VehicleUIModel
import com.eslam.vehicletracker.overview.network.VehiclesApi
import com.eslam.vehicletracker.overview.network.retrofit
import com.eslam.vehicletracker.overview.presentation.IVehicleOverviewInteractor
import com.eslam.vehicletracker.overview.repository.VehicleOverviewRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class VehicleOverviewModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideVehiclesApi(): VehiclesApi = retrofit.create(VehiclesApi::class.java)

    @Provides
    fun provideOverviewRepository(repository: VehicleOverviewRepository): IVehicleOverviewRepository {
        return repository
    }

    @Provides
    fun provideOverviewInteractor(interactor:  VehicleOverviewInteractor): IVehicleOverviewInteractor {
        return interactor
    }

    @Provides
    fun provideMapper(mapper: VehicleMapper): Function<VehicleApiModel, VehicleUIModel> {
        return mapper
    }

    @Provides
    fun provideStringProvider(stringProvider: StringProvider): IStringProvider {
        return stringProvider
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}