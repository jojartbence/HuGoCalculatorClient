package com.jojartbence.utdijkalkulator

import androidx.lifecycle.ViewModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.TruckRepository

class UserDataTableViewModel : ViewModel() {

    fun getTruckList(): List<TruckModel> {
        val truck1 = TruckModel("ABC-123")
        val truck2 = TruckModel("CDE-678")

        return listOf(truck1, truck2)
    }

    fun addNewTruck(licensePlateNumber: String) {
        val newTruck = TruckModel(licensePlateNumber)
        TruckRepository.create(newTruck)
    }
}
