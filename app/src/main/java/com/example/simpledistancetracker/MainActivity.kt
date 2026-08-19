package com.example.simpledistancetracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import android.location.Location

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: com.google.android.gms.location.FusedLocationProviderClient
    private var startLocation: Location? = null

    private lateinit var btnSetStart: Button
    private lateinit var btnSetEnd: Button
    private lateinit var tvStart: TextView
    private lateinit var tvEnd: TextView
    private lateinit var tvDistance: TextView

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (!granted) {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        btnSetStart = findViewById(R.id.btnSetStart)
        btnSetEnd = findViewById(R.id.btnSetEnd)
        tvStart = findViewById(R.id.tvStart)
        tvEnd = findViewById(R.id.tvEnd)
        tvDistance = findViewById(R.id.tvDistance)

        btnSetStart.setOnClickListener { handleSetStart() }
        btnSetEnd.setOnClickListener { handleSetEnd() }
    }

    private fun handleSetStart() {
        if (!PermissionUtils.hasLocationPermission(this)) {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }
        LocationUtils.fetchLocation(
            fusedLocationClient,
            onSuccess = { location ->
                startLocation = location
                tvStart.text = "Start: ${location.latitude}, ${location.longitude}"
            },
            onFailure = {
                Toast.makeText(this, "Couldn't get location, try again", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun handleSetEnd() {
        val start = startLocation
        if (start == null) {
            Toast.makeText(this, "Set a start point first!", Toast.LENGTH_SHORT).show()
            return
        }
        if (!PermissionUtils.hasLocationPermission(this)) {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }
        LocationUtils.fetchLocation(
            fusedLocationClient,
            onSuccess = { location ->
                tvEnd.text = "End: ${location.latitude}, ${location.longitude}"
                val distance = DistanceUtils.calculateDistance(start, location)
                tvDistance.text = "Distance: ${DistanceUtils.formatDistance(distance)}"
            },
            onFailure = {
                Toast.makeText(this, "Couldn't get location, try again", Toast.LENGTH_SHORT).show()
            }
        )
    }
}