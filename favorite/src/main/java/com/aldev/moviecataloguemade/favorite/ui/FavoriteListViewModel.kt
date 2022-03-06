package com.aldev.moviecataloguemade.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase

class FavoriteListViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    var favoriteListLiveData: LiveData<Resource<List<Movie>>> =
        movieUseCase.getFavoriteList().asLiveData()
}