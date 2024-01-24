package com.example.midtermproject.domain.useCases

import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.domain.model.Issue
import com.example.midtermproject.domain.model.Symptom
import com.example.midtermproject.domain.repository.HealthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSymptomsUseCase(private val healthRepository: HealthRepository) {

    suspend fun getSymptoms(): Flow<Resource<List<Symptom>>> {
        return healthRepository.getSymptoms().map { resource ->
            when (resource) {
                is Resource.Success -> Resource.Success(resource.responseData)
                is Resource.Failed -> Resource.Failed(resource.message)
                is Resource.Loading -> Resource.Loading()
            }
        }
    }




}