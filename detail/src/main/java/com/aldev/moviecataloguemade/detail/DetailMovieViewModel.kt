package com.aldev.moviecataloguemade.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.usecase.detail.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase
) : ViewModel() {

    private var movieId = 0
    private var movieType = ""
    private var sourceType = ""

    val detailMovieLiveData: LiveData<Resource<DetailMovie>> get() = _detailMovieLiveData
    val favoriteStatus: LiveData<Boolean> get() = _favoriteStatus

    private var _detailMovieLiveData: MutableLiveData<Resource<DetailMovie>> = MutableLiveData()
    private var _favoriteStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun setIntentExtraData(bundle: Bundle?) {
        movieId = bundle?.getInt(CommonConstant.DetailIntentExtra.EXTRA_ID) ?: 0
        movieType = bundle?.getString(CommonConstant.DetailIntentExtra.EXTRA_TYPE).orEmpty()
        sourceType = bundle?.getString(CommonConstant.DetailIntentExtra.EXTRA_SOURCE).orEmpty()
    }

    fun getDetailData() {
        if (movieId != 0 && movieType.isNotEmpty()) {
            viewModelScope.launch {
                if (sourceType == CommonConstant.DataSource.REMOTE) {
                    detailUseCase.getDetailData(movieId, movieType).collect {
                        _detailMovieLiveData.value = it
                    }
                } else {
                    detailUseCase.getDetailFavorite(movieId, movieType).collect {
                        _detailMovieLiveData.value = it
                    }
                }
            }
        }
    }

    fun requestFavoriteStatus() {
        if (movieId != 0 && movieType.isNotEmpty()) {
            viewModelScope.launch {
                detailUseCase.checkIsFavorite(movieId, movieType).collect {
                    _favoriteStatus.value = it
                }
            }
        }
    }

    fun setFavorite(status: Boolean) {
        viewModelScope.launch {
            if (status) {
                _detailMovieLiveData.value?.data?.let { detailUseCase.insertMovie(it) }
            } else {
                detailUseCase.deleteMovie(movieId, movieType)
            }
        }
    }
}