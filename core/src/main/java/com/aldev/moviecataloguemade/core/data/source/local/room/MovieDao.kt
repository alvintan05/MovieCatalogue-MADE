package com.aldev.moviecataloguemade.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE movie_id = :id AND type = :type")
    suspend fun deleteMovie(id: Int, type: String)

    @Query("SELECT * FROM favorite ORDER BY added_at DESC")
    fun getFavoriteList(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM favorite WHERE movie_id = :id AND type = :type")
    fun getDetailFavorite(id: Int, type: String): Flow<FavoriteEntity>

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE movie_id = :id AND type = :type)")
    fun checkIsFavorite(id: Int, type: String): Flow<Boolean>
}