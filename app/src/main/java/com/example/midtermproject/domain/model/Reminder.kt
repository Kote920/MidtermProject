package com.example.midtermproject.domain.model

data class Reminder (
    var reminderId: Int = 0,
    var medicineName: String,
    var time: String,
    var checked: Boolean
)