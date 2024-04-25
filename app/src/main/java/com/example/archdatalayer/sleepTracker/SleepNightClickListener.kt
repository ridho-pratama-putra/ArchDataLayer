package com.example.archdatalayer.sleepTracker

import com.example.archdatalayer.database.SleepNight

/**
 * callback as viewHolder needs to inform fragment which item clicked
 */
class SleepNightClickListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClickSleepNight(night: SleepNight) = clickListener(night.nightId)
}