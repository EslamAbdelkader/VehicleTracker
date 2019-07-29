package com.eslam.vehicletracker.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Shows a SwipeRefreshLayout refreshing marker
 */
fun SwipeRefreshLayout.showLoading() {
    isRefreshing = true
}

/**
 * Hides a SwipeRefreshLayout refreshing marker
 */
fun SwipeRefreshLayout.hideLoading() {
    isRefreshing = false
}

/**
 * Inflate new item from a ViewGroup
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
