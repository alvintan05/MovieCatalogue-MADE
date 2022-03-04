package com.aldev.moviecataloguemade.core.domain.usecase

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : MovieUseCase {
    override suspend fun getMovieList(): Flow<Resource<List<Movie>>> =
        movieRepository.getMovieList()
}