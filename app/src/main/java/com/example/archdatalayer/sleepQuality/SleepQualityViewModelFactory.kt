package com.example.archdatalayer.sleepQuality

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archdatalayer.database.SleepDatabaseDao
import com.example.archdatalayer.sleepTracker.SleepTrackerViewModel

class SleepQualityViewModelFactory(
    private val dataSource: SleepDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            return SleepTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }
}