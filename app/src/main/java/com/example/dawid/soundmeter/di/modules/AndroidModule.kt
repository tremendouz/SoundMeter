package com.example.dawid.soundmeter.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by dawid on 12.03.18.
 */

@Module
class AndroidModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context = application
}