package com.aldev.moviecataloguemade.core.data.source.local

import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertMovie(favoriteEntity: FavoriteEntity)
    suspend fun deleteMovie(id: Int, type: String)
    fun getFavoriteList(): Flow<List<FavoriteEntity>>
    fun getDetailFavorite(id: Int, type: String): Flow<FavoriteEntity?>
    fun checkIsFavorite(id: Int, type: String): Flow<Boolean>
}