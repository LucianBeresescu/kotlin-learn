package com.example.mykotlin.network

import com.example.mykotlin.BuildConfig
import com.example.mykotlin.model.Movie
import com.example.mykotlin.model.MovieWrapper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.http.GET

interface RestApiService {

    @GET("3/discover/movie")
    fun getPopularMovieAsync(): Deferred<MovieWrapper>

    companion object {

        fun createCorService(): RestApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY


            val headerAuthorizationInterceptor = Interceptor { chain ->
                val request = chain.request().url().newBuilder().addQueryParameter("api_key", BuildConfig.TMBD_API_KEY).build()
                val newRequest = chain.request().newBuilder().url(request).build()
                chain.proceed(newRequest)
            }

            val okHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptor)
                    .addInterceptor(headerAuthorizationInterceptor)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(okHttpClient)
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build().create(RestApiService::class.java)
        }
    }
}