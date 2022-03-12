package com.aldev.moviecataloguemade.ui.movie

import android.content.Intent
import android.os.Bundle
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
import com.aldev.moviecataloguemade.databinding.FragmentMovieListBinding
import com.aldev.moviecataloguemade.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    private val viewModel: MovieListViewModel by viewModels()
    private val movieListAdapter = MovieListAdapter()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieListBinding = FragmentMovieListBinding.inflate(inflater, container, false)

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
            adapter = movieListAdapter
        }

        movieListAdapter.onItemClick = { movie ->
            val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
            intent.putExtras(
                bundleOf().apply {
                    putInt(CommonConstant.DetailIntentExtra.EXTRA_ID, movie.id)
                    putString(
                        CommonConstant.DetailIntentExtra.EXTRA_TYPE,
                        CommonConstant.MovieType.MOVIE
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
            viewModel.requestMovieList()
            binding?.progressBar?.visibility = View.VISIBLE
        }
    }

    override fun observeLiveData() {
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding?.progressBar?.visible()
                    binding?.rvMovies?.gone()
                }
                is Resource.Success -> {
                    binding?.progressBar?.gone()
                    binding?.tvError?.gone()
                    binding?.rvMovies?.visible()
                    movieListAdapter.setData(it.data)
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