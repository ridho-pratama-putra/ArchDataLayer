package com.example.archdatalayer

import android.app.Application
import timber.log.Timber

class ArchDataLayer: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}