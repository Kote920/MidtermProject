package com.example.midtermproject.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermproject.domain.mapper.toPresentation
import com.example.midtermproject.domain.useCases.InsertReminderUseCase
import com.example.midtermproject.domain.useCases.ReadRemindersUseCase
import com.example.midtermproject.presentation.mapper.toDomain
import com.example.midtermproject.presentation.model.ReminderUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel  @Inject constructor(
    private val readRemindersUseCase: ReadRemindersUseCase,
    private val insertRemindersUseCase: InsertReminderUseCase
): ViewModel() {

    val reminders: Flow<List<ReminderUI>> = readRemindersUseCase().map { reminders ->
        reminders.map {
            it.toPresentation()
        }

    }

     fun insertReminder(medicineName: String, time: String){
        viewModelScope.launch{
            insertRemindersUseCase(ReminderUI(medicineName = medicineName, time = time, checked = false, ).toDomain())
        }

    }
}