package com.example.midtermproject.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.domain.useCases.ChangePasswordUseCase
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val changePasswordUseCase: ChangePasswordUseCase) :
    ViewModel() {


    private val _changePasswordFlow = MutableSharedFlow<Resource<Task<Void>>>()
    val changePasswordFlow: SharedFlow<Resource<Task<Void>>> = _changePasswordFlow.asSharedFlow()



    fun changePassword(email: String, oldPassword: String, newPassword: String){
        viewModelScope.launch {
            changePasswordUseCase.invoke(
                email = email,
                oldPassword = oldPassword,
                newPassword = newPassword
            ).collect{
                when (it) {
                    is Resource.Loading -> _changePasswordFlow.emit(Resource.Loading())
                    is Resource.Success -> _changePasswordFlow.emit(Resource.Success(it.responseData))
                    is Resource.Failed -> _changePasswordFlow.emit(Resource.Failed(it.message))

                }
            }
        }

    }
}