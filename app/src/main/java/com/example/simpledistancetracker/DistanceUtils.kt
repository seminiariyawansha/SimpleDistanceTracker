package com.example.simpledistancetracker

import android.location.Location

object DistanceUtils {

    fun calculateDistance(start: Location, end: Location): Float {
        return start.distanceTo(end)
    }

    fun formatDistance(distanceMeters: Float): String {
        return if (distanceMeters >= 1000) {
            "%.2f km".format(distanceMeters / 1000)
        } else {
            "%.2f m".format(distanceMeters)
        }
    }
}