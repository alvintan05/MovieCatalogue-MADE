package com.aldev.moviecataloguemade.core.data.source.remote.network

import com.aldev.moviecataloguemade.core.BuildConfig
import com.aldev.moviecataloguemade.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): DetailMovieResponse

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): DetailTvResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("query") searchQuery: String
    ): ListResponse<List<MovieResponse>>

    @GET("search/tv")
    suspend fun searchTvShow(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("query") searchQuery: String
    ): ListResponse<List<TvResponse>>
}