package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.helpers.LicensePlateNumberValidityChecker
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NumberFormatException

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


    fun addNewTruck(licensePlateNumber: String, emissionCategoryString: String) {

        if (!LicensePlateNumberValidityChecker.isValidLicensePlateNumber(licensePlateNumber)) {
            throw NumberFormatException()
        }

        val emissionCategory = emissionCategoryString.toInt()
        if (emissionCategory < 0 || emissionCategory > 2) {
            throw NumberFormatException()
        }

        val newTruck = TruckModel(null, licensePlateNumber, emissionCategory)
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
