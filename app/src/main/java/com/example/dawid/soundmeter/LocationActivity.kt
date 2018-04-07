package com.example.dawid.soundmeter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import com.firebase.geofire.GeoFire
import com.firebase.geofire.GeoLocation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class LocationActivity : AppCompatActivity() {
    val TAG = LocationActivity::class.java.simpleName
    val DB_REFERENCE = FirebaseDatabase.getInstance().reference
    val GEOFIRE_REF = GeoFire(DB_REFERENCE)

    lateinit var elobutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)


        elobutton = findViewById(R.id.elo_button)
        elobutton.setOnClickListener {
            val itemId = DB_REFERENCE.child("measurements").push().key
            DB_REFERENCE.child("measurements").child(itemId).child("noise").setValue(50)
            DB_REFERENCE.child("measurements").child(itemId).child("userID").setValue(50)

            val geofire = GeoFire(DB_REFERENCE.child("measurementLocation"))
            val firelistener = object : GeoFire.CompletionListener{
                override fun onComplete(key: String?, error: DatabaseError?) {
                    Log.d("ELO", "ELO 420 wysylamy geo FIRE")
                }
            }
            geofire.setLocation(itemId, GeoLocation(1.0, 2.0), firelistener)
        }

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



