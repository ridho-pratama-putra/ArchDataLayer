package com.example.archdatalayer.sleepTracker

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archdatalayer.database.SleepDatabaseDao
import com.example.archdatalayer.database.SleepNight
import timber.log.Timber

class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
): ViewModel() {

    private var tonight = MutableLiveData<SleepNight?>()

    //    encapsulate mutable livedata
    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality: LiveData<SleepNight> get() = _navigateToSleepQuality

    fun onButtonStartPressed() {
        Timber.i("onButtonStartPressed")
        tonight.value = SleepNight()
    }

    fun onButtonStopPressed() {
        Timber.i("onButtonStopPressed")
        _navigateToSleepQuality.value = tonight.value
    }

    fun onButtonClearPressed() {
        Timber.i("onButtonClearPressed")
    }
    override fun onCleared() {
        super.onCleared()
    }
}