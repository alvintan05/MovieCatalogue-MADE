package com.aldev.moviecataloguemade.core.data

import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import com.aldev.moviecataloguemade.core.data.source.remote.RemoteDataSource
import com.aldev.moviecataloguemade.core.data.source.remote.RemoteDataSourceImpl
import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiResponse
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import com.aldev.moviecataloguemade.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) : MovieRepository {
    override suspend fun getMovieList(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSourceImpl.getListMovie().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("Empty"))
            }
        }
    }

    override suspend fun getTvShowList(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertMovie(favoriteEntity: FavoriteEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovie(id: Int, type: String) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteList(type: String): Flow<List<FavoriteEntity>> {
        TODO("Not yet implemented")
    }

    override fun getDetailFavorite(id: Int, type: String): Flow<FavoriteEntity> {
        TODO("Not yet implemented")
    }

    override fun checkIsFavorite(id: Int, type: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}