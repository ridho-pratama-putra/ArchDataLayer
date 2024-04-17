package com.example.archdatalayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class], version = 1, exportSchema = true)
abstract class SleepDatabase: RoomDatabase() {
    abstract val sleepDatabaseDao: SleepDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: SleepDatabase? = null // keep reference once we have one. prevent us repeatedly opening database yang expensive

        fun getInstance(context: Context) : SleepDatabase {

            // ensuring database di instansiasi sekali saat ada thread yang beraksi
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, SleepDatabase::class.java, "sleep").fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}