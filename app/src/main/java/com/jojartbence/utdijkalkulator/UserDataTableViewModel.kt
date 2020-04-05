package com.jojartbence.utdijkalkulator

import androidx.lifecycle.ViewModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.TruckRepository

class UserDataTableViewModel : ViewModel() {

    fun getTruckList(): List<TruckModel> {
        return TruckRepository.findAll()
    }

    fun addNewTruck(licensePlateNumber: String) {
        val newTruck = TruckModel(licensePlateNumber)
        TruckRepository.create(newTruck)
    }
}
