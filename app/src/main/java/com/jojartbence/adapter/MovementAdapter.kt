package com.jojartbence.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jojartbence.utdijkalkulator.R
import com.jojartbence.model.MovementModel
import kotlinx.android.synthetic.main.card_movement.view.*
import java.text.SimpleDateFormat
import java.util.*


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

        private val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        fun bind(movement: MovementModel, listener: OnClickListener?) {
            movementCard.movementFrom.setText("From: " + formatter.format(Date(movement.timeFrom)))
            movementCard.movementTo.setText("To: " + formatter.format(Date(movement.timeTo)))
            movementCard.movementDistance.setText("Distance: " + movement.distance.toString() + " km")
            movementCard.movementJCategory.text = intToJCategory(movement.jCategory)
            movementCard.movementIsOnMotorway.isChecked = movement.onMotorway

            movementCard.setOnClickListener { listener?.onMovementClick(movement) }
        }

        private fun intToJCategory(jCategory: Int): String {
            return when(jCategory) {
                0 -> "J2"
                1 -> "J3"
                2 -> "J4"
                else -> "Parsing error"
            }
        }
    }

    interface OnClickListener {
        fun onMovementClick(movement: MovementModel)
    }
}