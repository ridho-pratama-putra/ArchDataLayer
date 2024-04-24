package com.example.archdatalayer.sleepTracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.archdatalayer.R
import com.example.archdatalayer.convertDurationToFormatted
import com.example.archdatalayer.convertNumericQualityToString
import com.example.archdatalayer.database.SleepNight
import timber.log.Timber

class SleepNightAdapter: RecyclerView.Adapter<ViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false) as View
        Timber.i("onCreateViewHolder $this")
        return ViewHolder(view)
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
        if (item.sleepQuality <= 1) {
            holder.sleepDuration.setTextColor(Color.RED)
        } else {
            holder.sleepDuration.setTextColor(Color.BLACK)
        }
        val resources = holder.itemView.context.resources
        holder.sleepQuality.text = convertNumericQualityToString(item.sleepQuality, resources)
        holder.sleepDuration.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, resources)
        holder.sleepIcon.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
        // Timber.i("onBindViewHolder $this $holder")
    }

}