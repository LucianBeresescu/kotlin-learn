package com.example.mykotlin.di

import com.example.mykotlin.network.RestApiService
import com.example.mykotlin.screens.MovieDetailsViewModel
import com.example.mykotlin.viewmodel.MovieDetailsRepository
import com.example.mykotlin.viewmodel.MovieRepository
import com.example.mykotlin.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module{
    single { provideMovieRepository(get()) }
    single { provideMovieDetailsRepository(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}

fun provideMovieRepository(restApiService: RestApiService) : MovieRepository = MovieRepository(restApiService)

fun provideMovieDetailsRepository(restApiService: RestApiService) : MovieDetailsRepository = MovieDetailsRepository(restApiService)