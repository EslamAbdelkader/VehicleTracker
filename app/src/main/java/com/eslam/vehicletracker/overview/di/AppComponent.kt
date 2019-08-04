package com.eslam.vehicletracker.overview.di

import android.content.Context
import com.eslam.vehicletracker.util.IStringProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * The component whose lifetime is attached to the application's lifetime
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context(): Context
    fun stringProvider(): IStringProvider

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}