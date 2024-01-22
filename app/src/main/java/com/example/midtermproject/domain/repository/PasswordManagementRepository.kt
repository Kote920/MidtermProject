package com.example.midtermproject.domain.repository

import com.example.midtermproject.data.common.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface PasswordManagementRepository {

    suspend fun recoverPassword(email: String): Flow<Resource<Task<Void>>>
    suspend fun changePassword(email: String, oldPassword: String, newPassword: String): Flow<Resource<Task<Void>>>
}