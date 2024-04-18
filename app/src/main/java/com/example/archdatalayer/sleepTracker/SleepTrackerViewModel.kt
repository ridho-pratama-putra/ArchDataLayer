package com.example.archdatalayer.sleepTracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archdatalayer.database.SleepDatabaseDao
import com.example.archdatalayer.database.SleepNight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
): ViewModel() {

    private var tonight = MutableLiveData<SleepNight?>()

    // encapsulate mutable livedata
    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality: LiveData<SleepNight> get() = _navigateToSleepQuality

    // to manage coroutine (seperti cancel all coroutine saat viewmodel nolonger use dan destroyed)
    private var viewModelJob = Job()

    // scope determine what thread the coroutine will run on and its need to know about the job
    // this means coroutine launch in the ui scope will run on main thread
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun onButtonStartPressed() {
        Timber.i("onButtonStartPressed")
        ioScope.launch {
            delay(5000)

            Timber.i("onstarttracking launch")
            val newNight = SleepNight()

            database.save(newNight)
        }

        // Cannot invoke setValue on a background thread so I use main thread
        mainScope.launch {
            tonight.value = getTonightFromDatabase()
        }

    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        Timber.i("getTonightFromDatabase")
        return withContext(Dispatchers.IO) {
            Timber.i("getTonightFromDatabase with context")
            var night = database.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                night = null
            }
            night
        }
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