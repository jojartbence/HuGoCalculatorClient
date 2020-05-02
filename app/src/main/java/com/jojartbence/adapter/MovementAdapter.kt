package com.jojartbence.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jojartbence.utdijkalkulator.R
import com.jojartbence.model.MovementModel
import kotlinx.android.synthetic.main.card_movement.view.*


class MovementAdapter(private val movementList: List<MovementModel>, private val listener: MovementAdapter.OnClickListener?)
    : RecyclerView.Adapter<MovementAdapter.MovementHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementHolder  {
        val movementCard = LayoutInflater.from(parent.context).inflate(R.layout.card_movement, parent, false)
        return MovementHolder(movementCard)
    }

    override fun onBindViewHolder(holder: MovementHolder, position: Int) {
        val movement = movementList[position]
        holder.bind(movement, listener)
    }

    override fun getItemCount(): Int = movementList.size

    class MovementHolder (private val movementCard: View) : RecyclerView.ViewHolder(movementCard) {

        fun bind(movement: MovementModel, listener: OnClickListener?) {
            movementCard.movementFrom.setText(movement.timeFrom.time.toString())
            movementCard.movementTo.setText(movement.timeTo.time.toString())
            movementCard.movementDistance.setText(movement.distance.toString())
            movementCard.movementIsOnMotorway.isChecked = movement.onMotorway

            movementCard.setOnClickListener { listener?.onMovementClick(movement) }
        }
    }

    interface OnClickListener {
        fun onMovementClick(movement: MovementModel)
    }
}