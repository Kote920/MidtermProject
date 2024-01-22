package com.example.midtermproject.data.repository

import com.example.midtermproject.data.local.dao.ReminderDao
import com.example.midtermproject.data.local.mapper.toDomain
import com.example.midtermproject.domain.mapper.toData
import com.example.midtermproject.domain.model.Reminder
import com.example.midtermproject.domain.repository.LocalReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalReminderRepositoryImpl @Inject constructor(private val  reminderDao: ReminderDao): LocalReminderRepository
{
    override fun getALl(): Flow<List<Reminder>> {
        return reminderDao.getAll().map { reminders ->
            reminders.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insert(reminder: Reminder) {
        reminderDao.insert(reminder = reminder.toData())
    }

    override suspend fun delete(reminder: Reminder) {
        reminderDao.delete(reminder = reminder.toData())
    }
}