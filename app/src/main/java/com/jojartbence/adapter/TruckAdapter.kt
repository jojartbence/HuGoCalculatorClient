package com.jojartbence.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jojartbence.model.TruckModel
import com.jojartbence.utdijkalkulator.R
import kotlinx.android.synthetic.main.card_truck.view.*


class TruckAdapter (private val truckList: List<TruckModel>, private val listener: OnClickListener?)
    : RecyclerView.Adapter<TruckAdapter.TruckHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruckHolder {
        val truckCard = LayoutInflater.from(parent.context).inflate(R.layout.card_truck, parent, false)
        return TruckHolder(truckCard)
    }

    override fun onBindViewHolder(holder: TruckHolder, position: Int) {
        val truck = truckList[position]
        holder.bind(truck, listener)
    }

    override fun getItemCount(): Int = truckList.size

    class TruckHolder (private val truckCard: View) : RecyclerView.ViewHolder(truckCard) {

        fun bind(truck: TruckModel, listener: OnClickListener?) {
            truckCard.textViewLicensePlateNumber.text = truck.licensePlateNumber
            truckCard.setOnClickListener { listener?.onTruckClick(truck) }
        }
    }

    interface OnClickListener {
        fun onTruckClick(truck: TruckModel)
    }
}
