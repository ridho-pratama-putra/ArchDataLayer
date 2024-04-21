package com.example.archdatalayer.sleepTracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.archdatalayer.R
import com.example.archdatalayer.database.SleepNight
import timber.log.Timber

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_view_item, parent, false) as TextView
        Timber.i("onCreateViewHolder $this")
        return TextItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /*
    * called everytime view recycled from previous screen
    * ensure doing reset to what have been set. ie. set text back to default black when other set to red
     */
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        if (item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED)
        } else {
            holder.textView.setTextColor(Color.BLACK)
        }
        holder.textView.text = item.sleepQuality.toString()
        // Timber.i("onBindViewHolder $this $holder")
    }

}