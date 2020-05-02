package com.jojartbence.repository

import com.jojartbence.model.MovementModel
import com.jojartbence.model.TruckModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Repository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service = retrofit.create(BackendService::class.java)

    fun getAllTrucks(): List<TruckModel> {
        return service.getAllTrucks().execute().body() ?: emptyList()
    }

    fun addTruck(truck: TruckModel) {
        service.addTruck(truck)
    }

    fun getAllMovements(): List<MovementModel> {
        return service.getAllMovements().execute().body() ?: emptyList()
    }

    fun addMovement(movement: MovementModel) {
        service.addMovement(movement)
    }



}