package com.example.mykotlin.network

import com.example.mykotlin.model.Movie
import com.example.mykotlin.model.MovieWrapper
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApiService {

    @GET("3/discover/movie")
    fun getPopularMovieAsync(@Query("page") page: Int): Deferred<Response<MovieWrapper>>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieAsync(@Path("movie_id") movieId: String): Deferred<Response<Movie>>
}