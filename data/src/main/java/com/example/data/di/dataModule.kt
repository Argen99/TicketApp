package com.example.data.di

import com.example.data.BuildConfig.BASE_URL
import com.example.data.remote.api_service.MainApiService
import com.example.data.remote.repository.MainRepositoryImpl
import com.example.data.local.PreferencesManager
import com.example.domain.repository.MainRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    singleOf(::provideRetrofit)
    singleOf(::provideOkHttpClient)
    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    singleOf(::provideMainApiService)
    singleOf(::MainRepositoryImpl) {
        bind<MainRepository>()
    }
    singleOf(::PreferencesManager)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideMainApiService(retrofit: Retrofit): MainApiService {
    return retrofit.create(MainApiService::class.java)
}