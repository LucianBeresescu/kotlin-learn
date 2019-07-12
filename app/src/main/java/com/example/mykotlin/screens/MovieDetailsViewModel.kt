package com.example.mykotlin.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mykotlin.model.Movie
import com.example.mykotlin.viewmodel.MovieDetailsRepository

class MovieDetailsViewModel(movieRepository: MovieDetailsRepository) : ViewModel() {
    var movie: LiveData<Movie> = movieRepository.getMovieDetail("5")
}
