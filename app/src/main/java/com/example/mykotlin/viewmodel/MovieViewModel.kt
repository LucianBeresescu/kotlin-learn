package com.example.mykotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mykotlin.model.Movie

class MovieViewModel constructor(private val movieRepository: MovieRepository): ViewModel() {
    val movieList: LiveData<List<Movie>> get() = movieRepository.getMutableLiveData()
}
