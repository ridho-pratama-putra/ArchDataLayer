package com.example.archdatalayer.sleepTracker

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.archdatalayer.database.SleepNight
import timber.log.Timber

/*
* this class responsible to adapt data tobe used by recycle view
 */
//class SleepNightAdapter: RecyclerView.Adapter<ViewHolder>() {
class SleepNightAdapter: ListAdapter<SleepNight, ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    /*
    * called everytime view recycled from previous screen
    * ensure doing reset to what have been set. ie. set text back to default black when other set to red
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val resources = holder.itemView.context.resources
        holder.bind(item, resources)
        Timber.i("onBindViewHolder $this $holder")
    }
}

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