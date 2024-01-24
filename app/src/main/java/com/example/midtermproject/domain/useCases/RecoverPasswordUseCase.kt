package com.example.midtermproject.domain.useCases

import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.domain.repository.PasswordManagementRepository
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(private val repository: PasswordManagementRepository) {

    suspend operator fun invoke(
        email: String,

    ): Flow<Resource<Task<Void>>> {

        return repository.recoverPassword(
            email = email,
        ).map { resource ->
            when (resource) {
                is Resource.Success -> Resource.Success(resource.responseData)
                is Resource.Failed -> Resource.Failed(resource.message)
                is Resource.Loading -> Resource.Loading()
            }

        }


    }
}