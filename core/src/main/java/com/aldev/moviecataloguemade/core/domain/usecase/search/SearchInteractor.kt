package com.aldev.moviecataloguemade.core.domain.usecase.search

import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : SearchUseCase {
    override suspend fun searchData(
        searchQuery: String,
        type: String
    ): Flow<Resource<List<Movie>>> {
        return when (type) {
            CommonConstant.MovieType.MOVIE -> {
                movieRepository.searchMovie(searchQuery)
            }
            else -> {
                movieRepository.searchTvShow(searchQuery)
            }
        }
    }
}