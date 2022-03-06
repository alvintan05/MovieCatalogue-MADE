package com.aldev.moviecataloguemade.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    lateinit var movieListLiveData: LiveData<Resource<List<Movie>>>

    init {
        requestMovieList()
    }

    fun requestMovieList() = viewModelScope.launch {
        movieListLiveData = movieUseCase.getListData(CommonConstant.MovieType.MOVIE).asLiveData()
    }

}