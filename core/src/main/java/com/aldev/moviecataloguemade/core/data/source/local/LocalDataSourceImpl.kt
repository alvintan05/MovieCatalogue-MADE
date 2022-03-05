package com.aldev.moviecataloguemade.core.data.source.local

import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import com.aldev.moviecataloguemade.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : LocalDataSource {
    override suspend fun insertMovie(favoriteEntity: FavoriteEntity) {
        movieDao.insertMovie(favoriteEntity)
    }

    override suspend fun deleteMovie(id: Int, type: String) {
        movieDao.deleteMovie(id, type)
    }

    override fun getFavoriteList(type: String): Flow<List<FavoriteEntity>> =
        movieDao.getFavoriteList(type)

    override fun getDetailFavorite(id: Int, type: String): Flow<FavoriteEntity> =
        movieDao.getDetailFavorite(id, type)

    override fun checkIsFavorite(id: Int, type: String): Flow<Boolean> =
        movieDao.checkIsFavorite(id, type)
}