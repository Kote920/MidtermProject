package com.example.midtermproject.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.midtermproject.data.common.HandleResponse
import com.example.midtermproject.data.local.dao.ReminderDao

import com.example.midtermproject.data.repository.AuthRepositoryImpl
import com.example.midtermproject.data.repository.DataStoreRepositoryImpl
import com.example.midtermproject.data.repository.LocalReminderRepositoryImpl
import com.example.midtermproject.data.repository.PasswordManagementRepositoryImpl
import com.example.midtermproject.domain.repository.AuthRepository
import com.example.midtermproject.domain.repository.DataStoreRepository
import com.example.midtermproject.domain.repository.LocalReminderRepository
import com.example.midtermproject.domain.repository.PasswordManagementRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, handleResponse: HandleResponse): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth,handleResponse)
    }

    @Provides
    @Singleton
    fun providePasswordManagementRepository(firebaseAuth: FirebaseAuth):PasswordManagementRepository {
        return PasswordManagementRepositoryImpl(firebaseAuth)
    }


    @Singleton
    @Provides
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>) : DataStoreRepository {

        return  DataStoreRepositoryImpl(dataStore)

    }

    @Singleton
    @Provides
    fun provideLocalReminderRepository(reminderDao: ReminderDao): LocalReminderRepository{

        return LocalReminderRepositoryImpl(reminderDao = reminderDao)

    }
//
//    @Provides
//    @Singleton
//    fun provideMedicineRepository(apiService: MedicineApiService): MedicineRepository {
//        return MedicineRepositoryImpl(apiService)
//    }




}