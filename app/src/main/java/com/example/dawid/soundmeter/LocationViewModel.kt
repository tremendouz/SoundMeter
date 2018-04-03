package com.example.dawid.soundmeter

import android.arch.lifecycle.ViewModel
import android.content.Context

/**
 * Created by daza on 24.03.18.
 */
class LocationViewModel : ViewModel() {
    var locationData: LocationLiveData? = null

    fun getLocation(context: Context): LocationLiveData =
            locationData ?: synchronized(this) {
                locationData ?: LocationLiveData(context).also {
                    locationData = it }
            }

}