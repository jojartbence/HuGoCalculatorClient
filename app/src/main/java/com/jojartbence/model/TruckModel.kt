package com.jojartbence.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class TruckModel (
    var licensePlateNumber: String? = null
    ): Parcelable
{
}