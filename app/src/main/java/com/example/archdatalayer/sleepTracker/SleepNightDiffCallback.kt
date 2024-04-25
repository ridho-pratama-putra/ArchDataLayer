package com.example.archdatalayer.sleepTracker

import androidx.recyclerview.widget.DiffUtil

class SleepNightDiffCallback: DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        Timber.i("areItemsTheSame ${oldItem.nightId} && ${newItem.nightId}")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
//        Timber.i("areContentsTheSame ${oldItem.endTimeMilli} && ${newItem.endTimeMilli}")
        return oldItem == newItem
    }

}