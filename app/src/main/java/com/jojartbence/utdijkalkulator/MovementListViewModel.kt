package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.model.MovementModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.Repository
import java.util.*

class MovementListViewModel : ViewModel() {

    lateinit var truck: TruckModel
    lateinit var movementList: MutableLiveData<List<MovementModel>>

    fun attachArguments(truck: TruckModel) {
        this.truck = truck
        this.movementList = MutableLiveData(getFilteredMovementList())
    }

    fun addMovement(from: Date, to: Date, distance: Int, onMotorway: Boolean) {
        val newMovement = MovementModel(truck, from, to, distance, onMotorway)
        Repository.addMovement(newMovement)
        movementList = MutableLiveData(getFilteredMovementList())
    }

    private fun getFilteredMovementList(): List<MovementModel> {
        return Repository.getAllMovements().filter {
            it.truck.licensePlateNumber.equals(truck.licensePlateNumber.toString())
        }
    }
}
