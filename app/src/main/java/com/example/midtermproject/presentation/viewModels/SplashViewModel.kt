package com.example.midtermproject.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermproject.data.common.DataStoreUtil
import com.example.midtermproject.domain.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    private val _sessionFlow = MutableSharedFlow<Boolean>()
    val sessionFlow: SharedFlow<Boolean> get() = _sessionFlow

    private val _emailFlow = MutableSharedFlow<String>()
    val emailFlow: SharedFlow<String> get() = _emailFlow

    fun readSession() {
        viewModelScope.launch {
            dataStoreRepository.readBoolean(DataStoreUtil.REMEMBER).collect{
                _sessionFlow.emit(it)
            }
        }

    }

    fun readEmail() {
        viewModelScope.launch {
            dataStoreRepository.readString(DataStoreUtil.EMAIL).collect{
                _emailFlow.emit(it)
            }
        }

    }
}