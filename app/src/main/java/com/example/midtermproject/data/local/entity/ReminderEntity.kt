package com.example.midtermproject.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val reminderId: Int = 0,
    @ColumnInfo(name = "medicine_name") val medicineName: String,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "checked") val checked: Boolean,

)
