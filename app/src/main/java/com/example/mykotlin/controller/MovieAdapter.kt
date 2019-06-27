package com.example.mykotlin.controller

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mykotlin.BuildConfig
import com.example.mykotlin.R
import com.example.mykotlin.model.Movie

class MovieAdapter(private val movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
        private var mTitleView: TextView? = null
        private var mPosterView: ImageView? = null

        init {
            mTitleView = itemView.findViewById(R.id.list_item_movie_title)
            mPosterView = itemView.findViewById(R.id.list_item_movie_poster)
        }

        fun bind(movie: Movie) {
            Log.e("BIND", BuildConfig.ORIGINAL_IMAGE_URL + movie.imagePath)
            mTitleView?.text = movie.title
            mPosterView?.context?.let {
                Glide.with(it)
                        .load(BuildConfig.ORIGINAL_IMAGE_URL + movie.imagePath)
                        .into(mPosterView!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = movieList[position]
        holder.bind(movie)
    }
}