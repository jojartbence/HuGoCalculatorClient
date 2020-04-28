package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.TruckRepository

class TruckListViewModel : ViewModel() {

    val truckList = MutableLiveData<List<TruckModel>>(TruckRepository.findAll())

    fun addNewTruck(licensePlateNumber: String) {
        val newTruck = TruckModel(licensePlateNumber)
        TruckRepository.create(newTruck)

        truckList.value = TruckRepository.findAll()
    }
}
