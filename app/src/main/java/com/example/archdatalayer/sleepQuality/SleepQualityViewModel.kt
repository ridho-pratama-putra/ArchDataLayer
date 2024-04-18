package com.example.archdatalayer.sleepQuality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archdatalayer.database.SleepDatabaseDao

class SleepQualityViewModel(
    private val nightId: Long,
    private val dataSource: SleepDatabaseDao
): ViewModel() {

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?> get() = _navigateToSleepTracker

    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigatingToSleepTracker () {
        _navigateToSleepTracker.value = null
    }

    fun onSetSleepQuality(quality: Int) {
        _navigateToSleepTracker.value = true
    }
}