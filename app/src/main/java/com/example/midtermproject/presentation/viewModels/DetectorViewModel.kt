package com.example.midtermproject.presentation.viewModels

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.data.remote.model.IssueDto
import com.example.midtermproject.domain.mapper.toPresentation
import com.example.midtermproject.domain.repository.HealthRepository
import com.example.midtermproject.domain.useCases.GetDiagnosisUseCase
import com.example.midtermproject.domain.useCases.GetSymptomsUseCase
import com.example.midtermproject.presentation.mapper.SymptomUI
import com.example.midtermproject.presentation.model.IssueUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetectorViewModel @Inject constructor(
    private val getSymptomsUseCase: GetSymptomsUseCase,
    private val getDiagnosisUseCase: GetDiagnosisUseCase
) :
    ViewModel() {


    private val _symptomListFlow = MutableSharedFlow<Resource<List<SymptomUI>>>()

    val symptomListFlow: SharedFlow<Resource<List<SymptomUI>>> = _symptomListFlow.asSharedFlow()

    private val _issueListFlow = MutableSharedFlow<Resource<List<IssueUI>>>()

    val issueListFlow: SharedFlow<Resource<List<IssueUI>>> = _issueListFlow.asSharedFlow()


    fun getDiagnosis(symptomList: List<Int>, gender: String, age: String) {

        viewModelScope.launch {
            getDiagnosisUseCase.getDiagnosis(symptomList, gender, age).collect {
            when (it) {
                is Resource.Loading -> _issueListFlow.emit(Resource.Loading())
                is Resource.Success -> {
                    _issueListFlow.emit(Resource.Success(it.responseData.map { issue ->
                        issue.toPresentation()
                    }))
                    d("diagnosis success", it.responseData.toString())

                }

                is Resource.Failed -> {
                    _issueListFlow.emit(Resource.Failed(it.message))
                    d("diagnosis failed", it.message)
                }

            }
        }
        }
    }

    fun getSymptoms() {

        viewModelScope.launch {
            getSymptomsUseCase.getSymptoms().collect {
                when (it) {
                    is Resource.Loading -> _symptomListFlow.emit(Resource.Loading())
                    is Resource.Success -> {
                        _symptomListFlow.emit(Resource.Success(it.responseData.map { symptom ->
                            symptom.toPresentation()
                        }))

                    }

                    is Resource.Failed -> {
                        _symptomListFlow.emit(Resource.Failed(it.message))
                    }

                }
            }
        }
    }
}