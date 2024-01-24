package com.example.midtermproject.di

import com.example.midtermproject.data.remote.service.HealthService
import com.example.midtermproject.data.remote.service.TokenService
import com.google.firebase.auth.FirebaseAuth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val BASE_URL_AUTH = "https://authservice.priaid.ch/"
    const val BASE_URL_SYMPTOMS = "https://healthservice.priaid.ch/"

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().build()
//    }

    @Singleton
    @Provides
    @Named("authRetrofit")
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_AUTH)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()

    }

    @Provides
    @Singleton
    @Named("symptomRetrofit")
    fun provideSymptomRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_SYMPTOMS)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }



    @Provides
    @Singleton
    fun provideTokenService(@Named("authRetrofit")retrofit: Retrofit): TokenService {
        return retrofit.create(TokenService::class.java)
    }

    @Provides
    @Singleton
    fun provideHealthService(@Named("symptomRetrofit")retrofit: Retrofit): HealthService {
        return retrofit.create(HealthService::class.java)
    }











}
