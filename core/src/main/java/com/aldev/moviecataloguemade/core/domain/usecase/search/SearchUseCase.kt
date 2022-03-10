package com.aldev.moviecataloguemade.core.domain.usecase.search

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend fun searchData(searchQuery: String, type: String): Flow<Resource<List<Movie>>>
}