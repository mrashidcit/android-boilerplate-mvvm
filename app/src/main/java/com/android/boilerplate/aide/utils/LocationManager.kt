package com.android.boilerplate.aide.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * @author Abdul Rahman
 */
class LocationManager @Inject constructor(@ActivityContext private val context: Context) {

    val currentLocation = MutableLiveData<Location>(null)
    val requestLocationPermission = MutableLiveData(false)
    val satisfyLocationSettings = MutableLiveData(false)
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            currentLocation.value = locationResult.locations[0]
        }
    }

    fun initLocationFlow() {
        if (Utils.isMPlus()) {
            if (checkLocationPermission()) {
                satisfyLocationSettings()
            } else {
                requestLocationPermission()
            }
        } else {
            satisfyLocationSettings()
        }
    }

    private fun checkLocationPermission(): Boolean {
        val p1 = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        )
        val p2 = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return p1 == PackageManager.PERMISSION_GRANTED && p2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestLocationPermission.value = true
    }

    fun resetRequestLocationPermission() {
        requestLocationPermission.value = false
    }

    private fun satisfyLocationSettings() {
        satisfyLocationSettings.value = true
    }

    fun resetSatisfyLocationSettings() {
        satisfyLocationSettings.value = false
    }

    fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        if (checkLocationPermission()) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}