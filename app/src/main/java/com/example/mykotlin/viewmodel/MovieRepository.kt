package com.example.mykotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mykotlin.model.Movie
import com.example.mykotlin.network.RestApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MovieRepository() {
    private var movies = mutableListOf<Movie>()
    private var mutableLiveData = MutableLiveData<List<Movie>>()

    private val thisApiCorService by lazy {
        RestApiService.createCorService()
    }

    fun getMutableLiveData(): MutableLiveData<List<Movie>> {
        CoroutineScope(Dispatchers.IO).launch {
            val request = thisApiCorService.getPopularMovieAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.blog == null) {
                        Log.e("Tag", "Response is null")
                    } else {
                        movies = response.blog as MutableList<Movie>
                        mutableLiveData.value = movies;
                    }
                } catch (e: HttpException) {
                    Log.e("Tag", e.toString())
                } catch (e: Throwable) {
                    Log.e("Tag", e.toString())
                }
            }
        }
        return mutableLiveData;
    }

}