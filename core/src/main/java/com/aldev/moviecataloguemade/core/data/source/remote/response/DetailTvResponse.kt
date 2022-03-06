package com.aldev.moviecataloguemade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTvResponse(
    @field:SerializedName("id")
    var id: Int?,

    @field:SerializedName("name")
    var title: String?,

    @field:SerializedName("first_air_date")
    var releaseDate: String?,

    @field:SerializedName("episode_run_time")
    var episodeRunTime: List<Int>?,

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