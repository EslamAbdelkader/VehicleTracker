package com.eslam.vehicletracker.overview.presentation

import android.view.View
import android.view.ViewGroup
import androidx.core.util.Consumer
import androidx.recyclerview.widget.RecyclerView
import com.eslam.vehicletracker.R
import com.eslam.vehicletracker.overview.model.VehicleUIModel
import com.eslam.vehicletracker.util.inflate
import kotlinx.android.synthetic.main.row_vehicle.view.*

/**
 * RecyclerView adapter for vehicles for the overview screen
 */
class VehicleAdapter(
    private val vehicles: List<VehicleUIModel>,
    private val consumer: Consumer<VehicleUIModel>
) :
    RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.row_vehicle, false))
    }

    override fun getItemCount(): Int = vehicles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vehicles[position], consumer)
    }

    class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(vehicleUIModel: VehicleUIModel, consumer: Consumer<VehicleUIModel>) {
            view.title.text = vehicleUIModel.name
            view.subtitle.text = vehicleUIModel.model
            view.setOnClickListener { consumer.accept(vehicleUIModel) }
        }

    }
}
