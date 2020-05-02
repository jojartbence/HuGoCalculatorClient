package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TruckListViewModel : ViewModel() {

    val truckList = MutableLiveData<List<TruckModel>>()

    fun getAllTrucks() {
        val call = Repository.getAllTrucks()
        call.enqueue( object: Callback<List<TruckModel>> {
            override fun onFailure(call: Call<List<TruckModel>>, t: Throwable) {
                truckList.value = emptyList()
            }

            override fun onResponse(
                call: Call<List<TruckModel>>,
                response: Response<List<TruckModel>>
            ) {
                truckList.value = response.body() ?: emptyList()
            }
        })
    }


    fun addNewTruck(licensePlateNumber: String) {
        val newTruck = TruckModel(licensePlateNumber)
        val call = Repository.addTruck(newTruck)
        call.enqueue( object: Callback<TruckModel> {
            override fun onFailure(call: Call<TruckModel>, t: Throwable) {
                getAllTrucks()
            }

            override fun onResponse(
                call: Call<TruckModel>,
                response: Response<TruckModel>
            ) {
                getAllTrucks()
            }
        })
    }
}
