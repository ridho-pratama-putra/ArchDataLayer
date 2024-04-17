package com.example.archdatalayer.sleepQuality

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.archdatalayer.database.SleepDatabaseDao

class SleepQualityViewModel(
    val database: SleepDatabaseDao,
    val application: Application
): ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }
}