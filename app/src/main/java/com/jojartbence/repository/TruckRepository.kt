package com.jojartbence.repository

import android.content.Context
import com.jojartbence.model.TruckModel

object TruckRepository {
    private lateinit var truckStore: TruckStoreInterface

    fun createDatabase(context: Context, userEmail: String) {
        truckStore = TruckFirebaseStore()
    }


    fun fetchSites(onSitesReady: () -> Unit) {
        truckStore.fetchSites {
            onSitesReady()
        }
    }


    fun findAll(): List<TruckModel> {
        return truckStore.findAll()
    }


    fun create(truck: TruckModel) {
        truckStore.create(truck)
    }


    fun update(truck: TruckModel) {
        truckStore.update(truck)
    }


    fun delete(truck: TruckModel) {
        truckStore.delete(truck)
    }


    fun findById(id: String) : TruckModel? {
        return truckStore.findById(id)
    }


    fun clear() {
        truckStore.clear()
    }

}