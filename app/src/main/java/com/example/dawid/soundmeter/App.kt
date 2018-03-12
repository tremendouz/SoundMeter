package com.example.dawid.soundmeter

import android.app.Application
import com.example.dawid.soundmeter.di.ApplicationComponent
import com.example.dawid.soundmeter.di.DaggerApplicationComponent
import com.example.dawid.soundmeter.di.modules.AndroidModule
import dagger.Component

/**
 * Created by dawid on 12.03.18.
 */
class App: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this)).build()
    }
}