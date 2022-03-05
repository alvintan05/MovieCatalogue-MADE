package com.aldev.moviecataloguemade.core.data.source.remote.network

import com.aldev.moviecataloguemade.core.BuildConfig
import com.aldev.moviecataloguemade.core.data.source.remote.response.ListResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.MovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.TvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): ListResponse<List<MovieResponse>>

    @GET("tv/popular")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): ListResponse<List<TvResponse>>
}