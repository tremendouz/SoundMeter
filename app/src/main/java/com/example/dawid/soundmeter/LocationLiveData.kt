package com.example.dawid.soundmeter

import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.os.Looper
import com.example.dawid.soundmeter.utils.LOCATION_REQUEST_FASTEST_INTERVAL
import com.example.dawid.soundmeter.utils.LOCATION_REQUEST_INTERVAL
import com.google.android.gms.location.*

/**
 * Created by daza on 24.03.18.
 */
class LocationLiveData(val context: Context) : LiveData<Location>() {
    lateinit var fusedLocationClient: FusedLocationProviderClient
    val locationRequest by lazy { LocationRequest.create() }


    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val newLocation = locationResult.lastLocation
            value = newLocation
        }
    }


    @SuppressWarnings("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        setupLocationRequest()
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }


    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }


    fun setupLocationRequest() {
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.fastestInterval = LOCATION_REQUEST_FASTEST_INTERVAL
        locationRequest.interval = LOCATION_REQUEST_INTERVAL
    }
}