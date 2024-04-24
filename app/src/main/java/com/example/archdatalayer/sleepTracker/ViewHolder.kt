package com.example.archdatalayer.sleepTracker

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.archdatalayer.R

/**
 * ViewHolder that holds a single [TextView].
 *
 * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
 * to the RecyclerView such as where on the screen it was last drawn during scrolling.
 */
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val sleepDuration: TextView = itemView.findViewById(R.id.sleep_duration_string)
    val sleepQuality: TextView = itemView.findViewById(R.id.sleep_quality_string)
    val sleepIcon: ImageView = itemView.findViewById(R.id.sleep_quality_icon)
}