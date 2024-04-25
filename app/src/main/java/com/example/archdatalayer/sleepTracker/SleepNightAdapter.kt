package com.example.archdatalayer.sleepTracker

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.archdatalayer.database.SleepNight
import timber.log.Timber

/*
* this class responsible to adapt data tobe used by recycle view
 */
//class SleepNightAdapter: RecyclerView.Adapter<ViewHolder>() {
// listAdapter : chatGPT -> mengoptimalkan kinerja aplikasi Anda dengan menghindari pembaruan yang tidak perlu pada item yang tidak berubah.
class SleepNightAdapter(val sleepNightClickListener: SleepNightClickListener): ListAdapter<SleepNight, ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    /*
    * called everytime view recycled from previous screen
    * ensure doing reset to what have been set. ie. set text back to default black when other set to red
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, sleepNightClickListener)
        Timber.i("onBindViewHolder $this $holder")
    }
}