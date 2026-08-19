package com.example.simpledistancetracker

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority

object LocationUtils {

    @SuppressLint("MissingPermission")
    fun fetchLocation(
        client: FusedLocationProviderClient,
        onSuccess: (Location) -> Unit,
        onFailure: () -> Unit
    ) {
        client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) onSuccess(location) else onFailure()
            }
            .addOnFailureListener { onFailure() }
    }
}