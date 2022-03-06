package com.aldev.moviecataloguemade.core.domain.usecase.detail

import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    suspend fun getDetailData(id: Int, type: String): Flow<Resource<DetailMovie>>
    suspend fun insertMovie(detailMovie: DetailMovie)
    suspend fun deleteMovie(id: Int, type: String)
    fun getDetailFavorite(id: Int, type: String): Flow<Resource<DetailMovie>>
    fun checkIsFavorite(id: Int, type: String): Flow<Boolean>
}