package com.jojartbence.repository

import com.jojartbence.model.TruckModel

interface TruckStoreInterface {
    fun findAll(): List<TruckModel>
    fun create(truck: TruckModel)
    fun update(truck: TruckModel)
    fun delete(truck: TruckModel)
    fun findById(id: String): TruckModel?
    fun clear()
    fun fetchSites(onTruckListReady: () -> Unit)
}