package com.aldev.moviecataloguemade.core.domain.usecase.detail

import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailInteractor @Inject constructor(
    private val movieRepository: MovieRepository
) : DetailUseCase {
    override suspend fun getDetailData(id: Int, type: String): Flow<Resource<DetailMovie>> {
        return when (type) {
            CommonConstant.MovieType.MOVIE -> {
                movieRepository.getDetailMovie(id)
            }
            else -> {
                movieRepository.getDetailTvShow(id)
            }
        }
    }

    override suspend fun insertMovie(detailMovie: DetailMovie) {
        movieRepository.insertMovie(detailMovie)
    }

    override suspend fun deleteMovie(id: Int, type: String) {
        movieRepository.deleteMovie(id, type)
    }

    override fun getDetailFavorite(id: Int, type: String): Flow<Resource<DetailMovie>> =
        movieRepository.getDetailFavorite(id, type)

    override fun checkIsFavorite(id: Int, type: String): Flow<Boolean> =
        movieRepository.checkIsFavorite(id, type)
}