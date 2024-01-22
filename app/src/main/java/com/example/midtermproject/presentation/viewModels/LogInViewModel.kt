package com.example.midtermproject.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermproject.data.common.DataStoreUtil
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.domain.repository.DataStoreRepository
import com.example.midtermproject.domain.useCases.AuthUseCase
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val useCase: AuthUseCase,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _logInFlow = MutableSharedFlow<Resource<AuthResult>>()
    val logInFlow: SharedFlow<Resource<AuthResult>> = _logInFlow.asSharedFlow()

    private val _successFlow = MutableSharedFlow<LogInFragmentNavigationEvent>()
    val successFlow: SharedFlow<LogInFragmentNavigationEvent> get() = _successFlow



    fun logIn(email: String, password: String) {

        viewModelScope.launch {
            useCase.login(email, password).collect {
                when (it) {
                    is Resource.Loading -> _logInFlow.emit(Resource.Loading())
                    is Resource.Success -> _logInFlow.emit(Resource.Success(it.responseData))
                    is Resource.Failed -> _logInFlow.emit(Resource.Failed(it.message))

                }
            }
        }
    }

    fun saveToken(email: String, remember: Boolean) {
        viewModelScope.launch {

            dataStoreRepository.saveString(DataStoreUtil.EMAIL, email)
            dataStoreRepository.saveBoolean(DataStoreUtil.REMEMBER, remember)

            _successFlow.emit(LogInFragmentNavigationEvent.NavigationToHome)
        }

    }


}



sealed class LogInFragmentNavigationEvent {
    object NavigationToHome : LogInFragmentNavigationEvent()
}