package com.aldev.moviecataloguemade.core.domain.usecase.movie

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getListData(type: String): Flow<Resource<List<Movie>>>
    fun getFavoriteList(): Flow<List<Movie>>
}