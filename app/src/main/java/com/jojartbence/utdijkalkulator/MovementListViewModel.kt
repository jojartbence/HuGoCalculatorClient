package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.model.MovementModel
import com.jojartbence.model.TruckModel
import com.jojartbence.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovementListViewModel : ViewModel() {

    lateinit var truck: TruckModel
    val movementList = MutableLiveData<List<MovementModel>>()

    fun attachArguments(truck: TruckModel) {
        this.truck = truck
    }


    fun getAllMovements() {
        val call = Repository.getAllMovements()
        call.enqueue( object: Callback<List<MovementModel>> {
            override fun onFailure(call: Call<List<MovementModel>>, t: Throwable) {
                movementList.value = emptyList()
            }

            override fun onResponse(
                call: Call<List<MovementModel>>,
                response: Response<List<MovementModel>>
            ) {
                movementList.value = response.body()?.filter {
                    it.truck.licensePlateNumber.equals(truck.licensePlateNumber.toString())
                } ?: emptyList()
            }
        })
    }


    fun addMovement(from: Date, to: Date, distance: Int, onMotorway: Boolean) {
        val newMovement = MovementModel(truck, from, to, distance, onMotorway)
        val call = Repository.addMovement(newMovement)
        call.enqueue( object: Callback<MovementModel> {
            override fun onFailure(call: Call<MovementModel>, t: Throwable) {
                getAllMovements()
            }

            override fun onResponse(
                call: Call<MovementModel>,
                response: Response<MovementModel>
            ) {
                getAllMovements()
            }
        })
    }
}
