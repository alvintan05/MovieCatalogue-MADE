package com.aldev.moviecataloguemade.core.utils

import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailMovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailTvResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.MovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.TvResponse
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.model.Movie

object DataMapper {
    fun mapMovieResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id ?: 0,
                title = it.title.orEmpty(),
                voteAverage = it.voteAverage ?: 0.0,
                overview = it.overview.orEmpty(),
                posterPath = it.posterPath.orEmpty(),
                type = CommonConstant.MovieType.MOVIE
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvResponsesToDomain(input: List<TvResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id ?: 0,
                title = it.title.orEmpty(),
                voteAverage = it.voteAverage ?: 0.0,
                overview = it.overview.orEmpty(),
                posterPath = it.posterPath.orEmpty(),
                type = CommonConstant.MovieType.TV_SHOW
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapDetailMovieResponsesToDomain(input: DetailMovieResponse) = DetailMovie(
        movieId = input.id ?: 0,
        title = input.title.orEmpty(),
        releaseDate = input.releaseDate.orEmpty(),
        runtime = input.runtime ?: 0,
        rating = input.rating ?: 0.0,
        genres = input.genres?.joinToString(", ", "", ".", 3, "etc") {
            it.name.toString()
        }.orEmpty(),
        overview = input.overview.orEmpty(),
        posterPath = input.posterPath.orEmpty(),
        backdropPath = input.backdropPath.orEmpty(),
        type = CommonConstant.MovieType.MOVIE
    )

    fun mapDetailTvResponsesToDomain(input: DetailTvResponse) = DetailMovie(
        movieId = input.id ?: 0,
        title = input.title.orEmpty(),
        releaseDate = input.releaseDate.orEmpty(),
        runtime = input.episodeRunTime?.average()?.toInt() ?: 0,
        rating = input.rating ?: 0.0,
        genres = input.genres?.joinToString(", ", "", ".", 3, "etc") {
            it.name.toString()
        }.orEmpty(),
        overview = input.overview.orEmpty(),
        posterPath = input.posterPath.orEmpty(),
        backdropPath = input.backdropPath.orEmpty(),
        type = CommonConstant.MovieType.TV_SHOW
    )

    fun mapListEntityToDomain(input: List<FavoriteEntity>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.movieId,
                title = it.title,
                voteAverage = it.rating,
                overview = it.overview,
                posterPath = it.posterPath,
                type = it.type
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapDetailEntityToDomain(input: FavoriteEntity) = DetailMovie(
        movieId = input.movieId,
        title = input.title,
        releaseDate = input.releaseDate,
        runtime = input.runtime,
        rating = input.rating,
        genres = input.genres,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        type = input.type
    )

    fun mapDetailDomainToEntity(input: DetailMovie) = FavoriteEntity(
        movieId = input.movieId,
        title = input.title,
        releaseDate = input.releaseDate,
        runtime = input.runtime,
        rating = input.rating,
        genres = input.genres,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        type = input.type,
        addedAt = System.currentTimeMillis()
    )
}