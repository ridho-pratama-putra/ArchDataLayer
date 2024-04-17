package com.example.archdatalayer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface SleepDatabaseDao {

    /* returning live data, amazing feature of rrom. room make sure live data updated whenever database is updated
    * hanya perlu get all night once, attach observer, dan saat di db berubah, UI akan berubah tanpa perlu get lagi
    * save time, code complexity
    * */
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>
}