package com.eslam.vehicletracker.overview.di

import javax.inject.Scope

/**
 * Used to create singleton instances per activity scope
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity