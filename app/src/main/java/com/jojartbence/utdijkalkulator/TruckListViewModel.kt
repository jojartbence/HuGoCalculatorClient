package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.TruckRepository

class TruckListViewModel : ViewModel() {

    val truckList = MutableLiveData(TruckRepository.findAll())

    fun addNewTruck(licensePlateNumber: String) {
        val newTruck = TruckModel(licensePlateNumber)
        // TODO: add to repo
    }
}
