package com.aldev.moviecataloguemade.detail

import android.os.Bundle
import androidx.lifecycle.*
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.core.domain.usecase.detail.DetailInteractor
import com.aldev.moviecataloguemade.core.domain.usecase.detail.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase
) : ViewModel() {

    private var movieId = 0
    private var movieType = ""
    private var sourceType = ""

    var detailMovieLiveData: LiveData<Resource<DetailMovie>> = MutableLiveData()
    var favoriteStatus: LiveData<Boolean> = MutableLiveData()

    fun setIntentExtraData(bundle: Bundle?) {
        movieId = bundle?.getInt(CommonConstant.DetailIntentExtra.EXTRA_ID) ?: 0
        movieType = bundle?.getString(CommonConstant.DetailIntentExtra.EXTRA_TYPE).orEmpty()
        sourceType = bundle?.getString(CommonConstant.DetailIntentExtra.EXTRA_SOURCE).orEmpty()
    }

    fun getDetailData() {
        if (movieId != 0 && movieType.isNotEmpty()) {
            viewModelScope.launch {
                detailMovieLiveData = if (sourceType == CommonConstant.DataSource.REMOTE) {
                    detailUseCase.getDetailData(movieId, movieType).asLiveData()
                } else {
                    detailUseCase.getDetailFavorite(movieId, movieType).asLiveData()
                }
            }
        }
    }

    fun requestFavoriteStatus() {
        if (movieId != 0 && movieType.isNotEmpty()) {
            viewModelScope.launch {
                favoriteStatus = detailUseCase.checkIsFavorite(movieId, movieType).asLiveData()
            }
        }
    }

    fun setFavorite(status: Boolean) {
        viewModelScope.launch {
            if (status) {
                detailMovieLiveData.value?.data?.let { detailUseCase.insertMovie(it) }
            } else {
                detailUseCase.deleteMovie(movieId, movieType)
            }
        }
    }
}