package com.example.archdatalayer.sleepTracker

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.archdatalayer.database.SleepDatabaseDao
import timber.log.Timber

class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    val application: Application
): ViewModel() {

    fun onButtonStartPressed() {
        Timber.i("onButtonStartPressed")
    }

    fun onButtonStopPressed() {
        Timber.i("onButtonStopPressed")
    }

    fun onButtonClearPressed() {
        Timber.i("onButtonClearPressed")
    }
    override fun onCleared() {
        super.onCleared()
    }
}