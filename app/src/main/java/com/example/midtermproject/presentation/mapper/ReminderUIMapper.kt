package com.example.midtermproject.presentation.mapper

import com.example.midtermproject.domain.model.Reminder
import com.example.midtermproject.presentation.model.ReminderUI

fun ReminderUI.toDomain() = Reminder(
    medicineName = medicineName,
    time = time,
    checked = checked)