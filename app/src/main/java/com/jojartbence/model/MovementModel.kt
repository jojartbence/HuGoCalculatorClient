package com.jojartbence.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovementModel (
    val truck: TruckModel,
    val timeFrom: Long,
    val timeTo: Long,
    val distance: Int,
    val jCategory: Int,
    val onMotorway: Boolean
) : Parcelable