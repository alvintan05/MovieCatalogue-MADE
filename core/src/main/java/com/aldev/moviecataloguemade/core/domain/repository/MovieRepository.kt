package com.aldev.moviecataloguemade.core.domain.repository

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(): Flow<Resource<List<Movie>>>
    suspend fun getTvShowList(): Flow<Resource<List<Movie>>>
    suspend fun insertMovie(favoriteEntity: FavoriteEntity)
    suspend fun deleteMovie(id: Int, type: String)
    fun getFavoriteList(type: String): Flow<List<FavoriteEntity>>
    fun getDetailFavorite(id: Int, type: String): Flow<FavoriteEntity>
    fun checkIsFavorite(id: Int, type: String): Flow<Boolean>
}