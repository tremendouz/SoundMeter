package com.example.dawid.soundmeter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.DataSnapshot

class LocationActivity : AppCompatActivity() {
    val TAG = LocationActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        ViewModelProviders.of(this)
                .get(LocationViewModel::class.java)
                .getLocation(this)
                .observe(this, Observer<Location> { location ->
                    Log.d(TAG, "Latitude: ${location?.latitude} Longitude: ${location?.longitude}")
                })

        ViewModelProviders.of(this)
                .get(FirebaseQueryViewModel::class.java)
                .getDataSnapshotLiveData()
                .observe(this, Observer<DataSnapshot> {
                    dataSnapshot ->  Log.d(TAG, "Data from firebase $dataSnapshot")
                })

    }

}



