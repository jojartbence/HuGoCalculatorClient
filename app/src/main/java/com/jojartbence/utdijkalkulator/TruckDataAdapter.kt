package com.jojartbence.utdijkalkulator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jojartbence.model.TruckDataModel
import kotlinx.android.synthetic.main.card_truck_data.view.*


class TruckDataAdapter constructor(private var truckDataList: List<TruckDataModel>,
                              private val listener: OnClickListener?
) : RecyclerView.Adapter<TruckDataAdapter.MainHolder>() {

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
        val truckData = truckDataList[holder.adapterPosition]
        holder.bind(truckData, listener)
    }

    override fun getItemCount(): Int = truckDataList.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(truckData: TruckDataModel, listener: OnClickListener?) {
            itemView.textViewLicensePlateNumber.text = truckData.licensePlateNumber
            itemView.textViewTodayMotorway.setText(truckData.getDistance(TruckDataModel.RoadType.MOTORWAY, TruckDataModel.DayType.TODAY).toString())
            itemView.textViewTomorrowMotorway.setText(truckData.getDistance(TruckDataModel.RoadType.MOTORWAY, TruckDataModel.DayType.TOMORROW).toString())
            itemView.textViewAfterTomorrowMotorway.setText(truckData.getDistance(TruckDataModel.RoadType.MOTORWAY, TruckDataModel.DayType.AFTERTOMORROW).toString())
            itemView.textViewTodayMainRoad.setText(truckData.getDistance(TruckDataModel.RoadType.MAINROAD, TruckDataModel.DayType.TODAY).toString())
            itemView.textViewTomorrowMainRoad.setText(truckData.getDistance(TruckDataModel.RoadType.MAINROAD, TruckDataModel.DayType.TOMORROW).toString())
            itemView.textViewAfterTomorrowMainRoad.setText(truckData.getDistance(TruckDataModel.RoadType.MAINROAD, TruckDataModel.DayType.AFTERTOMORROW).toString())

            itemView.setOnClickListener { listener?.onTruckDataClick(truckData) }
        }
    }

    interface OnClickListener {
        fun onTruckDataClick(truckData: TruckDataModel)
    }
}
