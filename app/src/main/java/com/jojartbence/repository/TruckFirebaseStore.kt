package com.jojartbence.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jojartbence.model.TruckModel

class TruckFirebaseStore: TruckStoreInterface {

    private val trucks = ArrayList<TruckModel>()
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


    override fun findAll(): List<TruckModel> {
        return trucks
    }


    override fun findById(id: String): TruckModel? {
        return trucks.find { truck -> truck.id == id }
    }


    override fun create(truck: TruckModel) {
        val key = database.reference.child("users").child(userId).child("trucks").push().key
        key?.let {
            truck.id = key
            trucks.add(truck)
            database.reference.child("users").child(userId).child("trucks").child(key).setValue(truck)
        }
    }


    override fun update(truck: TruckModel) {

        truck.id?.let {
            database.reference.child("users").child(userId).child("trucks").child(it).setValue(truck)
        }

    }


    override fun delete(truck: TruckModel) {
        truck.id?.let {
            database.reference.child("users").child(userId).child("trucks").child(it).removeValue()
        }
        trucks.remove(trucks.find { it.id == truck.id })
    }


    override fun clear() {
        trucks.clear()
    }


    override fun fetchTrucks(onTruckListReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNullTo(trucks) { it.getValue<TruckModel>(TruckModel::class.java) }
                onTruckListReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        trucks.clear()
        database.reference.child("users").child(userId).child("trucks").addListenerForSingleValueEvent(valueEventListener)
    }
}