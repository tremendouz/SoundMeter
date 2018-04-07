package com.example.dawid.soundmeter

import android.arch.lifecycle.ViewModel
import com.firebase.geofire.GeoFire
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by daza on 04.04.18.
 */
class GeoFireViewModel: ViewModel() {
    val DB_REFERENCE = FirebaseDatabase.getInstance().getReference("user")
    val GEOFIRE_REF = GeoFire(DB_REFERENCE)
}