package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.model.MovementModel
import com.jojartbence.model.TruckModel
import java.util.*

class MovementListViewModel : ViewModel() {

    lateinit var truck: TruckModel
    lateinit var movementList: MutableLiveData<List<MovementModel>>

    fun attachArguments(truck: TruckModel) {
        this.truck = truck
        this.movementList = MutableLiveData(truck.movements)
    }

    fun addMovement(from: Date, to: Date, distance: Int, onMotorway: Boolean) {
        val newMovement = MovementModel(from, to, distance, onMotorway)
        // TODO: add to repo
    }
}
