package com.example.mykotlin.screens

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.example.mykotlin.R
import com.example.mykotlin.controller.MovieAdapter
import com.example.mykotlin.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        Log.e("", viewModel.movieList.toString());

        viewModel.movieList.observe(this, Observer {
            movieList ->
            movies_list.apply {
                layoutManager = GridLayoutManager(activity,3)
                adapter = MovieAdapter(movieList)
            }
        }
        )
    }

}
