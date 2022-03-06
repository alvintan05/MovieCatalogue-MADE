package com.aldev.moviecataloguemade.core.data

import com.aldev.moviecataloguemade.core.data.source.local.LocalDataSourceImpl
import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import com.aldev.moviecataloguemade.core.data.source.remote.RemoteDataSourceImpl
import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailMovieResponse
import com.aldev.moviecataloguemade.core.data.source.remote.response.DetailTvResponse
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
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
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
) : MovieRepository {
    override suspend fun getMovieList(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getListMovie().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapMovieResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("Empty Data From API"))
            }
        }
    }

    override suspend fun getTvShowList(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getListTvShow().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapTvResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error("Empty Data From API"))
            }
        }
    }

    override suspend fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getDetailMovie(movieId).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapDetailMovieResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
        }
    }

    override suspend fun getDetailTvShow(tvId: Int): Flow<Resource<DetailMovie>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getDetailTvShow(tvId).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapDetailTvResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
        }
    }

    override suspend fun insertMovie(detailMovie: DetailMovie) {
        val favoriteEntity = DataMapper.mapDomainToEntity(detailMovie)
        localDataSource.insertMovie(favoriteEntity)
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