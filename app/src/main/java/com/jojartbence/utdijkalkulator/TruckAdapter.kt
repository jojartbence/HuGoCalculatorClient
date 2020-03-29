package com.jojartbence.utdijkalkulator

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.TruckRepository
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
            setUiText(truck)
            setOnTextChangedListeners(truck)

            itemView.setOnClickListener { listener?.onTruckClick(truck) }
        }


        private fun setUiText(truck: TruckModel) {
            itemView.textViewLicensePlateNumber.text = truck.licensePlateNumber
            itemView.textViewTodayMotorway.setText(truck.getDistance(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.TODAY).toString())
            itemView.textViewTomorrowMotorway.setText(truck.getDistance(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.TOMORROW).toString())
            itemView.textViewAfterTomorrowMotorway.setText(truck.getDistance(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.AFTERTOMORROW).toString())
            itemView.textViewTodayMainRoad.setText(truck.getDistance(TruckModel.RoadType.MAINROAD, TruckModel.DayType.TODAY).toString())
            itemView.textViewTomorrowMainRoad.setText(truck.getDistance(TruckModel.RoadType.MAINROAD, TruckModel.DayType.TOMORROW).toString())
            itemView.textViewAfterTomorrowMainRoad.setText(truck.getDistance(TruckModel.RoadType.MAINROAD, TruckModel.DayType.AFTERTOMORROW).toString())
        }

        private fun setOnTextChangedListeners(truck: TruckModel) {
            setOnSingleTextChangedListener(truck, itemView.textViewTodayMotorway)
            setOnSingleTextChangedListener(truck, itemView.textViewTomorrowMotorway)
            setOnSingleTextChangedListener(truck, itemView.textViewAfterTomorrowMotorway)
            setOnSingleTextChangedListener(truck, itemView.textViewTodayMainRoad)
            setOnSingleTextChangedListener(truck, itemView.textViewTomorrowMainRoad)
            setOnSingleTextChangedListener(truck, itemView.textViewAfterTomorrowMainRoad)
        }

        private fun setOnSingleTextChangedListener(truck: TruckModel, text: EditText) {

            val distanceDataType = when(text) {
                itemView.textViewTodayMotorway -> Pair(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.TODAY)
                itemView.textViewTomorrowMotorway -> Pair(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.TOMORROW)
                itemView.textViewAfterTomorrowMotorway -> Pair(TruckModel.RoadType.MOTORWAY, TruckModel.DayType.AFTERTOMORROW)
                itemView.textViewTodayMainRoad -> Pair(TruckModel.RoadType.MAINROAD, TruckModel.DayType.TODAY)
                itemView.textViewTomorrowMainRoad -> Pair(TruckModel.RoadType.MAINROAD, TruckModel.DayType.TOMORROW)
                itemView.textViewAfterTomorrowMainRoad -> Pair(TruckModel.RoadType.MAINROAD, TruckModel.DayType.AFTERTOMORROW)
                else -> null
            }

            text.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    distanceDataType?.let {
                        truck.setDistance(s.toString().toInt(), it.first, it.second)
                        TruckRepository.update(truck)
                    }
                }

            })
        }


    }

    interface OnClickListener {
        fun onTruckClick(truck: TruckModel)
    }
}
