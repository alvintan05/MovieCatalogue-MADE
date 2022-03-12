package com.aldev.moviecataloguemade.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.aldev.moviecataloguemade.common.base.BaseFragment
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.common.gone
import com.aldev.moviecataloguemade.common.visible
import com.aldev.moviecataloguemade.core.data.Resource
import com.aldev.moviecataloguemade.detail.DetailMovieActivity
import com.aldev.moviecataloguemade.tvshow.databinding.FragmentTvShowListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowListFragment : BaseFragment<FragmentTvShowListBinding>() {

    private val viewModel: TvShowListViewModel by viewModels()
    private val tvShowListAdapter = TvShowListAdapter()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTvShowListBinding = FragmentTvShowListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setupRecyclerView()
            setupSwipeRefresh()
            observeLiveData()
        }
    }

    private fun setupRecyclerView() {
        binding?.rvMovies?.apply {
            setHasFixedSize(true)
            adapter = tvShowListAdapter
        }

        tvShowListAdapter.onItemClick = { movie ->
            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
            intent.putExtras(
                bundleOf().apply {
                    putInt(CommonConstant.DetailIntentExtra.EXTRA_ID, movie.id)
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_TYPE,
                        CommonConstant.MovieType.TV_SHOW
                    )
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_SOURCE,
                        CommonConstant.DataSource.REMOTE
                    )
                }
            )
            startActivity(intent)
        }
    }

    private fun setupSwipeRefresh() {
        binding?.swipeRefresh?.setColorSchemeResources(com.aldev.moviecataloguemade.common.R.color.blue_primary)
        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = false
            viewModel.requestTvShowList()
            binding?.progressBar?.visibility = View.VISIBLE
        }
    }

    override fun observeLiveData() {
        viewModel.tvShowListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding?.progressBar?.visible()
                    binding?.rvMovies?.gone()
                }
                is Resource.Success -> {
                    binding?.progressBar?.gone()
                    binding?.tvError?.gone()
                    binding?.rvMovies?.visible()
                    tvShowListAdapter.setData(it.data)
                }
                is Resource.Error -> {
                    binding?.progressBar?.gone()
                    binding?.rvMovies?.gone()
                    binding?.tvError?.visible()

                    if (it.message == CommonConstant.RESPONSE_EMPTY) {
                        binding?.tvError?.text =
                            getString(com.aldev.moviecataloguemade.common.R.string.not_found_label)
                    } else {
                        binding?.tvError?.text =
                            getString(com.aldev.moviecataloguemade.common.R.string.error_label)
                    }
                }
            }
        }
    }

}