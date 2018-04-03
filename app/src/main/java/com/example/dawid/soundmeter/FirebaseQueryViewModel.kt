package com.example.dawid.soundmeter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by daza on 01.04.18.
 */
class FirebaseQueryViewModel: ViewModel() {
    // TODO add argument to getReference
    val DB_REFERENCE : DatabaseReference = FirebaseDatabase.getInstance().getReference("user")
    val liveData: FirebaseQueryLiveData = FirebaseQueryLiveData(DB_REFERENCE)

    fun getDataSnapshotLiveData(): LiveData<DataSnapshot>{
        return liveData
    }
}