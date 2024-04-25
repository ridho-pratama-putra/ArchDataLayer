package com.example.archdatalayer.sleepTracker

import androidx.recyclerview.widget.DiffUtil
import com.example.archdatalayer.database.SleepNight

class SleepNightDiffCallback: DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
//        Timber.i("areItemsTheSame ${oldItem.nightId} && ${newItem.nightId}")
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
//        Timber.i("areContentsTheSame ${oldItem.endTimeMilli} && ${newItem.endTimeMilli}")
        return oldItem == newItem
    }

}