package com.jojartbence.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TruckModel (
    var licensePlateNumber: String? = null
    ): Parcelable
{
    val movements: MutableList<MovementModel> = mutableListOf()
}