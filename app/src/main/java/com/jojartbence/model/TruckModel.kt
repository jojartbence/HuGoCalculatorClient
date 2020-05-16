package com.jojartbence.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TruckModel (
    val licensePlateNumber: String
): Parcelable
{
}