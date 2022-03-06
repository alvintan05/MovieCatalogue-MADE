package com.aldev.moviecataloguemade.core.domain.repository

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailMovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailTvResponse
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
}