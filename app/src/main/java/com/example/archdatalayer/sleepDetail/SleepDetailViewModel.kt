package com.example.archdatalayer.sleepDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archdatalayer.database.SleepDatabaseDao
import com.example.archdatalayer.database.SleepNight

/**
 * call database is happen on the main thread
 * idk good practice or not
 */
class SleepDetailViewModel(private val sleepNightKey: Long = 0L, dataSource: SleepDatabaseDao) : ViewModel() {

    val database = dataSource

    private val night = MediatorLiveData<SleepNight>()

    fun getNight() = night

    init {
        night.addSource(database.getNightWithId(sleepNightKey), night::setValue)
    }


    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }
}