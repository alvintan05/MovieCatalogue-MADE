package com.aldev.moviecataloguemade.tvshow

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
class TvShowListViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    val tvShowListLiveData: LiveData<Resource<List<Movie>>> get() = _tvShowListLiveData

    private val _tvShowListLiveData: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    init {
        requestTvShowList()
    }

    fun requestTvShowList() {
        viewModelScope.launch {
            movieUseCase.getListData(CommonConstant.MovieType.TV_SHOW).collect {
                _tvShowListLiveData.value = it
            }
        }
    }

}