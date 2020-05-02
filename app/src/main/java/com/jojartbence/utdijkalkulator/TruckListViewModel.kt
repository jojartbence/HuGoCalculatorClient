package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.Repository

class TruckListViewModel : ViewModel() {

    val truckList = MutableLiveData(Repository.getAllTrucks())

    fun addNewTruck(licensePlateNumber: String) {
        val newTruck = TruckModel(licensePlateNumber)
        Repository.addTruck(newTruck)
        truckList.value = Repository.getAllTrucks()
    }
}
