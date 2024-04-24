package com.example.archdatalayer.sleepTracker

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.archdatalayer.R
import com.example.archdatalayer.convertDurationToFormatted
import com.example.archdatalayer.convertNumericQualityToString
import com.example.archdatalayer.database.SleepNight
import timber.log.Timber

/**
 * ViewHolder that holds a single [TextView].
 *
 * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
 * to the RecyclerView such as where on the screen it was last drawn during scrolling.
 * manage the view like which xml layout, match sleep icon, do colouring
 */
class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val sleepDuration: TextView = itemView.findViewById(R.id.sleep_duration_string)
    val sleepQuality: TextView = itemView.findViewById(R.id.sleep_quality_string)
    val sleepIcon: ImageView = itemView.findViewById(R.id.sleep_quality_icon)

     fun bind(
        item: SleepNight,
        holder: ViewHolder,
        resources: Resources
    ) {
        if (item.sleepQuality <= 1) {
            holder.sleepDuration.setTextColor(Color.RED)
        } else {
            holder.sleepDuration.setTextColor(Color.BLACK)
        }
        holder.sleepQuality.text = convertNumericQualityToString(item.sleepQuality, resources)
        holder.sleepDuration.text =
            convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, resources)
        holder.sleepIcon.setImageResource(
            when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            }
        )
    }

    companion object {
        fun from (parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false) as View
            Timber.i("onCreateViewHolder $this")
            return ViewHolder(view)
        }
    }
}