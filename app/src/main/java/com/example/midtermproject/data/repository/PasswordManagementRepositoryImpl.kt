package com.example.midtermproject.data.repository

import com.example.midtermproject.data.common.HandleResponse
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.domain.repository.PasswordManagementRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PasswordManagementRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : PasswordManagementRepository {
    override suspend fun recoverPassword(email: String): Flow<Resource<Task<Void>>> {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword(
        email: String,
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<Task<Void>>> {
        return flow {
            try {


                emit(Resource.Loading())
                val result = firebaseAuth.signInWithEmailAndPassword(email, oldPassword)
                    .await().user!!.updatePassword(newPassword)

                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Failed("Could not change password"))
            }
        }
    }


}