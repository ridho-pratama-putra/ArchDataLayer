package com.example.archdatalayer.sleepQuality

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archdatalayer.database.SleepDatabaseDao
import com.example.archdatalayer.sleepTracker.SleepTrackerViewModel

class SleepQualityViewModelFactory(
    private val nightId: Long,
    private val dataSource: SleepDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepQualityViewModel::class.java)) {
            return SleepQualityViewModel(nightId, dataSource) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }
}