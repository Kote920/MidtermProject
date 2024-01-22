package com.example.midtermproject.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import javax.inject.Singleton
//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Provides
//    @Singleton
//    fun provideMoshi(): Moshi {
//        return Moshi.Builder()
//            .addLast(KotlinJsonAdapterFactory())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            // No interceptor added
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://medicine-search-and-autocomplete.p.rapidapi.com/api/")
//            .client(okHttpClient)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideMedicineApiService(retrofit: Retrofit): MedicineApiService {
//        return retrofit.create(MedicineApiService::class.java)
//    }
//}
