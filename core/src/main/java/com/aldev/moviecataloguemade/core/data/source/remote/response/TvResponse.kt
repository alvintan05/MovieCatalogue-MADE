package com.aldev.moviecataloguemade.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("name")
    val title: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?
)