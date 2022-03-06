package com.aldev.moviecataloguemade.detail

import androidx.lifecycle.ViewModel
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

}