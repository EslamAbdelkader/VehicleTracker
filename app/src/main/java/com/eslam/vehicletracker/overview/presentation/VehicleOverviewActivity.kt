package com.eslam.vehicletracker.overview.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Consumer
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.eslam.vehicletracker.R
import com.eslam.vehicletracker.map.presentation.MapActivity
import com.eslam.vehicletracker.map.presentation.VEHICLE_KEY
import com.eslam.vehicletracker.overview.model.State
import com.eslam.vehicletracker.overview.model.VehicleUIModel
import com.eslam.vehicletracker.util.hideLoading
import com.eslam.vehicletracker.util.showLoading
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_vehicle_overview.*

/**
 * The Home activity, showing the list of vehicles
 */
class VehicleOverviewActivity : AppCompatActivity() {

    private val viewModel: VehicleOverviewViewModel by lazy {
        ViewModelProviders.of(
            this,
            VehicleOverviewViewModelFactory()
        ).get(VehicleOverviewViewModel::class.java)
    }

    /**
     * Initializing views, Observing LiveData components, and setting refresh listener
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_overview)

        viewModel.getState().observe(this, Observer { handleState(it) })

        swipeLayout.setOnRefreshListener { reload() }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    /**
     * Handles the state of the data, either shows the data, the error, or the loading indicator
     */
    @Suppress("UNCHECKED_CAST")
    private fun handleState(state: State) {
        when (state) {
            is State.SUCCESS<*> -> {
                swipeLayout.hideLoading()
                populateData(state.data as List<VehicleUIModel>)
            }
            is State.ERROR -> {
                swipeLayout.hideLoading()
                showError(state.message)
            }
            is State.LOADING -> {
                swipeLayout.showLoading()
            }
        }
    }

    /**
     * Assigns [vehicles] to the [recyclerView]
     */
    private fun populateData(vehicles: List<VehicleUIModel>) {
        recyclerView.adapter = VehicleAdapter(vehicles, Consumer {
            navigateToMapsActivity(it)
        })
    }

    /**
     * Shows error in a snackbar
     */
    private fun showError(message: String) {
        Snackbar.make(swipeLayout, message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
            .setAction(R.string.retry_button_title) { reload() }
            .show()
    }

    /**
     * Reload data
     */
    private fun reload() {
        viewModel.loadData()
    }

    /**
     * Navigate to maps activity, sending the required data on an intent
     */
    private fun navigateToMapsActivity(vehicle: VehicleUIModel) {
        val intent = Intent(this@VehicleOverviewActivity, MapActivity::class.java)
        intent.putExtra(VEHICLE_KEY, vehicle)
        startActivity(intent)
    }
}