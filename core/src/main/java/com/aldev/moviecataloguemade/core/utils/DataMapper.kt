package com.aldev.moviecataloguemade.core.utils

import com.aldev.moviecataloguemade.core.data.source.remote.response.MovieResponse
import com.aldev.moviecataloguemade.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id ?: 0,
                title = it.title.orEmpty(),
                voteAverage = it.voteAverage ?: 0.0,
                overview = it.overview.orEmpty(),
                posterPath = it.posterPath.orEmpty()
            )
            movieList.add(movie)
        }
        return movieList
    }
}