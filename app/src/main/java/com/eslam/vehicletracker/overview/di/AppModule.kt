package com.eslam.vehicletracker.overview.di

import com.eslam.vehicletracker.util.IStringProvider
import com.eslam.vehicletracker.util.StringProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideStringProvider(stringProvider: StringProvider): IStringProvider {
        return stringProvider
    }
}