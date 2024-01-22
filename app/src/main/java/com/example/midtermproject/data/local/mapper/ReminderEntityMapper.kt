package com.example.midtermproject.data.local.mapper

import com.example.midtermproject.data.local.entity.ReminderEntity
import com.example.midtermproject.domain.model.Reminder

fun ReminderEntity.toDomain() = Reminder(
    medicineName = medicineName,
    time = time,
    checked = checked,
    reminderId=  reminderId
)