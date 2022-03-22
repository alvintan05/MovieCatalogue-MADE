package com.aldev.moviecataloguemade.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    val movieListLiveData: LiveData<Resource<List<Movie>>> get() = _movieListLiveData

    private var _movieListLiveData: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    init {
        requestMovieList()
    }

    fun requestMovieList() {
        viewModelScope.launch {
            movieUseCase.getListData(CommonConstant.MovieType.MOVIE).collect {
                _movieListLiveData.value = it
            }
        }
    }
}