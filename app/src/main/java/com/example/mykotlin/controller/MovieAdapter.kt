package com.example.mykotlin.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlin.databinding.MovieListItemBinding
import com.example.mykotlin.model.Movie
import com.example.mykotlin.utils.DiffUtilCallBack

class MovieAdapter(
        val itemClickListener: (Movie) -> Unit = {}
) : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    class MovieViewHolder(val binding: MovieListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListItemBinding.inflate(inflater)
        binding.apply {
            root.setOnClickListener {
                item?.apply {
                    itemClickListener(this)
                }
            }
        }
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let{ holder.bind(it)}
    }
}