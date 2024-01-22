package com.example.midtermproject.presentation.viewModels

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.midtermproject.data.common.Resource
//import com.example.midtermproject.data.remote.model.MedicineResponse
//import com.example.midtermproject.domain.repository.MedicineRepository
//import com.google.firebase.auth.AuthResult
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.SharedFlow
//import kotlinx.coroutines.flow.asSharedFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class DetectorViewModel @Inject constructor(private val repository: MedicineRepository):ViewModel() {
//
//    private val _logInFlow = MutableSharedFlow<Resource<List<MedicineResponse>>>()
//    val logInFlow: SharedFlow<Resource<List<MedicineResponse>>> = _logInFlow.asSharedFlow()
//
//
//    fun get() {
//
//        viewModelScope.launch {
//           val res =  repository.searchMedicine("para")
//            _logInFlow.emit(res)
//        }
//    }
//}