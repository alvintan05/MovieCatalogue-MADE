package com.aldev.moviecataloguemade.core.data

import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.source.local.LocalDataSourceImpl
import com.aldev.moviecataloguemade.core.data.source.remote.RemoteDataSourceImpl
import com.aldev.moviecataloguemade.core.data.source.remote.network.ApiResponse
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import com.aldev.moviecataloguemade.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
) : MovieRepository {
    override fun getMovieList(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getListMovie().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapMovieResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error(CommonConstant.RESPONSE_EMPTY))
            }
        }
    }

    override fun getTvShowList(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.getListTvShow().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapTvResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error(CommonConstant.RESPONSE_EMPTY))
            }
        }
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<DetailMovie>> = flow {
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

    override fun getDetailTvShow(tvId: Int): Flow<Resource<DetailMovie>> = flow {
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
        val favoriteEntity = DataMapper.mapDetailDomainToEntity(detailMovie)
        localDataSource.insertMovie(favoriteEntity)
    }

    override suspend fun deleteMovie(id: Int, type: String) {
        localDataSource.deleteMovie(id, type)
    }

    override fun getFavoriteList(): Flow<List<Movie>>{
        return localDataSource.getFavoriteList().map {
            DataMapper.mapListEntityToDomain(it)
        }
    }

    override fun getDetailFavorite(id: Int, type: String): Flow<Resource<DetailMovie>> = flow {
        emit(Resource.Loading())
        val response = localDataSource.getDetailFavorite(id, type).first()
        if (response != null) {
            emit(Resource.Success(DataMapper.mapDetailEntityToDomain(response)))
        } else {
            emit(Resource.Error("Empty Data"))
        }
    }

    override fun checkIsFavorite(id: Int, type: String): Flow<Boolean> =
        localDataSource.checkIsFavorite(id, type)

    override fun searchMovie(searchQuery: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.searchMovie(searchQuery).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapMovieResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error(CommonConstant.RESPONSE_EMPTY))
            }
        }
    }

    override fun searchTvShow(searchQuery: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        when (val response = remoteDataSource.searchTvShow(searchQuery).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(DataMapper.mapTvResponsesToDomain(response.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(response.errorMessage))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error(CommonConstant.RESPONSE_EMPTY))
            }
        }
    }
}