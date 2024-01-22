package com.example.midtermproject.domain.useCases

import com.example.midtermproject.domain.model.Reminder
import com.example.midtermproject.domain.repository.LocalReminderRepository
import javax.inject.Inject

class InsertReminderUseCase @Inject constructor(private val repository: LocalReminderRepository) {

    suspend operator fun  invoke(reminder: Reminder) {

        repository.insert(reminder = reminder)

    }
}