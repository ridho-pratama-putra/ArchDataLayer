package com.example.archdatalayer.sleepQuality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archdatalayer.database.SleepDatabaseDao
import com.example.archdatalayer.database.SleepNight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SleepQualityViewModel(
    private val nightId: Long,
    private val dataSource: SleepDatabaseDao
): ViewModel() {

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?> get() = _navigateToSleepTracker

    private var viewModelJob = Job()

    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigatingToSleepTracker () {
        _navigateToSleepTracker.value = null
    }

    fun onSetSleepQuality(quality: Int) {
        ioScope.launch {
            val tonight: SleepNight? = dataSource.getTonight()
            tonight!!.endTimeMilli = System.currentTimeMillis()
            tonight.sleepQuality = quality
            dataSource.update(tonight)
        }
        _navigateToSleepTracker.value = true
    }
}