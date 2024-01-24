package com.example.midtermproject.di

import com.example.midtermproject.domain.repository.AuthRepository
import com.example.midtermproject.domain.repository.HealthRepository
import com.example.midtermproject.domain.useCases.AuthUseCase
import com.example.midtermproject.domain.useCases.GetDiagnosisUseCase
import com.example.midtermproject.domain.useCases.GetSymptomsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetSymptomsUseCase(healthRepository: HealthRepository): GetSymptomsUseCase {
        return GetSymptomsUseCase(healthRepository)
    }

    @Provides
    @Singleton
    fun provideGetDiagnosisUseCase(healthRepository: HealthRepository): GetDiagnosisUseCase {
        return GetDiagnosisUseCase(healthRepository)
    }
}