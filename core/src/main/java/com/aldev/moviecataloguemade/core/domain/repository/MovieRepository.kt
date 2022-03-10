package com.aldev.moviecataloguemade.core.domain.repository

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.data.source.remote.response.MovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.TvResponse
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(): Flow<Resource<List<Movie>>>
    suspend fun getTvShowList(): Flow<Resource<List<Movie>>>
    suspend fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>>
    suspend fun getDetailTvShow(tvId: Int): Flow<Resource<DetailMovie>>
    suspend fun insertMovie(detailMovie: DetailMovie)
    suspend fun deleteMovie(id: Int, type: String)
    fun getFavoriteList(): Flow<Resource<List<Movie>>>
    fun getDetailFavorite(id: Int, type: String): Flow<Resource<DetailMovie>>
    fun checkIsFavorite(id: Int, type: String): Flow<Boolean>
    suspend fun searchMovie(searchQuery: String): Flow<Resource<List<Movie>>>
    suspend fun searchTvShow(searchQuery: String): Flow<Resource<List<Movie>>>
}