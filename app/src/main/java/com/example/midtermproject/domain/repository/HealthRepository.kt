package com.example.midtermproject.domain.repository

import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.data.remote.model.IssueDto
import com.example.midtermproject.domain.model.Issue
import com.example.midtermproject.domain.model.Symptom
import kotlinx.coroutines.flow.Flow

interface HealthRepository {




    suspend fun getSymptoms(): Flow<Resource<List<Symptom>>>

    suspend fun getDiagnosis(
        symptoms: List<Int>,
        gender: String,
        age: Int
    ): Flow<Resource<List<Issue>>>

}