package com.example.midtermproject.data.remote.service

import com.example.midtermproject.data.remote.model.TokenResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface TokenService {

    @POST
    suspend fun getToken(@Url uri: String, @Header("Authorization") authorizationHeader: String): Response<TokenResponseDto>

}