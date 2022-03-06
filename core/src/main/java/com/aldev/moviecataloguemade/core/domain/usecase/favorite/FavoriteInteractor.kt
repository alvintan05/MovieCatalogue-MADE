package com.aldev.moviecataloguemade.core.domain.usecase.favorite

import com.aldev.moviecataloguemade.core.domain.repository.MovieRepository
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(
    private val movieRepository: MovieRepository
): FavoriteUseCase {
}