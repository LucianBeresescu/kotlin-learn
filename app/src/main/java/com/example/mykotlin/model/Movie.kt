package com.example.mykotlin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie (
        @Json(name = "vote_count") val voteCount: Int = -1,
        val id: Int,
        val title: String,
        @Json(name = "poster_path") val imagePath: String,
        @Json(name = "genre_ids") val genres: List<Int>,
        val overview: String
)
