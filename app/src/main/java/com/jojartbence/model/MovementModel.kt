package com.jojartbence.model

import java.util.*

data class MovementModel (
    val truck: TruckModel,
    val timeFrom: Date,
    val timeTo: Date,
    val distance: Int,
    val onMotorway: Boolean
)