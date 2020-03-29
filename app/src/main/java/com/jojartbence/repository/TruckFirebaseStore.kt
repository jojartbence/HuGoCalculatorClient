package com.jojartbence.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jojartbence.model.TruckDataModel

class TruckFirebaseStore: TruckDataStoreInterface {

    private val trucks = ArrayList<TruckDataModel>()
    lateinit var userId: String
    private val database = FirebaseDatabase.getInstance()


    companion object {
        var persistenceAlreadySet: Boolean = false

        fun setPersistenceEnabled() {
            if (!persistenceAlreadySet) {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true)
                persistenceAlreadySet = true
            }
        }
    }


    init {
        setPersistenceEnabled()
    }


    override fun findAll(): List<TruckDataModel> {
        return trucks
    }


    override fun findById(id: String): TruckDataModel? {
        return trucks.find { truck -> truck.id == id }
    }


    override fun create(truck: TruckDataModel) {
        val key = database.reference.child("users").child(userId).child("sites").push().key
        key?.let {
            truck.id = key
            trucks.add(truck)
            database.reference.child("users").child(userId).child("sites").child(key).setValue(truck)
        }
    }


    override fun update(truck: TruckDataModel) {
        val foundTruck: TruckDataModel? = trucks.find { truck -> truck.id == truck.id }
        if (foundTruck != null) {

            TODO("Update data in repository")
        }

        truck.id?.let {
            database.reference.child("users").child(userId).child("sites").child(it).setValue(truck)
        }

    }


    override fun delete(truck: TruckDataModel) {
        truck.id?.let {
            database.reference.child("users").child(userId).child("sites").child(it).removeValue()
        }
        trucks.remove(trucks.find { it.id == truck.id })
    }


    override fun clear() {
        trucks.clear()
    }


    override fun fetchSites(onTruckListReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNullTo(trucks) { it.getValue<TruckDataModel>(TruckDataModel::class.java) }
                onTruckListReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        trucks.clear()
        database.reference.child("users").child(userId).child("sites").addListenerForSingleValueEvent(valueEventListener)
    }
}