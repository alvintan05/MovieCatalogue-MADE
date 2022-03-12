package com.aldev.moviecataloguemade.core.domain.repository

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(): Flow<Resource<List<Movie>>>
    fun getTvShowList(): Flow<Resource<List<Movie>>>
    fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>>
    fun getDetailTvShow(tvId: Int): Flow<Resource<DetailMovie>>
    suspend fun insertMovie(detailMovie: DetailMovie)
    suspend fun deleteMovie(id: Int, type: String)
    fun getFavoriteList(): Flow<List<Movie>>
    fun getDetailFavorite(id: Int, type: String): Flow<Resource<DetailMovie>>
    fun checkIsFavorite(id: Int, type: String): Flow<Boolean>
    fun searchMovie(searchQuery: String): Flow<Resource<List<Movie>>>
    fun searchTvShow(searchQuery: String): Flow<Resource<List<Movie>>>
}