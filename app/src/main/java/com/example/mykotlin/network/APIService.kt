package com.example.mykotlin.network

import com.example.mykotlin.model.MovieResponse
import com.example.mykotlin.model.MovieWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {

    @GET("3/discover/movie")
    fun getPopularMovieAsync(
            @Query("page") page: Int): Deferred<Response<MovieWrapper>>
}