package com.aldev.moviecataloguemade.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    lateinit var favoriteListLiveData: LiveData<Resource<List<Movie>>>

    init {
        requestFavoriteList()
    }

    fun requestFavoriteList() = viewModelScope.launch {
        favoriteListLiveData = movieUseCase.getFavoriteList().asLiveData()
    }

}