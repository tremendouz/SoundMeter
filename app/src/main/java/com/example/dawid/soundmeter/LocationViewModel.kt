package com.example.dawid.soundmeter

import android.arch.lifecycle.ViewModel
import android.content.Context

/**
 * Created by daza on 24.03.18.
 */
class LocationViewModel: ViewModel() {
    //TODO locationData need to singleton in Kotlin way (below is java-like)
    lateinit var locationData: MyLocationListener

    fun getLocation(context: Context): MyLocationListener {
        locationData = MyLocationListener(context)
        return locationData
    }

//    companion object {
//        private var instance: MyLocationListener? = null
//
//        @Synchronized
//        fun getInstance(context: Context): MyLocationListener{
//            if (instance==null){
//                instance = MyLocationListener(context)
//            }
//            return instance!!
//        }
//    }
}