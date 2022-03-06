package com.aldev.moviecataloguemade.core.domain.model

data class DetailMovie(
    var movieId: Int,
    var title: String,
    var releaseDate: String,
    var runtime: Int,
    var rating: Double,
    var genres: String,
    var overview: String,
    var posterPath: String,
    var backdropPath: String,
    var type: String
)
