package com.aldev.moviecataloguemade.core.domain.usecase.movie

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.data.source.local.entity.FavoriteEntity
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun getListData(type: String): Flow<Resource<List<Movie>>>
    fun getFavoriteList(): Flow<Resource<List<Movie>>>
}