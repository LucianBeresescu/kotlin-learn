package com.example.mykotlin.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieWrapper (
        @Json(name="results")
        var blog: MutableList<Movie>? = null,
        @Json(name="page")
        var page: Int? = null,
        @Json(name="total_pages")
        var totalPages: String? = null,
        @Json(name="total_results")
        var totalResults: String? = null
)