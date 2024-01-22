package com.example.midtermproject.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {


    suspend fun saveString(key: Preferences.Key<String>, value: String)

    suspend fun saveBoolean(key: Preferences.Key<Boolean>, value: Boolean)

    suspend fun readString(key: Preferences.Key<String>): Flow<String>

    suspend fun readBoolean(key: Preferences.Key<Boolean>): Flow<Boolean>

    suspend fun clear()



}