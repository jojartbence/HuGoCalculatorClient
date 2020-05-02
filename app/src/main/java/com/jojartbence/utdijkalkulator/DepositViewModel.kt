package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepositViewModel : ViewModel() {

    val recommendedDeposit = MutableLiveData<Double>()

    fun getRecommendedDeposit() {
        val call = Repository.getRecommendedDeposit()
        call.enqueue( object: Callback<Double> {
            override fun onFailure(call: Call<Double>, t: Throwable) {
                recommendedDeposit.value = 0.0
            }

            override fun onResponse(
                call: Call<Double>,
                response: Response<Double>
            ) {
                recommendedDeposit.value = response.body()
            }
        })
    }

    fun deposit(value: Double) {
        val call = Repository.deposit(value)
        call.enqueue(object : Callback<Double> {
            override fun onFailure(call: Call<Double>, t: Throwable) {
                getRecommendedDeposit()
            }

            override fun onResponse(
                call: Call<Double>,
                response: Response<Double>
            ) {
                getRecommendedDeposit()
            }
        })
    }
}
