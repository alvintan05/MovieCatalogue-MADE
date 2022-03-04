package com.aldev.moviecataloguemade.core.data.source.remote

import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.MovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getListMovie(): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun getListTvShow(): Flow<ApiResponse<List<TvShowResponse>>>
}