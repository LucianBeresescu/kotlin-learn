package com.example.mykotlin

import android.app.Application
import com.example.mykotlin.di.networkModule
import com.example.mykotlin.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(networkModule, viewModelModules))
        }
    }

}