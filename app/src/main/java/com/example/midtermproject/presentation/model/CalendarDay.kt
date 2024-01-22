package com.example.midtermproject.presentation.model

data class CalendarDay(
    val dayOfMonth: Int,
    val dayOfWeek: String,
    val isCurrentMonth: Boolean,
    val isCurrentDay: Boolean = false,
    val currentMonth: String
)