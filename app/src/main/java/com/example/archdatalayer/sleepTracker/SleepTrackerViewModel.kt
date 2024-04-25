package com.example.archdatalayer.sleepTracker

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.archdatalayer.database.SleepDatabaseDao
import com.example.archdatalayer.database.SleepNight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SleepTrackerViewModel(
    val database: SleepDatabaseDao,
    application: Application
): ViewModel() {

    private var tonight = MutableLiveData<SleepNight?>()
    /**
     * If tonight has not been set, then the START button should be visible.
     */
    val startButtonVisible = tonight.map { null == it }

    /**
     * If tonight has been set, then the STOP button should be visible.
     */
    val stopButtonVisible = tonight.map{ null != it }

    // encapsulate mutable livedata
    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality: LiveData<SleepNight> get() = _navigateToSleepQuality
    private val _navigateToSleepDetailQuality = MutableLiveData<Long?>()
    val navigateToSleepDetailQuality: LiveData<Long?> get() = _navigateToSleepDetailQuality

    // to manage coroutine (seperti cancel all coroutine saat viewmodel nolonger use dan destroyed)
    private var viewModelJob = Job()

    // scope determine what thread the coroutine will run on and its need to know about the job
    // this means coroutine launch in the ui scope will run on main thread
    // at first using different scope that is io for save to db and main to set value
    // but later found that it can create branch from ui (main) to thread by using withContext
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val mainScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val nights = database.getAllNights()

    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    fun onButtonStartPressed() {
        Timber.i("onButtonStartPressed")
        uiScope.launch {
            Timber.i("onstarttracking launch")
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(newNight: SleepNight) {
        withContext(Dispatchers.IO) {
            database.save(newNight)
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

        uiScope.launch {
            cleardb()
        }
    }

    private suspend fun cleardb() {
        withContext(Dispatchers.IO) {
            database.clearDb()
        }
    }
    override fun onCleared() {
        Timber.i("onCleared")
        super.onCleared()
    }

    fun onClickDetailSleepNight(nightId: Long) {
        _navigateToSleepDetailQuality.value = nightId
    }

    fun onSleepDetailNavigated() {
        _navigateToSleepDetailQuality.value = null
    }
}