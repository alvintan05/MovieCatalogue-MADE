package com.aldev.moviecataloguemade.core.domain.usecase.movie

import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override suspend fun getListData(type: String): Flow<Resource<List<Movie>>> {
        return when (type) {
            CommonConstant.MovieType.MOVIE -> {
                movieRepository.getMovieList()
            }
            else -> {
                movieRepository.getTvShowList()
            }
        }
    }

    override fun getFavoriteList(): Flow<Resource<List<Movie>>> =
        movieRepository.getFavoriteList()
}