package com.jojartbence.repository

import android.content.Context
import com.jojartbence.model.TruckDataModel

object TruckDataRepository {
    private lateinit var truckStore: TruckDataStoreInterface

    fun createDatabase(context: Context, userEmail: String) {
        truckStore = TruckFirebaseStore()
    }


    fun fetchSites(onSitesReady: () -> Unit) {
        truckStore.fetchSites {
            onSitesReady()
        }
    }


    fun findAll(): List<TruckDataModel> {
        return truckStore.findAll()
    }


    fun create(truck: TruckDataModel) {
        truckStore.create(truck)
    }


    fun update(truck: TruckDataModel) {
        truckStore.update(truck)
    }


    fun delete(truck: TruckDataModel) {
        truckStore.delete(truck)
    }


    fun findById(id: String) : TruckDataModel? {
        return truckStore.findById(id)
    }


    fun clear() {
        truckStore.clear()
    }

}