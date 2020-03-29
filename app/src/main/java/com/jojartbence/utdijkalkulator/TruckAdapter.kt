package com.jojartbence.utdijkalkulator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jojartbence.model.TruckModel
import kotlinx.android.synthetic.main.card_truck_data.view.*


class TruckAdapter constructor(private var truckList: List<TruckModel>,
                               private val listener: OnClickListener?
) : RecyclerView.Adapter<TruckAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_truck_data,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val truckData = truckList[holder.adapterPosition]
        holder.bind(truckData, listener)
    }

    override fun getItemCount(): Int = truckList.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(truck: TruckModel, listener: OnClickListener?) {
            itemView.textViewLicensePlateNumber.text = truck.licensePlateNumber
            itemView.textViewTodayMotorway.setText(truck.getDistance(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.TODAY).toString())
            itemView.textViewTomorrowMotorway.setText(truck.getDistance(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.TOMORROW).toString())
            itemView.textViewAfterTomorrowMotorway.setText(truck.getDistance(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.AFTERTOMORROW).toString())
            itemView.textViewTodayMainRoad.setText(truck.getDistance(TruckModel.RoadType.MAINROAD, TruckModel.DayType.TODAY).toString())
            itemView.textViewTomorrowMainRoad.setText(truck.getDistance(TruckModel.RoadType.MAINROAD, TruckModel.DayType.TOMORROW).toString())
            itemView.textViewAfterTomorrowMainRoad.setText(truck.getDistance(TruckModel.RoadType.MAINROAD, TruckModel.DayType.AFTERTOMORROW).toString())

            itemView.setOnClickListener { listener?.onTruckClick(truck) }
        }
    }

    interface OnClickListener {
        fun onTruckClick(truck: TruckModel)
    }
}
