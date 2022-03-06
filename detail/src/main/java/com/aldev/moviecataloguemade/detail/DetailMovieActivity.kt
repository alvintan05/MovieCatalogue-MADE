package com.aldev.moviecataloguemade.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.aldev.moviecataloguemade.common.base.BaseActivity
import com.aldev.moviecataloguemade.detail.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>() {

    private val viewModel: DetailMovieViewModel by viewModels()

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityDetailMovieBinding =
        ActivityDetailMovieBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}