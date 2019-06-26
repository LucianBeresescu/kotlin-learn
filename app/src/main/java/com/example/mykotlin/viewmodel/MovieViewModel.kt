package com.example.mykotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.example.mykotlin.model.Movie

class MovieViewModel : ViewModel() {

    private val movieRepository= MovieRepository()
    val movieList: LiveData<List<Movie>> get() = movieRepository.getMutableLiveData()
}
