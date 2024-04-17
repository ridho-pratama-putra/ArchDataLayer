package com.example.archdatalayer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight(

    @PrimaryKey(autoGenerate = true)
    var nightId: Long = 0L,

    @ColumnInfo(name = "start_time_mili")
    var startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_mili")
    var endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "sleep_quality")
    var sleepQuality: Int = -1
)
