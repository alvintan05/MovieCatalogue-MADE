package com.aldev.moviecataloguemade.core.domain.usecase

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getMovieList(): Flow<Resource<List<Movie>>>
}