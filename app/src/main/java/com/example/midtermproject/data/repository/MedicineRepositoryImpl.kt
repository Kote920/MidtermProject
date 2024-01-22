package com.example.midtermproject.data.repository

//import android.util.Log
//import com.example.midtermproject.data.common.Resource
//import com.example.midtermproject.data.remote.model.MedicineResponse
//import kotlinx.coroutines.flow.flow
//import java.net.ResponseCache

//class MedicineRepositoryImpl(private val apiService: MedicineApiService) : MedicineRepository {
//
////    override suspend fun searchMedicine(searchTerm: String): Resource<List<MedicineResponse>> {
////        return try {
////            val response = apiService.searchMedicine(searchTerm)
////            if (response.isSuccessful) {
////                Log.d("responseMedicine", response.isSuccessful)
////                Resource.Success(response.body() ?: emptyList())
////            } else {
////               Resource.Failed("Error fetching data")
////            }
////        } catch (e: Exception) {
////            Resource.Failed("Error fetching data")
////        }
////    }
//
//
//}
