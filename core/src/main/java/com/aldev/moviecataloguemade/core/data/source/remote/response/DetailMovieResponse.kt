package com.aldev.moviecataloguemade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @field:SerializedName("id")
    var id: Int?,

    @field:SerializedName("title")
    var title: String?,

    @field:SerializedName("release_date")
    var releaseDate: String?,

    @field:SerializedName("runtime")
    var runtime: Int?,

    @field:SerializedName("vote_average")
    var rating: Double?,

    @field:SerializedName("genres")
    var genres: List<GenreResponse>?,

    @field:SerializedName("overview")
    var overview: String?,

    @field:SerializedName("poster_path")
    var posterPath: String?,

    @field:SerializedName("backdrop_path")
    var backdropPath: String?
)