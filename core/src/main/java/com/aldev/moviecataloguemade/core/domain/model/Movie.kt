package com.aldev.moviecataloguemade.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val posterPath: String,
    val type: String
)
