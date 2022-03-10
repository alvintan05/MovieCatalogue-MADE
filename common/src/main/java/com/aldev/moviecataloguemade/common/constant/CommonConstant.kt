package com.aldev.moviecataloguemade.common.constant

import com.aldev.moviecataloguemade.common.BuildConfig

object CommonConstant {
    object MovieType{
        const val MOVIE = "movie"
        const val TV_SHOW = "tv"
    }

    const val posterUrlPath = "${BuildConfig.IMAGE_BASE_URL}w342"
    const val backdropUrlPath = "${BuildConfig.IMAGE_BASE_URL}w780"

    object DataSource{
        const val REMOTE = "remote"
        const val LOCAL = "local"
    }

    object DetailIntentExtra{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_SOURCE = "extra_source"
    }
}