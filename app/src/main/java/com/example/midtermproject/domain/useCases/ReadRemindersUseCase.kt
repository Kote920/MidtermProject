package com.example.midtermproject.domain.useCases

import com.example.midtermproject.domain.repository.LocalReminderRepository
import javax.inject.Inject

class ReadRemindersUseCase @Inject constructor(private val repository: LocalReminderRepository)  {

    operator fun invoke() = repository.getALl()
}