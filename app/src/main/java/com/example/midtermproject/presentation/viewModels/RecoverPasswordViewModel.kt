package com.example.midtermproject.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.domain.useCases.ChangePasswordUseCase
import com.example.midtermproject.domain.useCases.RecoverPasswordUseCase
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoverPasswordViewModel@Inject constructor(private val recoverPasswordUseCase: RecoverPasswordUseCase) : ViewModel() {

    private val _recoverPasswordFlow = MutableSharedFlow<Resource<Task<Void>>>()
    val recoverPasswordFlow: SharedFlow<Resource<Task<Void>>> = _recoverPasswordFlow.asSharedFlow()


    fun recoverPassword(email: String){
        viewModelScope.launch {
            recoverPasswordUseCase.invoke(
                email = email
            ).collect{
                when (it) {
                    is Resource.Loading -> _recoverPasswordFlow.emit(Resource.Loading())
                    is Resource.Success -> _recoverPasswordFlow.emit(Resource.Success(it.responseData))
                    is Resource.Failed -> _recoverPasswordFlow.emit(Resource.Failed(it.message))

                }
            }
        }

    }


}