package com.jojartbence.repository

import com.jojartbence.model.MovementModel
import com.jojartbence.model.TruckModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BackendService {

    @GET("/truck/all")
    fun getAllTrucks(): Call<List<TruckModel>>

    @POST("/truck/add")
    fun addTruck(@Body truck: TruckModel): Call<Int>

    @GET("/movement/all")
    fun getAllMovements(): Call<List<MovementModel>>

    @POST("/truck/add")
    fun addMovement(@Body movement: MovementModel): Call<Int>

    @GET("/payment/recommended")
    fun getRecommendedDeposit(): Call<Double>

    @POST("payment/deposit")
    fun deposit(@Body value: Double): Call<Int>
}