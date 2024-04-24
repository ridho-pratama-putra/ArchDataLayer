package com.example.archdatalayer.sleepTracker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.archdatalayer.database.SleepNight

/*
* this class responsible to adapt data tobe used by recycle view
 */
class SleepNightAdapter: RecyclerView.Adapter<ViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /*
    * called everytime view recycled from previous screen
    * ensure doing reset to what have been set. ie. set text back to default black when other set to red
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val resources = holder.itemView.context.resources
        holder.bind(item, holder, resources)
        // Timber.i("onBindViewHolder $this $holder")
    }

}