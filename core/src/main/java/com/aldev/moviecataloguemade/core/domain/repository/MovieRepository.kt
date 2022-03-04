package com.aldev.moviecataloguemade.core.domain.repository

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(): Flow<Resource<List<Movie>>>
    suspend fun getTvShowList(): Flow<Resource<List<Movie>>>
}