package com.example.midtermproject.domain.repository

import com.example.midtermproject.data.common.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun login(email: String, password: String): Flow<Resource<AuthResult>>
}