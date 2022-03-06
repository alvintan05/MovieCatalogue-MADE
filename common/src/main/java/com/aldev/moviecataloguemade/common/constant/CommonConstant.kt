package com.aldev.moviecataloguemade.common.constant

import com.aldev.moviecataloguemade.common.BuildConfig

object CommonConstant {
    object MovieType{
        const val MOVIE = "movie"
        const val TV_SHOW = "tv"
    }

    const val posterUrlPath = "${BuildConfig.IMAGE_BASE_URL}w342"
    const val backdropUrlPath = "${BuildConfig.IMAGE_BASE_URL}w780"
}