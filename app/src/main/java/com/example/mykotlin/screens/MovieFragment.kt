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
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()

    companion object {
        fun newInstance() = MovieFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
