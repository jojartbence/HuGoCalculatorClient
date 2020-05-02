package com.jojartbence.utdijkalkulator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jojartbence.repository.Repository

class DepositViewModel : ViewModel() {

    lateinit var recommendedDeposit: MutableLiveData<Double>

    fun updateRecommendedDeposit() {
        recommendedDeposit.value = Repository.getRecommendedDeposit() ?: 0.0
    }

    fun deposit(value: Double) {
        Repository.deposit(value)
        updateRecommendedDeposit()
    }
}
