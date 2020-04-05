package com.jojartbence.model

data class TruckModel (
    var licensePlateNumber: String? = null
    ) {

    var distancePerDay: MutableList<Int> = mutableListOf(0,0,0,0,0,0) // TODO: should use a better structure that also can be handled by firebase
    lateinit var id: String // TODO: it shouldnt be used as a firebase id

    enum class RoadType {
        MOTORWAY, MAINROAD
    }

    enum class DayType {
        TODAY, TOMORROW, AFTERTOMORROW
    }

    fun getDistance(roadType: RoadType, dayType: DayType): Int {
        return distancePerDay[roadType.ordinal*3+dayType.ordinal]
    }

    fun setDistance(distance: Int, roadType: RoadType, dayType: DayType) {
        distancePerDay[roadType.ordinal*3+dayType.ordinal] = distance
    }
}