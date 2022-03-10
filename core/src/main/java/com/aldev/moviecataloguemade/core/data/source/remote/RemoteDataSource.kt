package com.aldev.moviecataloguemade.core.data.source.remote

import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailMovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailTvResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.MovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.TvResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getListMovie(): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun getListTvShow(): Flow<ApiResponse<List<TvResponse>>>
    suspend fun getDetailMovie(movieId: Int): Flow<ApiResponse<DetailMovieResponse>>
    suspend fun getDetailTvShow(tvId: Int): Flow<ApiResponse<DetailTvResponse>>
    suspend fun searchMovie(searchQuery: String): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun searchTvShow(searchQuery: String): Flow<ApiResponse<List<TvResponse>>>
}