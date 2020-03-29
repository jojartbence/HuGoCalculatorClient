package com.jojartbence.repository

import com.jojartbence.model.TruckDataModel

interface TruckDataStoreInterface {
    fun findAll(): List<TruckDataModel>
    fun create(truck: TruckDataModel)
    fun update(truck: TruckDataModel)
    fun delete(truck: TruckDataModel)
    fun findById(id: String): TruckDataModel?
    fun clear()
    fun fetchSites(onTruckListReady: () -> Unit)
}