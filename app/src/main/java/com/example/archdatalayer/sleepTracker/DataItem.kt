package com.example.archdatalayer.sleepTracker

import com.example.archdatalayer.database.SleepNight

sealed class DataItem {
    abstract val id: Long
    data class SleepNightItem(val sleepNightInstance: SleepNight): DataItem() {
        override val id: Long
            get() = sleepNightInstance.nightId
    }

    object Header: DataItem() {
        override val id: Long = Long.MIN_VALUE
    }

}