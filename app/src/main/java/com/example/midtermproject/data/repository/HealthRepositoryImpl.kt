package com.example.midtermproject.data.repository

import com.example.midtermproject.data.common.Resource
import com.example.midtermproject.data.remote.service.TokenService
import com.example.midtermproject.domain.repository.HealthRepository
import kotlinx.coroutines.flow.Flow
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
import android.util.Log.d
import com.example.midtermproject.data.remote.mapper.toDomain
import com.example.midtermproject.data.remote.model.IssueDto
import com.example.midtermproject.data.remote.service.HealthService
import com.example.midtermproject.domain.model.Issue
import com.example.midtermproject.domain.model.Symptom
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HealthRepositoryImpl @Inject constructor(
    private val tokenService: TokenService,
    private val healthService: HealthService
) : HealthRepository {

    private suspend fun getToken(): String {


        try {
            val uri = "https://authservice.priaid.ch/login"
            val apiKey = "Qc56J_GMAIL_COM_AUT"
            val secretKey = "x5WCq2p6TYt48Ndb7"

            val computedHashString = calculateHmacMd5(uri, secretKey)
            val computedHashString2 = computedHashString.replace("\n", "")

            val authorizationHeader = "Bearer $apiKey:$computedHashString2".trim()

            val response = tokenService.getToken(uri, authorizationHeader)


            if (response.isSuccessful) {
                return response.body()!!.token

            } else {
                d("Request failed is not success in rep impl", "no succ")
                return "Failed"
            }
        } catch (e: Exception) {
            d("Request failed is not success in rep impl", e.toString())
            return " Failed "
        }

    }


    override suspend fun getDiagnosis(
        symptoms: List<Int>,
        gender: String,
        age: Int
    ): Flow<Resource<List<Issue>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = healthService.getDiagnosis(getToken(), getJsonArr(symptoms), gender, age)

                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!!.map {
                        it.toDomain()
                    }))
                } else {
                    emit(Resource.Failed("Request failed"))
                }
            } catch (e: Exception) {

                d("failed in rep", e.toString())
                emit(Resource.Failed("Request failed"))

            }
        }

    }

    override suspend fun getSymptoms(): Flow<Resource<List<Symptom>>> {

        return flow {
            try {
                emit(Resource.Loading())
                val response = healthService.getSymptoms(getToken(), "en-gb", null)

                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!!.map {
                        it.toDomain()
                    }))
                } else {
                    emit(Resource.Failed("Request failed"))
                }
            } catch (e: Exception) {

                emit(Resource.Failed("Request failed"))

            }
        }
    }


    fun calculateHmacMd5(data: String, key: String): String {
        val keyBytes = key.toByteArray(charset("UTF-8"))
        val dataBytes = data.toByteArray(charset("UTF-8"))

        val hmacMd5 = Mac.getInstance("HmacMD5")
        val secretKey = SecretKeySpec(keyBytes, "HmacMD5")
        hmacMd5.init(secretKey)

        val hashedBytes = hmacMd5.doFinal(dataBytes)

        return Base64.encodeToString(hashedBytes, Base64.DEFAULT)
    }

    private fun getJsonArr(symptomList : List<Int>): String{

        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<*>>? = moshi.adapter(List::class.java)
         return adapter!!.toJson(symptomList)

    }


}
