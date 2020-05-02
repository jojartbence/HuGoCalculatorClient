package com.jojartbence.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MovementModel (
    val truck: TruckModel,
    val timeFrom: Date,
    val timeTo: Date,
    val distance: Int,
    val onMotorway: Boolean
) : Parcelable