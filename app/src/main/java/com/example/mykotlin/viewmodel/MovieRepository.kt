package com.example.mykotlin.viewmodel

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.mykotlin.model.Movie
import com.example.mykotlin.network.RestApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieRepository(private val thisApiCorService: RestApiService) : PageKeyedDataSource<Int, Movie>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        GlobalScope.launch {
            try {
                val response = thisApiCorService.getPopularMovieAsync(1).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val redditPosts = listing?.movies?.map { it }
                        callback.onResult(redditPosts ?: listOf(), null, 2)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        GlobalScope.launch {
            try {
                val response = thisApiCorService.getPopularMovieAsync(params.key).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val items = listing?.movies?.map { it }
                        callback.onResult(items ?: listOf(), params.key + 1)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        GlobalScope.launch {
            try {
                val response = thisApiCorService.getPopularMovieAsync(params.key).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val items = listing?.movies?.map { it }
                        callback.onResult(items ?: listOf(), params.key - 1)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }
        }

    }
}
