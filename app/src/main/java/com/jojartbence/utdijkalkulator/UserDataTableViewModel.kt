package com.jojartbence.utdijkalkulator

import androidx.lifecycle.ViewModel
import com.jojartbence.model.TruckDataModel

class UserDataTableViewModel : ViewModel() {

    fun getTruckDataList(): List<TruckDataModel> {
        val truck1 = TruckDataModel("ABC-123")
        val truck2 = TruckDataModel("CDE-678")

        return listOf(truck1, truck2)
    }
}
