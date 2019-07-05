package com.example.mykotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mykotlin.model.Movie

class MovieViewModel constructor(private val movieRepository: MovieRepository): ViewModel() {

    var movieList: LiveData<PagedList<Movie>>

    init {
        val config = PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .build()
        movieList  = initializedPagedListBuilder(config).build()
    }

    fun getPosts():LiveData<PagedList<Movie>> = movieList

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Movie> {

        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return movieRepository
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}
