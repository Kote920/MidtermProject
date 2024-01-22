package com.example.midtermproject.data.repository

import android.util.Log.d
import com.example.midtermproject.data.common.HandleResponse
import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import io.grpc.android.BuildConfig
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val handleResponse: HandleResponse
) : AuthRepository {
    override suspend fun register(email: String, password: String): Flow<Resource<AuthResult>> {


        return handleResponse.safeApiCall {
           firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        }.map {
            when(it){
                is Resource.Success -> Resource.Success(it.responseData)
                is Resource.Failed -> Resource.Failed(it.message)
                is Resource.Loading -> Resource.Loading()

            }
        }



    }

    override suspend fun login(email: String, password: String): Flow<Resource<AuthResult>> {

        return handleResponse.safeApiCall {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }.map {
            when(it){
                is Resource.Success -> Resource.Success(it.responseData)
                is Resource.Failed -> Resource.Failed(it.message)
                is Resource.Loading -> Resource.Loading()

            }
        }


    }

}