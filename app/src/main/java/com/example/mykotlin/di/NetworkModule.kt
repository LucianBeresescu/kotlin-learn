package com.example.mykotlin.di

import android.util.Log
import com.example.mykotlin.BuildConfig
import com.example.mykotlin.network.RestApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { provideDefaultOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }

}


fun provideDefaultOkhttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY


    val headerAuthorizationInterceptor = Interceptor { chain ->
        val request = chain.request().url().newBuilder().addQueryParameter("api_key", BuildConfig.TMBD_API_KEY).build()
        val newRequest = chain.request().newBuilder().url(request).build()
        chain.proceed(newRequest)
    }
    return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .addInterceptor(headerAuthorizationInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
}

fun provideApiService(retrofit: Retrofit): RestApiService = retrofit.create(RestApiService::class.java)