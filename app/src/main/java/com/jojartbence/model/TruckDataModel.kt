package com.jojartbence.model

data class TruckDataModel (
    var licensePlateNumber: String,
    private var distancePerDay: Array<IntArray> = Array(2) {IntArray(3)}
    ) {

    enum class RoadType {
        MOTORWAY, MAINROAD
    }

    enum class DayType {
        TODAY, TOMORROW, AFTERTOMORROW
    }

    fun getDistance(roadType: RoadType, dayType: DayType): Int {
        return distancePerDay[roadType.ordinal][dayType.ordinal]
    }

    fun setDistance(distance: Int, roadType: RoadType, dayType: DayType) {
        distancePerDay[roadType.ordinal][dayType.ordinal] = distance
    }
}