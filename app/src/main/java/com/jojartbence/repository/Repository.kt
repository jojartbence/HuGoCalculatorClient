package com.jojartbence.repository

import com.jojartbence.model.MovementModel
import com.jojartbence.model.TruckModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Repository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.109:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(BackendService::class.java)

    fun getAllTrucks(): Call<List<TruckModel>> {
        return service.getAllTrucks()
    }

    fun addTruck(truck: TruckModel) {
        service.addTruck(truck)
    }

    fun getAllMovements(): Call<List<MovementModel>> {
        return service.getAllMovements()
    }

    fun addMovement(movement: MovementModel) {
        service.addMovement(movement)
    }

    fun getRecommendedDeposit(): Call<Double> {
        return service.getRecommendedDeposit()
    }

    fun deposit(value: Double) {
        service.deposit(value)
    }



}