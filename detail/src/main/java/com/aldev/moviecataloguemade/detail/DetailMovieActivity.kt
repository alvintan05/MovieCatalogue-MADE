package com.aldev.moviecataloguemade.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.aldev.moviecataloguemade.common.base.BaseActivity
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.core.domain.model.DetailMovie
import com.aldev.moviecataloguemade.detail.databinding.ActivityDetailMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>() {

    private val viewModel: DetailMovieViewModel by viewModels()

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityDetailMovieBinding =
        ActivityDetailMovieBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()
        viewModel.setIntentExtraData(intent.extras)
        viewModel.getDetailData()
        observeLiveData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapseToolbarDetail.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
    }

    private fun observeLiveData() {
        viewModel.detailMovieLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d("TAG", "loading")
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val detailData = it.data
                    if (detailData != null) {
                        setDetailMovieData(detailData)
                    }
                    Log.d("TAG", "success")
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("TAG", it.message ?: "error")
                }
            }
        }
    }

    private fun setDetailMovieData(data: DetailMovie) {
        binding.apply {
            collapseToolbarDetail.title = data.title
            tvDetailTitle.text = data.title
            tvDetailDate.text = resources.getString(R.string.release_time, data.releaseDate)
            tvDetailLength.text = resources.getString(R.string.length, data.runtime.toString())
            tvDetailCategory.text = data.genres
            tvDetailSynopsis.text = data.overview
            tvDetailRating.text = data.rating.toString()

            Glide.with(this@DetailMovieActivity)
                .load(CommonConstant.posterUrlPath + data.posterPath)
                .apply(
                    RequestOptions.placeholderOf(com.aldev.moviecataloguemade.common.R.color.background_home)
                        .error(com.aldev.moviecataloguemade.common.R.color.background_home)
                )
                .into(imgDetailPoster)

            Glide.with(this@DetailMovieActivity)
                .load(CommonConstant.backdropUrlPath + data.backdropPath)
                .apply(
                    RequestOptions.placeholderOf((com.aldev.moviecataloguemade.common.R.color.background_home))
                        .error((com.aldev.moviecataloguemade.common.R.color.background_home))
                )
                .into(imgDetailBackdrop)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}