package com.aldev.moviecataloguemade.core.data.source.remote

import android.util.Log
import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiResponse
import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiService
import com.aldev.moviecataloguemade.core.data.source.remote.response.MovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getListMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                dataArray?.let {
                    if (it.isNotEmpty()){
                        emit(ApiResponse.Success(response.results))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getListTvShow(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows()
                val dataArray = response.results
                dataArray?.let {
                    if (it.isNotEmpty()){
                        emit(ApiResponse.Success(response.results))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}