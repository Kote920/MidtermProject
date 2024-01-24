package com.example.midtermproject.data.remote.service

import com.example.midtermproject.data.remote.model.IssueDto
import com.example.midtermproject.data.remote.model.SymptomDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface HealthService {

    @GET("/symptoms")
    suspend fun getSymptoms(
        @Query("token") token: String,
        @Query("language") language: String,
        @Query("symptoms") symptoms: String? = null
    ): Response<List<SymptomDto>>


    @GET("/diagnosis")
    suspend fun getDiagnosis(
        @Query("token") token: String,
        @Query("symptoms") symptoms: String,
        @Query("gender") gender: String,
        @Query("year_of_birth") yearOfBirth: Int,
        @Query("language") language: String = "en-gb",
        @Query("format") format: String = "json"
    ): Response<List<IssueDto>>


}