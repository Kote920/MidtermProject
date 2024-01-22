package com.example.midtermproject.domain.repository

import com.example.midtermproject.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface LocalReminderRepository {

   fun getALl(): Flow<List<Reminder>>

    suspend fun insert(reminder: Reminder)

    suspend fun delete(reminder: Reminder)

}