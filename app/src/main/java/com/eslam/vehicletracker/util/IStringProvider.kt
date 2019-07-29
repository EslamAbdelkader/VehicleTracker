package com.eslam.vehicletracker.util

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

/**
 * Helper to provide strings from the android resources
 */
class StringProvider @Inject constructor() : IStringProvider {

    @Inject
    lateinit var context: Context

    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}

/**
 * Helper to provide strings from the android resources
 */
interface IStringProvider {
    fun getString(@StringRes resourceId: Int): String
}
