package com.example.mykotlin.network

import com.example.mykotlin.model.MovieWrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {

    @GET("3/discover/movie")
    fun getPopularMovieAsync(@Query("page") page: Int = 1): Deferred<MovieWrapper>
}