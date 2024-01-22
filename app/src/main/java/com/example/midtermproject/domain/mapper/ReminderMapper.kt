package com.example.midtermproject.domain.mapper

import com.example.midtermproject.data.local.entity.ReminderEntity
import com.example.midtermproject.domain.model.Reminder
import com.example.midtermproject.presentation.model.ReminderUI

fun Reminder.toData() = ReminderEntity(
    medicineName = medicineName,
    time = time,
    checked = checked
)

fun Reminder.toPresentation() = ReminderUI(
    medicineName = medicineName,
    time = time,
    checked = checked,
    reminderId = reminderId
)