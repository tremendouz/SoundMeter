package com.example.dawid.soundmeter

import android.app.Application
import com.example.dawid.soundmeter.di.ApplicationComponent
import com.example.dawid.soundmeter.di.DaggerApplicationComponent
import com.example.dawid.soundmeter.di.modules.AndroidModule
import com.google.firebase.FirebaseApp

/**
 * Created by dawid on 12.03.18.
 */
class App: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        applicationComponent = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this)).build()
    }
}