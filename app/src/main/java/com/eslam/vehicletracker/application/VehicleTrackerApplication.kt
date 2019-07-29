package com.eslam.vehicletracker.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.eslam.vehicletracker.overview.di.DaggerVehicleOverviewComponent
import com.eslam.vehicletracker.overview.di.VehicleOverviewComponent
import com.eslam.vehicletracker.overview.di.VehicleOverviewModule

/**
 * Application class
 */
class VehicleTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        component = DaggerVehicleOverviewComponent.builder()
            .vehicleOverviewModule(VehicleOverviewModule(context))
            .build()
    }

    companion object {
        /**
         * Not leaking, keeping the application context static, to provide for Dependency Injection
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        lateinit var component: VehicleOverviewComponent
    }
}