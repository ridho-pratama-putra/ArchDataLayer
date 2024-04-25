package com.example.archdatalayer.sleepTracker

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.archdatalayer.R
import com.example.archdatalayer.convertDurationToFormatted
import com.example.archdatalayer.convertNumericQualityToString
import com.example.archdatalayer.database.SleepNight

/**
 * adapt application data into something dataBinding can display on the screen
 */
@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: SleepNight) {
    item.let {
        text = convertDurationToFormatted(it.startTimeMilli, it.endTimeMilli, context.resources)
        if (it.sleepQuality <= 2) {
            setTextColor(Color.RED)
        } else { // must be reset set property
            setTextColor(Color.BLACK)
        }
    }
}

@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: SleepNight) {
    item.let {
        text = convertNumericQualityToString(it.sleepQuality, context.resources)
    }
}

@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: SleepNight) {
    item.let {
        setImageResource(when(item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }
}