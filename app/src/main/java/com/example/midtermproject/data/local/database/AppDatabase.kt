package com.example.midtermproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.midtermproject.data.local.dao.ReminderDao
import com.example.midtermproject.data.local.entity.ReminderEntity

@Database(entities = [ReminderEntity::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ReminderDao(): ReminderDao
}