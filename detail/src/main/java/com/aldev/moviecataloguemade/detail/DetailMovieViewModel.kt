package com.aldev.moviecataloguemade.detail

import android.os.Bundle
import androidx.lifecycle.*
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.usecase.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private var movieId = 0
    private var movieType = ""

    var detailMovieLiveData: LiveData<Resource<DetailMovie>> = MutableLiveData()

    fun setIntentExtraData(bundle: Bundle?) {
        movieId = bundle?.getInt(CommonConstant.DetailIntentExtra.EXTRA_ID) ?: 0
        movieType = bundle?.getString(CommonConstant.DetailIntentExtra.EXTRA_TYPE).orEmpty()
    }

    fun getDetailData() {
        if (movieId != 0 && movieType.isNotEmpty()) {
            viewModelScope.launch {
                detailMovieLiveData = movieUseCase.getDetailData(movieId, movieType).asLiveData()
            }
        }
    }

}