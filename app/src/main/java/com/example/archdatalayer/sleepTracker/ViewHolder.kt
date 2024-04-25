package com.example.archdatalayer.sleepTracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.archdatalayer.database.SleepNight
import com.example.archdatalayer.databinding.ListItemSleepNightBinding
import timber.log.Timber

/**
 * ViewHolder that holds a single [TextView].
 *
 * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
 * to the RecyclerView such as where on the screen it was last drawn during scrolling.
 * manage the view like which xml layout, match sleep icon, do colouring
 */
class ViewHolder private constructor(private val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {
     fun bind(
        item: SleepNight,
        resources: Resources
    ) {
         binding.sleepNight = item

         /**
          * memastikan bahwa semua perubahan data pada setiap item di RecyclerView telah diterapkan ke tampilan
          * sebelum item tersebut ditampilkan kepada pengguna.
          */
         binding.executePendingBindings()
    }

    companion object {
        fun from (parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
            Timber.i("onCreateViewHolder $this")
            return ViewHolder(view)
        }
    }
}