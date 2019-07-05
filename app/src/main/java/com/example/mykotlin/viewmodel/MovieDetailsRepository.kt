package com.example.mykotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.HttpException
import com.example.mykotlin.model.Movie
import com.example.mykotlin.network.RestApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsRepository(private val thisApiCorService: RestApiService) {

    private var mutableLiveData = MutableLiveData<Movie>()

    open fun getMovieDetail(): MutableLiveData<Movie> {
        CoroutineScope(Dispatchers.IO).launch {
            val request = thisApiCorService.getMovieAsync()
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    mutableLiveData.value = response.body()
                } catch (e: HttpException) {
                    Log.e("Tag", e.toString())
                } catch (e: Throwable) {
                    Log.e("Tag", e.toString())
                }
            }
        }
        return mutableLiveData
    }

}